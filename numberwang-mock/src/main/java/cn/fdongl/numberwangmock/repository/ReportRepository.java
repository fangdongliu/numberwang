package cn.fdongl.numberwangmock.repository;

import cn.fdongl.numberwangentity.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {

    Report findByJobIdAndVersionGreaterThan(Long jobId,Long version);

}
