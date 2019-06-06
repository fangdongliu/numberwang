package cn.fdongl.numberwangbackend.repository;

import cn.fdongl.numberwangentity.entity.Tables;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TablesRepository extends JpaRepository<Tables,Long> {

    List<Tables>findByReportIdAndTableName(Long reportId, String tableName, Pageable pageable);

}

