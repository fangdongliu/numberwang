package cn.fdongl.numberwangbackend.controller;

import cn.fdongl.numberwangbackend.repository.TableDescriptionRepository;
import cn.fdongl.numberwangbackend.repository.TableSummaryRepository;
import cn.fdongl.numberwangentity.entity.Report;
import cn.fdongl.numberwangentity.entity.Tables;
import cn.fdongl.numberwangentity.result.Result;
import cn.fdongl.numberwangbackend.repository.ReportRepository;
import cn.fdongl.numberwangbackend.repository.TablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("report")
public class ReportController {

    @Autowired
    TablesRepository tablesRepository;

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    TableDescriptionRepository tableDescriptionRepository;

    @Autowired
    TableSummaryRepository tableSummaryRepository;

    @GetMapping("status")
    public Result status(
            @RequestParam Long jobId,
            @RequestParam Long version){
        Map<String,Object> result = new HashMap<>(20);
        Report report = reportRepository.findByJobIdAndVersionGreaterThanAndStatusIsNot(jobId,version,-1L);
        if(report!=null){
            result.put("report",report);
            result.put("tables",tableDescriptionRepository.findByReportId(report.getId()));
            result.put("summary",tableSummaryRepository.findByReportId(report.getId()));
        }

        return Result.success(result);
    }

    @GetMapping("list/{tableName}")
    public Result list(
            @RequestParam Long reportId,
            @PathVariable("tableName")String tableName,
            @RequestParam Integer page,
            @RequestParam Integer pageSize){
        PageRequest pageRequest = PageRequest.of(page,pageSize);
        return Result.success(tablesRepository.findByReportIdAndTableName(reportId,tableName,pageRequest));
    }

}
