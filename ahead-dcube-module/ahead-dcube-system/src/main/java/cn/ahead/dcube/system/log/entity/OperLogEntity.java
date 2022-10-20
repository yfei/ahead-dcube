package cn.ahead.dcube.system.log.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import cn.ahead.dcube.jpa.entity.IdentifierEntity;
import cn.ahead.dcube.log.dto.OperLogDTO;
import lombok.Data;

/**
 * 操作日志记录表 oper_log
 * 
 * @author ruoyi
 */
@Entity
@Table(name = "tb_sys_oper_log")
@Data
public class OperLogEntity extends IdentifierEntity {

	/** 操作模块 */
	private String module;

	@Column(name = "function_name")
	private String functionName;

	/** 请求方式 */
	private String method;

	/** 是否定时任务 0 请求响应 1 定时任务 2 初始化任务 */
	@Column(name = "call_by")
	private String callby;

	/** 请求url */
	private String url;

	/** 业务类型（0其它 1新增 2修改 3删除） */
	private String oper;

	/** 业务类型数组 */
	private String[] opers;

	/** 请求的程序方法 */
	@Column(name = "function_method", columnDefinition = "TEXT")
	private String functionMethod;

	/** 请求参数 */
	@Column(name = "request_param", columnDefinition = "TEXT")
	private String requestParam;

	/** 返回参数 */
	@Column(name = "reponse_result", columnDefinition = "TEXT")
	private String responseResult;

	/** 操作状态（0正常 1异常） */
	private Integer status;

	/** 错误消息 */
	@Column(name = "error_msg", columnDefinition = "TEXT")
	private String errorMsg;

	/** 操作时间 */
	@Column(name = "oper_time")
	private Date operTime;

	/** 操作人员 */
	@Column(name = "oper_user")
	private String operUser;

	/** 部门名称 */
	@Column(name = "oper_org")
	private String operOrg;

	/** 操作地址 */
	@Column(name = "oper_ip")
	private String operIp;

	/** 操作地点 */
	@Column(name = "oper_location")
	private String operLocation;

	/** 操作者的系统 */
	@Column(name = "oper_os")
	private String operOS;

	/** 操作者的浏览器 */
	@Column(name = "oper_brower")
	private String operBrower;

	public static OperLogEntity convert(OperLogDTO log) {
		OperLogEntity entity = new OperLogEntity();
		if (log != null) {
			BeanUtils.copyProperties(log, entity);
		}
		return entity;
	}
}
