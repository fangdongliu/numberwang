package cn.fdongl.numberwangbackend.service;

import cn.fdongl.numberwangbackend.repository.JobRepository;
import cn.fdongl.numberwangbackend.repository.ReportRepository;
import cn.fdongl.numberwangentity.entity.Report;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class JobService {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    ReportRepository reportRepository;

    @Transactional(rollbackFor = Exception.class)
    public void reset(Long jobId) throws Exception {
        Report report = reportRepository.findByJobIdAndVersionGreaterThanAndStatusIsNot(jobId,0L,-1L);
        if(report!=null){
            log.info("report found:jobId:"+jobId);
            report.setStatus(-1L);
            reportRepository.save(report);

//            throw new Exception("report not found");
        }
        jobRepository.updateJobStatus(0L,jobId,-1L);
        jobRepository.updateJobStatus(0L,jobId,2L);

    }

}
