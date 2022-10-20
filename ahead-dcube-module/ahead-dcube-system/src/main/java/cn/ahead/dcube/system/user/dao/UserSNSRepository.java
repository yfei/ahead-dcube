package cn.ahead.dcube.system.user.dao;

import org.springframework.stereotype.Repository;

import cn.ahead.dcube.jpa.repository.AheadRepository;
import cn.ahead.dcube.system.user.entity.UserSNSEntity;

@Repository
public interface UserSNSRepository extends AheadRepository<UserSNSEntity, Long> {

	/**
	 * 根据type和unionid查找userSNS
	 * @param type
	 * @param unionid
	 * @return
	 */
	public UserSNSEntity findByTypeAndUnionid(String type,String unionid);
	
}
