package cn.ahead.dcube.base.response.code;

import lombok.Getter;

/**
 * 授权代码.1000+为security错误
 * 
 * @author yangfei
 *
 */
@Getter
public enum SecurityResponseCode implements StatusCode {

	UNAUTHORIZED(401, "认证失败!"),

	USERNOTFOUND(1000, "用户或者密码错误!"),

	PASSERROR(1001, "密码错误!"),

	CAPTCHAERROR(1002, "验证码不正确!"),

	CAPTCHATIMEOUT(1003, "验证码过期!");

	private int code;

	private String msg;

	SecurityResponseCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
