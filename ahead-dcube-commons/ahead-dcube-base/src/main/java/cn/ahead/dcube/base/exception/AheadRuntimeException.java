package cn.ahead.dcube.base.exception;

import cn.ahead.dcube.base.response.code.StatusCode;
import lombok.Getter;

/**
 * 
 * 运行时异常
 * 
 * @author：yangfei<br>
 * @date：2021年3月24日上午9:07:32
 * @since 1.0
 */
@Getter
public class AheadRuntimeException extends RuntimeException {
	
	protected Integer code;
	
	protected String msg;
	

	public AheadRuntimeException() {
		super();
	}
	
	
	public AheadRuntimeException(StatusCode statusCode) {
		super(statusCode.getMsg());
		this.code = statusCode.getCode();
		this.msg = statusCode.getMsg();
	}


	/**
	 * @param message
	 */
	public AheadRuntimeException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public AheadRuntimeException(Throwable cause) {
		super(cause);
	}

}
