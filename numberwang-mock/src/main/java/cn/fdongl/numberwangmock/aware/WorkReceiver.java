package cn.fdongl.numberwangmock.aware;

import cn.fdongl.numberwangentity.entity.Job;
import cn.fdongl.numberwangentity.entity.Report;
import cn.fdongl.numberwangentity.result.TableResult;
import cn.fdongl.numberwangmock.repository.JobRepository;
import cn.fdongl.numberwangmock.repository.ReportRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springfox.documentation.spring.web.json.Json;

import java.util.*;

@Component
@Slf4j
public class WorkReceiver {

    @Autowired
    ReportRepository reportRepository;
    @Autowired
    JobRepository jobRepository;

    @RabbitListener(queues = "work-queue")
    public void receive(Long object) {
        long jobId =  object;
        int n =jobRepository.updateJobStatus(1L,jobId,0L);
        if(n>0){
            log.info("开始处理Job:jobId:"+jobId);
            Job job = jobRepository.findById(jobId).get();
            long reportId = createReport(jobId);
            try {
                List<TableResult>list = new ArrayList<>();
                list.add(new TableResult("资源访问情况","/record/list/hh","这个表显示了资源的访问情况不是吗",200,"pie,line"));
                list.add(new TableResult("bb","/record/list/hf","ni hh",210,"pie"));
                list.add(new TableResult("aa","/record/list/dd","ni hh",18,"line"));
                for (TableResult tableResult : list) {
                    Thread.sleep(3000);
                    updateReportData(reportId,tableResult.toJson(),null);
                }
                List<Map.Entry<String,Object>>summary = new ArrayList<>();
                summary.add(new AbstractMap.SimpleEntry<>("时间范围","2018.8.20 - 2019.9.12"));
                summary.add(new AbstractMap.SimpleEntry<>("总用户数",8000));
                summary.add(new AbstractMap.SimpleEntry<>("总会话数",8000));
                summary.add(new AbstractMap.SimpleEntry<>("总下载量","800MB"));
                for (Map.Entry<String, Object> stringObjectEntry : summary) {
                    Thread.sleep(3000);
                    updateReportData(reportId,null,
                            '{'+ "\"key\":\""+stringObjectEntry.getKey()+"\","+
                            "\"value\":\""+stringObjectEntry.getValue()+"\""
                                    +'}');
                }
                jobRepository.updateJobStatus(2L,jobId,1L);
                finishReport(reportId);
                log.info("job处理完毕:jobId:"+jobId);
            } catch (Exception e) {
                jobRepository.updateJobStatus(-1L,jobId,1L);
                e.printStackTrace();
            }


        }else{
            log.info("任务处理失败:jobId:"+jobId);
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

    private void updateReportData(long reportId,String table,String summary){
        Report report = reportRepository.findById(reportId).get();
        if(table!=null){
            if(report.getTables().length()==0){
                report.setTables(table);
            }else{
                report.setTables(report.getTables()+';'+table);
            }
            reportRepository.save(report);
        }
        if(summary!=null){
            if(report.getSummary().length()==0){
                report.setSummary(summary);
            }else{
                report.setSummary(report.getSummary()+';'+summary);
            }
            reportRepository.save(report);
        }
    }



}