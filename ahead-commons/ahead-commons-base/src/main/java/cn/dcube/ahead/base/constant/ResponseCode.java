package cn.dcube.ahead.base.constant;

/**
 * 响应代码
 * 
 * @author yangfei
 *
 */
public enum ResponseCode {

	// 标准http响应
	SUCCESS(200),

	ERROR(500),

	UNAUTHORIZED(401),

	// 业务响应
	PARAM_ERROR(1000),

	// 没有token
	NO_TOKEN(1001),

	// 没有登录
	NO_LOGIN(1002),

	// 没有用户
	NO_USER(1003),

	// 密码错误
	PASSWD_ERROR(1004),

	// 用户被禁用
	USER_DISABLED(1005),

	// 用户已登录
	USER_LOGIN_ALREADY(1006),

	// 积分不足
	NO_ENOUGH_SCORE(1007),

	// 邮箱已注册
	EMAIL_REGISTER_ALREADY(1008),

	// 时间异常 比如重置密码时间间隔太近
	TIME_ERROR(1009),

	// 没有权限
	USER_NO_PRIVILEGE(1010);

	private final int code;

	private ResponseCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
}
