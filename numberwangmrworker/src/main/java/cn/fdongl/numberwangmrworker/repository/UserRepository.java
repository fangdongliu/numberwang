package cn.fdongl.numberwangmrworker.repository;

import cn.fdongl.numberwangentity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(@Param("username") String username);

}
