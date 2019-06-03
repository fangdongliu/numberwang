package cn.fdongl.numberwangmock.repository;

import cn.fdongl.numberwangentity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 这个接口的作用在于提供SQL查询
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(@Param("username") String username);

}
