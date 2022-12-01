package cn.ahead.dcube.base.exception;

import cn.ahead.dcube.base.response.code.StatusCode;
import lombok.Getter;

/**
 * 
 * @desc: 运行时异常
 * @date: 2022年11月28日 上午9:19:36<br>
 * @author:yangfei<br> 
 * @since 1.0.0
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
