package cn.ahead.dcube.log.dto;

import java.util.Date;

import cn.ahead.dcube.base.dto.IDTO;
import lombok.Data;

/**
 * 操作日志记录表 oper_log
 * 
 * @author ruoyi
 */
@Data
public class OperLogDTO implements IDTO {

	/** 操作模块 */
	private String module;

	/** 请求的程序方法 */
	private String functionName;

	/** 请求方式 */
	private String method;

	/** 是否定时任务 0 请求响应 1 定时任务 2 初始化任务 */
	private String callby;

	/** 请求url */
	private String url;

	/** 业务类型（0其它 1新增 2修改 3删除） */
	private String oper;

	/** 业务类型数组 */
	private String[] opers;

	/** 请求的程序方法 */
	private String functionMethod;

	/** 请求参数 */
	private String requestParam;

	/** 返回参数 */
	private String responseResult;

	/** 操作状态（0正常 1异常） */
	private Integer status;

	/** 错误消息 */
	private String errorMsg;

	/** 操作时间 */
	private Date operTime;

	/** 操作人员 */
	private String operUser;

	/** 部门名称 */
	private String operOrg;

	/** 操作地址 */
	private String operIp;

	/** 操作地点 */
	private String operLocation;

	/** 操作者的系统 */
	private String operOS;

	/** 操作者的浏览器 */
	private String operBrower;
}
