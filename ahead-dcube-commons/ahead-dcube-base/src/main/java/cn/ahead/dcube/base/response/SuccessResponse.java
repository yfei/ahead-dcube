package cn.ahead.dcube.base.response;

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

	public SuccessResponse() {
		super(true, ResponseCode.SUCCESS, null, null);
	}

	public SuccessResponse(Object object) {
		super(true, ResponseCode.SUCCESS, object, null);
	}

	public SuccessResponse(Integer code, Object object, String message) {
		super(true, code, object, message);
	}

}
