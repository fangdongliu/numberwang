package cn.fdongl.numberwangmrworker.core;

import cn.fdongl.numberwangentity.entity.TableDescription;
import cn.fdongl.numberwangentity.entity.Tables;
import cn.fdongl.numberwangmrworker.repository.ReportRepository;
import cn.fdongl.numberwangmrworker.repository.TableDescriptionRepository;
import cn.fdongl.numberwangmrworker.repository.TablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AsyncSave {

    @Autowired
    TableDescriptionRepository tableDescriptionRepository;

    @Autowired
    TablesRepository tablesRepository;

    @Autowired
    ReportRepository reportRepository;

    @Async
    public void save(List<Tables> tablesList, TableDescription tableDescription, Long reportId, AtomicInteger atom){
        List<Tables>temp10 = new ArrayList<>();
        for (Tables table : tablesList) {
            if(temp10.size()==200){
                tablesRepository.saveAll(temp10);
                temp10.clear();
            }
            temp10.add(table);
        }
        tablesRepository.saveAll(temp10);
        tableDescriptionRepository.save(tableDescription);
        reportRepository.updateReportStatus(reportId);
        atom.decrementAndGet();
    }

}
