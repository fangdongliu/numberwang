package cn.fdongl.numberwangmrworker.repository;

import cn.fdongl.numberwangentity.entity.TableDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableDescriptionRepository extends JpaRepository<TableDescription,Long> {
}
