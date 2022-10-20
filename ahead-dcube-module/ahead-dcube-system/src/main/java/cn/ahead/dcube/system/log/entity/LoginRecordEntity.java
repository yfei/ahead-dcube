package cn.ahead.dcube.system.log.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.BeanUtils;

import cn.ahead.dcube.jpa.entity.IdentifierEntity;
import cn.ahead.dcube.security.log.dto.LoginRecordDTO;
import lombok.Data;

/**
 * 登陆相关日志
 * 
 * @author yangfei
 *
 */
@Entity
@Table(name = "tb_sys_login_record")
@Data
public class LoginRecordEntity extends IdentifierEntity {

	/** ID */
	private Long id;

	/** 用户账号 */
	@Column(name = "account")
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
	@Column(name = "location")
	private String location;

	/** 浏览器类型 */
	private String browser;

	/** 操作系统 */
	private String platform;

	/** 提示消息 */
	private String msg;

	/** 访问时间 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	public static LoginRecordEntity convert(LoginRecordDTO record) {
		LoginRecordEntity entity = new LoginRecordEntity();
		if (record != null) {
			BeanUtils.copyProperties(record, entity);
		}
		return entity;
	}
}
