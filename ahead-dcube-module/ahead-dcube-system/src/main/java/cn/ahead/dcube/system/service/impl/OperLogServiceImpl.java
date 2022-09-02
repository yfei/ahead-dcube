package cn.ahead.dcube.system.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ahead.dcube.log.dto.OperLogDTO;
import cn.ahead.dcube.log.service.IOperLogService;
import cn.ahead.dcube.system.dao.LoginRecordRepository;
import cn.ahead.dcube.system.dao.OperLogRepository;
import cn.ahead.dcube.system.entity.LoginRecordEntity;
import cn.ahead.dcube.system.entity.OperLogEntity;

@Service
public class OperLogServiceImpl implements IOperLogService {

	@Autowired
	private OperLogRepository repository;

	@Transactional
	@Override
	public void save(OperLogDTO log) {
		OperLogEntity entity = OperLogEntity.convert(log);
		repository.save(entity);
	}

}
