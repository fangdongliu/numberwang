package cn.fdongl.numberwangbackend.repository;

import cn.fdongl.numberwangentity.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {

    Report findByJobIdAndVersionGreaterThanAndStatusIsNot(Long jobId, Long version,Long status);

}
