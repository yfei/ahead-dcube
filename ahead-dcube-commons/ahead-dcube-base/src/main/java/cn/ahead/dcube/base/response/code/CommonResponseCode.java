package cn.ahead.dcube.base.response.code;

import lombok.Getter;

/**
 * 授权代码.1000+为security错误
 * 
 * @author yangfei
 *
 */
@Getter
public enum CommonResponseCode implements StatusCode {

	PARAMETERERROR(1100, "参数异常!");

	private int code;

	private String msg;

	CommonResponseCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
