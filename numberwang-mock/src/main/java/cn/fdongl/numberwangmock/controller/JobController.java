package cn.fdongl.numberwangmock.controller;

import cn.fdongl.numberwangentity.entity.Job;
import cn.fdongl.numberwangentity.result.Result;
import cn.fdongl.numberwangmock.aware.WorkSend;
import cn.fdongl.numberwangmock.repository.JobRepository;
import cn.fdongl.numberwangmock.security.AppUser;
import cn.fdongl.numberwangmock.service.JobService;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("job")
public class JobController {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    JobService jobService;

    @Autowired
    private WorkSend workSend;

    @PostMapping("create")
    public Result create(String name,String description) throws Exception {
        Job job = new Job();
        job.setStatus(0L);
        job.setJobName(name);
        job.setDescription(description);
        jobRepository.save(job);
        return Result.success(job.getId());
    }

    @GetMapping("count")
    public Result count(AppUser user){
        return Result.success(jobRepository.countByCreateBy(user.getUser().getId()));
    }

    @GetMapping("detail")
    public Result detail(@RequestParam Long jobId,AppUser user){
        Job job = jobRepository.findByIdAndCreateBy(jobId,user.getUser().getId());
        if(job==null){
            return Result.fail("errMsg:jobController:detail:未发现目标Job");
        }
        return Result.success(job);
    }

    @GetMapping("list")
    public Result list(
            AppUser user,
            @RequestParam Integer page,
            @RequestParam Integer pageSize){
        PageRequest pageRequest = PageRequest.of(page,pageSize);
        return Result.success(jobRepository.findByCreateBy(user.getUser().getId(),pageRequest));
    }

    String join(Object[]array,char delimiter){
        StringBuffer buf = new StringBuffer();

        for(int i=0; i<array.length; i++){
            if(i>0){
                buf.append(delimiter);
            }
            buf.append( String.valueOf( array[i] ) );
        }
        return buf.toString();
    }

    @PutMapping("setInput")
    public Result setInput(AppUser user,@RequestParam Long jobId,@RequestParam("files[]")Long[]files){
        Job job = jobRepository.findByIdAndCreateBy(jobId,user.getUser().getId());
        if(job==null){
            return Result.fail("errMsg:jobController:setInput:未发现job");
        }
        job.setInput(join(files,','));
        jobRepository.save(job);
        return Result.success(files.length);
    }

    @PutMapping("setFormat")
    public Result setFormat(
            AppUser user,
            @RequestParam Long jobId,
            @RequestParam String lineFormat,
            @RequestParam String dateFormat,
            @RequestParam String suffix
    ){
        Job job = jobRepository.findByIdAndCreateBy(jobId,user.getUser().getId());
        if(job==null){
            return Result.fail("errMsg:jobController:setInput:未发现job");
        }
        job.setLineFormat(lineFormat);
        job.setDateFormat(dateFormat);
        job.setSuffix(suffix);
        jobRepository.save(job);
        return Result.success(1);
    }

    @PutMapping("submit")
    public Result submit(
            AppUser user,
            @RequestParam Long jobId){
        Job job = jobRepository.findByIdAndCreateBy(jobId,user.getUser().getId());
        jobService.reset(jobId);
        workSend.send(jobId);
        return Result.success("任务正在排队中");
    }






}
