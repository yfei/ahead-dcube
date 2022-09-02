package cn.ahead.dcube.system.dao;

import org.springframework.stereotype.Repository;

import cn.ahead.dcube.jpa.repository.AheadRepository;
import cn.ahead.dcube.system.entity.OperLogEntity;

@Repository
public interface OperLogRepository extends AheadRepository<OperLogEntity, Long> {

}
