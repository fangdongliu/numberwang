package cn.fdongl.numberwangbackend.repository;

import cn.fdongl.numberwangentity.entity.TableDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableDescriptionRepository extends JpaRepository<TableDescription,Long> {

    List<TableDescription> findByReportId(Long reportId);

}
