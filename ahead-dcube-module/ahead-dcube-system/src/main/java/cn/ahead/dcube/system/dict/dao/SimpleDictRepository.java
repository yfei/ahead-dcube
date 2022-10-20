package cn.ahead.dcube.system.dict.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import cn.ahead.dcube.jpa.repository.AheadRepository;
import cn.ahead.dcube.system.dict.entity.SimpleDictEntity;

@Repository
public interface SimpleDictRepository extends AheadRepository<SimpleDictEntity, Long>,JpaSpecificationExecutor<SimpleDictEntity> {
	
}
