package cn.ahead.dcube.base.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 失败响应结果
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ErrorResponse extends Response {

	public ErrorResponse(String message) {
		super(false, ResponseCode.ERROR, null, message);
	}

	public ErrorResponse(Integer code, String message) {
		super(false, code, null, message);
	}

	ErrorResponse(Integer code, Object object, String message) {
		super(false, code, object, message);
	}
}
