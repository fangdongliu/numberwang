package cn.fdongl.numberwangmrworker.core;

import cn.fdongl.numberwangentity.entity.*;
import cn.fdongl.numberwangmrworker.NumberwangmrworkerApplication;
import cn.fdongl.numberwangmrworker.lineformat.LineFormat;
import cn.fdongl.numberwangmrworker.lineformat.LineFormatSource;
import cn.fdongl.numberwangmrworker.mapreduce.RecordMapper;
import cn.fdongl.numberwangmrworker.repository.*;
import cn.fdongl.numberwangmrworker.task.HiveTask;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.*;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class WorkReceiver {

//    final
//    JdbcTemplate jdbcTemplate;

    @Autowired
    AsyncSave asyncSave;

    @Autowired
    List<HiveTask>hiveTaskList;

    @Autowired
    ReportRepository reportRepository;
    @Autowired
    JobRepository jobRepository;

    @Autowired
    TableSummaryRepository tableSummaryRepository;

    @Autowired
    TableDescriptionRepository tableDescriptionRepository;

    @Autowired
    TablesRepository tablesRepository;

    @Autowired
    Configuration configuration;

    AtomicInteger atomicInteger = new AtomicInteger(0);

//    @Autowired
//    public WorkReceiver(@Qualifier("hiveJdbcTemplate")JdbcTemplate dataSource) {
//        this.jdbcTemplate = dataSource;
//    }

    @RabbitListener(queues = "work-queue")
    public void receive(Long object) throws Exception {
        long jobId =  object;
        int n =jobRepository.updateJobStatus(1L,jobId,0L);
        if(n>0){
            log.info("开始处理Job:jobId:"+jobId);
            Job job = jobRepository.findById(jobId).get();
            long reportId = createReport(jobId);
            try {
                startMapReduce(job,reportId);
                while(atomicInteger.get()!=0){
                    Thread.sleep(1000);
                }
                finishReport(reportId);
                jobRepository.updateJobStatus(2L,jobId,1L);
                log.info("job处理完毕:jobId:"+jobId);
            }catch (Exception e){
                log.info("job处理失败:jobId:"+jobId);
                jobRepository.updateJobStatus(-1L,jobId,1L);
                throw e;
            }
        }else{
            log.info("job开始失败:jobId:"+jobId);
        }
    }

    private long createReport(long jobId){
        Report report = new Report();
        report.setJobId(jobId);
        report.setVersion(1L);
        report.setStatus(0L);
        report.setSummary("");
        report.setTables("");
        reportRepository.save(report);
        return report.getId();
    }

    private void finishReport(long reportId){
        Report report = reportRepository.findById(reportId).get();
        report.setStatus(1L);
        reportRepository.save(report);
    }

    public String startMapReduce(Job hjob,Long reportId) throws Exception {

        String jobName = "fd"+System.currentTimeMillis();

        org.apache.hadoop.mapreduce.Job job = org.apache.hadoop.mapreduce.Job.getInstance(configuration,jobName);
//
        LineFormatSource lineFormatSource = new LineFormatSource(hjob.getLineFormat(),hjob.getDateFormat());
//
        LineFormat lineFormat = lineFormatSource.buildLineFormat();
        RecordMapper.lineFormat = lineFormat;

        job.setJarByClass(NumberwangmrworkerApplication.class);

        StringBuilder builder = new StringBuilder();
        int len = 0;
        for (String input : hjob.getInput().split(",")) {
            if(len!=0){
                builder.append(',');
            }
            len++;
            builder.append("fp-");
            builder.append(input);
        }
        FileInputFormat.setInputPaths(job,builder.toString());
        FileOutputFormat.setOutputPath(job, new Path(jobName));

        job.setMapperClass(RecordMapper.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        job.waitForCompletion(true);
        if(!job.isSuccessful()){
            throw new Exception("mr failed:in workReceiver:jobName:"+jobName);
        }
//jdbcTemplate.setQueryTimeout(Integer.MAX_VALUE);
//            jdbcTemplate.execute("create table "+jobName+'('+lineFormat.getTableDefine()+") STORED AS TEXTFILE");
//            jdbcTemplate.execute("LOAD DATA INPATH '"+jobName+"' INTO TABLE "+jobName);

        Class.forName("org.apache.hive.jdbc.HiveDriver");
        try(
        Connection connection = DriverManager.getConnection("jdbc:hive2://192.168.157.129:10000/user_test");
        Statement statement = connection.createStatement();){
            statement.setQueryTimeout(Integer.MAX_VALUE);
                statement.execute("create table "+jobName+'('+lineFormat.getTableDefine()+") STORED AS TEXTFILE");
                statement.execute("LOAD DATA INPATH '"+jobName+"' INTO TABLE "+jobName);
            for (HiveTask hiveTask : hiveTaskList) {
                if(hiveTask.accept(lineFormat)){
                    ResultSet resultSet = statement.executeQuery(hiveTask.getSQL(jobName));

//                    List<Map<String, Object>> resultSet= jdbcTemplate.queryForList(hiveTask.getSQL(jobName));
//                    for (Map<String, Object> r : rs) {
//                        r.forEach((key,value)->{
//                            System.out.println(key+':'+value);
//                        });
//                    }
                    if(hiveTask.isSummary()){
                        if(resultSet.next()){
                            System.out.println("写入summary"+hiveTask.getName());
                            String value = resultSet.getString(1);
                            TableSummary tableSummary = new TableSummary();
                            tableSummary.setName(hiveTask.getName());
                            tableSummary.setDescription(hiveTask.getDescription());
                            tableSummary.setValue(value);
                            tableSummary.setReportId(reportId);
                            tableSummaryRepository.save(tableSummary);
                            reportRepository.updateReportStatus(reportId);
                        }
                    }else{
                        List<Tables> tables = convertResultSetToList(resultSet,reportId,hiveTask.getName());
                        System.out.println("写入tables："+tables.size()+" "+hiveTask.getName());

                        TableDescription tableDescription = new TableDescription();
                        tableDescription.setCount(tables.size());
                        tableDescription.setDescription(hiveTask.getDescription());
                        tableDescription.setName(hiveTask.getName());
                        tableDescription.setReportId(reportId);
                        tableDescription.setSchema(hiveTask.getSchema());
                        tableDescription.setShow(hiveTask.getShow());
                        tableDescription.setPath("/report/list/"+hiveTask.getName());
                        atomicInteger.incrementAndGet();
                        asyncSave.save(tables,tableDescription,reportId,atomicInteger);

                    }
                    resultSet.close();

                }
            }}
        System.out.println(atomicInteger.get());
        return jobName;
    }

    List<Tables> convertResultSetToList( ResultSet resultSet,Long reportId, String tableName) throws SQLException {
        List<Tables>result = new ArrayList<>();
        int len = resultSet.getMetaData().getColumnCount();
        while(resultSet.next()){
            StringBuilder builder = new StringBuilder();
            int n = 0;
            for(int i=1;i<=len;i++){
                if(i!=1){
                    builder.append('\1');
                }
                builder.append(resultSet.getString(i));
            }

            result.add(new Tables(null,reportId,tableName,builder.toString()));
        }

        return result;

    }

}