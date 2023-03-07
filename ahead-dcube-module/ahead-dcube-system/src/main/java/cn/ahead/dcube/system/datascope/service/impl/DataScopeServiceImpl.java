package cn.ahead.dcube.system.datascope.service.impl;

import org.springframework.stereotype.Service;

import cn.ahead.dcube.system.datascope.entity.DataScopeEntity;
import cn.ahead.dcube.system.datascope.service.IDataScopeService;

@Service
public class DataScopeServiceImpl implements IDataScopeService{

	@Override
	public boolean onlyOwner(String module) {
		// TODO Auto-generated method stub
		return false;
	}


}
