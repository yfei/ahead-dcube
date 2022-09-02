package cn.ahead.dcube.system.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ahead.dcube.security.log.dto.LoginRecordDTO;
import cn.ahead.dcube.security.log.service.ILoginRecordService;
import cn.ahead.dcube.system.dao.LoginRecordRepository;
import cn.ahead.dcube.system.entity.LoginRecordEntity;

@Service
public class LoginRecordServiceImpl implements ILoginRecordService {

	@Autowired
	private LoginRecordRepository repository;

	@Transactional
	@Override
	public void save(LoginRecordDTO record) {
		LoginRecordEntity entity = LoginRecordEntity.convert(record);
		repository.save(entity);
	}

	@Override
	public void cleanScheduled() {
		// TODO Auto-generated method stub

	}

}
