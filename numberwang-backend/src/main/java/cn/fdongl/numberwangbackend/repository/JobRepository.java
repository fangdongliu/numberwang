package cn.fdongl.numberwangbackend.repository;

import cn.fdongl.numberwangentity.entity.Job;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {

    Job findByIdAndCreateBy(Long id, Long createBy);

    List<Job> findByCreateBy(Long createBy, Pageable pageable);

    @Transactional
    @Query(value = "update job set `status` = ?1 where id = ?2 and `status` = ?3",nativeQuery = true)
    @Modifying
    int updateJobStatus(Long statusNew, Long id, Long statusLast);

}
