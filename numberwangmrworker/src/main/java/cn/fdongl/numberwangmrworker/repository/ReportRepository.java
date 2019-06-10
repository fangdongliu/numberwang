package cn.fdongl.numberwangmrworker.repository;

import cn.fdongl.numberwangentity.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {

    Report findByJobIdAndVersionGreaterThanAndStatusGreaterThan(Long jobId, Long version, Long status);

    @Transactional
    @Query(value = "update report set `version` = `version`+1 where id = ?1",nativeQuery = true)
    @Modifying
    int updateReportStatus(Long id);

}
