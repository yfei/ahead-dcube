package cn.ahead.dcube.security.log.dto;

import java.util.Date;

import cn.ahead.dcube.base.dto.IDTO;
import lombok.Data;

@Data
public class LoginRecordDTO implements IDTO{

	/** ID */
	private Long id;

	/** 用户账号 */
	private String userName;

	/** LOGIN/LOGOUT */
	private String oper;

	/** 登录状态 0成功 1失败 */
	private String status;
	
	/** 响应代码 */
	private Integer code;

	/** 登录IP地址 */
	private String ipaddr;

	/** 登录地点 */
	private String location;

	/** 浏览器类型 */
	private String browser;

	/** 操作系统 */
	private String platform;

	/** 提示消息 */
	private String msg;

	/** 访问时间 */
	private Date time;

}
