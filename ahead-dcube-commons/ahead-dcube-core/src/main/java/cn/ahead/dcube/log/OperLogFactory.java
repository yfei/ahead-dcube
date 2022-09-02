package cn.ahead.dcube.log;

import java.util.TimerTask;

import com.alibaba.fastjson.JSON;

import cn.ahead.dcube.context.SpringContext;
import cn.ahead.dcube.log.dto.OperLogDTO;
import cn.ahead.dcube.log.service.IOperLogService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OperLogFactory {

	public static TimerTask record(OperLogDTO operLog) {

		return new TimerTask() {
			@Override
			public void run() {
				// 插入数据
				IOperLogService service = SpringContext.getBean(IOperLogService.class);
				if (service != null) {
					service.save(operLog);
				} else {
					log.debug(JSON.toJSONString(operLog));
				}
			}
		};
	}

}
