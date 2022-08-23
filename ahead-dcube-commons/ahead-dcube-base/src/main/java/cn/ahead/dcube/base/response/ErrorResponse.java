package cn.ahead.dcube.base.response;

import cn.ahead.dcube.base.response.code.ResponseCode;
import cn.ahead.dcube.base.response.code.StatusCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 失败响应结果
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ErrorResponse extends Response {
	
	protected ErrorResponse(String message) {
		super(false, ResponseCode.ERROR.getCode(), null, message);
	}
	
	protected ErrorResponse(StatusCode status) {
		super(false, status.getCode(), null, status.getMsg());
	}

	protected ErrorResponse(boolean success, StatusCode status, Object data) {
		super(success, status, data);
	}

	protected ErrorResponse(Integer code, String message) {
		super(false, code, null, message);
	}

	protected ErrorResponse(Integer code, Object object, String message) {
		super(false, code, object, message);
	}
}
