package cn.fdongl.numberwangbackend.repository;

import cn.fdongl.numberwangentity.entity.LogFile;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<LogFile,Long> {

    List<LogFile>findByCreateBy(@Param("createBy") Long createBy, Pageable pageable);

    List<LogFile>findByCreateByAndAndFilenameLike(
            Long createBy,
            String filename,
            Pageable pageable);

    List<LogFile>findByCreateByAndCreateDateBetween(
            Long createBy,
            Date start, Date end,
            Pageable pageable);

    List<LogFile>findByCreateByAndFilenameLikeAndCreateDateBetween(
            Long createBy,
            String filename,
            Date start, Date end,
            Pageable pageable);

    LogFile findByCreateByAndAndId(@Param("createBy") Long createBy, @Param("id") Long id);

    void deleteLogFileByCreateByAndAndId(@Param("createBy") Long createBy, @Param("id") Long id);

    int countByCreateBy(Long createBy);
}
