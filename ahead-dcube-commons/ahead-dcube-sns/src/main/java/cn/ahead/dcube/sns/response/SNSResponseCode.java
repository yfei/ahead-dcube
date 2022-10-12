package cn.ahead.dcube.sns.response;

import cn.ahead.dcube.base.response.code.StatusCode;
import lombok.Getter;

/**
 * 授权代码
 * 
 * @author yangfei
 *
 */
@Getter
public enum SNSResponseCode implements StatusCode {

	UNAUTHORIZED(401, "认证失败!");

	private int code;

	private String msg;

	SNSResponseCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
