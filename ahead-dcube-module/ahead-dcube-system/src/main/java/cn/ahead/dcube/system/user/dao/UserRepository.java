package cn.ahead.dcube.system.user.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.ahead.dcube.jpa.repository.AheadRepository;
import cn.ahead.dcube.system.user.entity.UserEntity;

@Repository
public interface UserRepository extends AheadRepository<UserEntity, Long> {

	public UserEntity findByAccount(String account);

	/**
	 * 更新密码
	 * 
	 * @param id
	 * @param passwd
	 */
	@Modifying
	@Query(value = "update UserEntity u set u.password=:passwd where u.id=:id")
	public void chpass(@Param(value = "id") Long id, @Param(value = "passwd") String passwd);
	
}
