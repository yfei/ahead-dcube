package cn.dcube.ahead.module.curd.repository;

import cn.dcube.ahead.module.curd.entity.UserEntity2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

/**
 * @date：2021-12-21 16:43<br>
 * @author：yangfei<br>
 * @version: v1.0
 */
public interface UserRepository extends JpaRepository<UserEntity2,Long> {

}
