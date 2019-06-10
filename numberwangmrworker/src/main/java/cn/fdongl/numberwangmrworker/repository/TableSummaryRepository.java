package cn.fdongl.numberwangmrworker.repository;

import cn.fdongl.numberwangentity.entity.TableSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableSummaryRepository extends JpaRepository<TableSummary,Long> {
}
