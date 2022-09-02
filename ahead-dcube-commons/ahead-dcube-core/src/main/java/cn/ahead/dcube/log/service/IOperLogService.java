package cn.ahead.dcube.log.service;

import cn.ahead.dcube.base.service.IService;
import cn.ahead.dcube.log.dto.OperLogDTO;

public interface IOperLogService extends IService {

	public void save(OperLogDTO log);

}
