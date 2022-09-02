package cn.ahead.dcube.security.log.service;

import cn.ahead.dcube.base.service.IService;
import cn.ahead.dcube.security.log.dto.LoginRecordDTO;

/**
 * login record service interface.
 * 
 * @author yangfei
 *
 */
public interface ILoginRecordService extends IService {

	/**
	 * save this info
	 * @param record
	 */
	public void save(LoginRecordDTO record);
	
	/**
	 * clean log by scheduled
	 */
	public void cleanScheduled();

}
