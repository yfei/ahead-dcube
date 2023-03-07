package cn.ahead.dcube.system.datascope.dao;

import org.springframework.stereotype.Repository;

import cn.ahead.dcube.jpa.repository.AheadRepository;
import cn.ahead.dcube.system.datascope.entity.DataScopeEntity;

@Repository
public interface DataScopeRepository extends AheadRepository<DataScopeEntity, Long> {

}
