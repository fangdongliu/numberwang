package cn.fdongl.numberwangbackend.controller;

import cn.fdongl.numberwangentity.entity.Tables;
import cn.fdongl.numberwangentity.result.Result;
import cn.fdongl.numberwangbackend.repository.ReportRepository;
import cn.fdongl.numberwangbackend.repository.TablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("report")
public class ReportController {

    @Autowired
    TablesRepository tablesRepository;

    @Autowired
    ReportRepository reportRepository;

    @GetMapping("status")
    public Result status(
            @RequestParam Long jobId,
            @RequestParam Long version){
//        Map<String,Object> result = new HashMap<>(20);
//        List<Map.Entry<String,Object>>summary = new ArrayList<>();
//        summary.add(new AbstractMap.SimpleEntry<>("时间范围","2018.8.20 - 2019.9.12"));
//        summary.add(new AbstractMap.SimpleEntry<>("总用户数",8000));
//        summary.add(new AbstractMap.SimpleEntry<>("总会话数",8000));
//        summary.add(new AbstractMap.SimpleEntry<>("总下载量","800MB"));
//
//        List<TableResult> tables =new ArrayList<>();
//        tables.add(new TableResult("资源访问情况","/record/list/hh","这个表显示了资源的访问情况不是吗",200,"pie,line"));
//        tables.add(new TableResult("bb","/record/list/hf","ni hh",210,"pie"));
//        tables.add(new TableResult("aa","/record/list/dd","ni hh",18,"line"));
//
//        result.put("summary",summary);
//        result.put("version",version+1);
//        result.put("tables",tables);
        return Result.success(reportRepository.findByJobIdAndVersionGreaterThan(jobId,version));
    }

    @GetMapping("list/{tableName}")
    public Result list(
            @RequestParam Long reportId,
            @PathVariable("tableName")String tableName,
            @RequestParam Integer page,
            @RequestParam Integer pageSize){
        List<Tables>tables = new ArrayList<>();
        for(long i=page;i<page+pageSize;i++){
            tables.add(new Tables(i,reportId,tableName,"{\"hh\":\"ggg\"}"));
        }

        return Result.success(tables);
    }

}
