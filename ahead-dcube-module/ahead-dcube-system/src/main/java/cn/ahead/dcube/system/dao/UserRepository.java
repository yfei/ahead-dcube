package cn.ahead.dcube.system.dao;

import org.springframework.stereotype.Repository;

import cn.ahead.dcube.jpa.repository.AheadRepository;
import cn.ahead.dcube.system.entity.UserEntity;

@Repository
public interface UserRepository extends AheadRepository<UserEntity, Long> {

	public UserEntity findByAccount(String account);

}
