package cn.fdongl.numberwangbackend.repository;

import cn.fdongl.numberwangentity.entity.Report;
import cn.fdongl.numberwangentity.entity.TableSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableSummaryRepository extends JpaRepository<TableSummary,Long> {

    List<TableSummary>findByReportId(Long reportId);

}
