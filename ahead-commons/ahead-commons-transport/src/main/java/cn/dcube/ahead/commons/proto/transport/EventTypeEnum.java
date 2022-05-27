package cn.dcube.ahead.commons.proto.transport;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 事件类型
 * @date: 2021-12-21 9:49 <br>
 * @author: yangfei <br>
 * @version: 1.0
 */
@Getter
public enum EventTypeEnum {
	EVENT("EVENT", "原始事件"),
	LOG("LOG", "操作日志"),
	CEPEVENT("CEPEVENT", "CEP事件"), 
	SNPEVENT("SNPEVENT", "SNP事件"), 
	WEPEVENT("WEPEVENT", "WEP事件"),
	CONFIG("CONFIG", "配置信息"),
	RULE("RULE", "规则"),
	HEART_BEAT("HEAR_BEAT", "心跳数据"),
	OTHER("OTHER", "其他");

	private String code;

	private String name;

	EventTypeEnum(String code,String name) {
		this.code = code;
		this.name = name;
	}
}
