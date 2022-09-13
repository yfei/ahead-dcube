package cn.ahead.dcube.system.dao;

import org.springframework.stereotype.Repository;

import cn.ahead.dcube.jpa.repository.AheadRepository;
import cn.ahead.dcube.system.entity.OrgEntity;

@Repository
public interface OrgRepository extends AheadRepository<OrgEntity, Long> {
	
	public OrgEntity findByCode(String code);

}
