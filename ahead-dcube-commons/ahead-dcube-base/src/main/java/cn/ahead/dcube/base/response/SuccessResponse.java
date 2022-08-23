package cn.ahead.dcube.base.response;

import cn.ahead.dcube.base.response.code.ResponseCode;
import cn.ahead.dcube.base.response.code.StatusCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 成功响应结果
 * @author yangfei
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SuccessResponse extends Response {

	protected SuccessResponse() {
		super(true, ResponseCode.SUCCESS.getCode(), null, null);
	}
	
	protected SuccessResponse(boolean success, StatusCode status, Object data) {
		super(success, status, data);
	}

	protected SuccessResponse(Object object) {
		super(true, ResponseCode.SUCCESS.getCode(), object, null);
	}

	protected SuccessResponse(Integer code, Object object, String message) {
		super(true, code, object, message);
	}

}
