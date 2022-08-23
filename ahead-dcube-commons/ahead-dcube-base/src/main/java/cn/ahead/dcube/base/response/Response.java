package cn.ahead.dcube.base.response;

import cn.ahead.dcube.base.response.code.ResponseCode;
import cn.ahead.dcube.base.response.code.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

	/**
	 * 返回状态
	 */
	private boolean success = true;
	/**
	 * 响应代码
	 */
	private Integer code;

	/**
	 * 数据,如果success为true,此项需要有值
	 */
	private Object data;

	/**
	 * 错误信息,如果success为true,此项没有值
	 */
	private String message;

	protected Response(boolean success, String message) {
		this.success = success;
		if (this.success) {
			this.code = ResponseCode.SUCCESS.getCode();
		} else {
			this.code = ResponseCode.ERROR.getCode();
		}
		this.message = message;
	}

	protected Response(boolean success, StatusCode status, Object data) {
		this.success = success;
		this.code = status.getCode();
		this.message = status.getMsg();
		this.data = data;
	}

	public static Response error(StatusCode status) {
		return new ErrorResponse(status);
	}

	public static Response error(String message) {
		return new ErrorResponse(message);
	}

	public static Response error(Integer code, String message) {
		return new ErrorResponse(code, message);
	}

	public static Response success(Object data) {
		return new SuccessResponse(data);
	}
	
	public static Response success() {
		return new SuccessResponse();
	}

}
