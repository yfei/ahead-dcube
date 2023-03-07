package cn.ahead.dcube.system.datascope.service;

import cn.ahead.dcube.base.service.IService;

public interface IDataScopeService extends IService {

	// 是否当前
	public boolean onlyOwner(String module);

}
