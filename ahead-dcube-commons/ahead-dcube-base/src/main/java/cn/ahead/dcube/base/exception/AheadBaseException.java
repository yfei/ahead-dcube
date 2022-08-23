package cn.ahead.dcube.base.exception;

import cn.ahead.dcube.base.response.code.StatusCode;

/**
 * 
 * service运行时异常
 * 
 * @author：yangfei<br>
 * @date：2021年3月24日上午9:07:32
 * @since 1.0
 */
public class AheadBaseException extends AheadRuntimeException {

	public AheadBaseException() {
		super();
	}
	
	
	public AheadBaseException(StatusCode statusCode) {
		super(statusCode.getMsg());
		this.code = statusCode.getCode();
		this.msg = statusCode.getMsg();
	}

	/**
	 * @param message
	 */
	public AheadBaseException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public AheadBaseException(Throwable cause) {
		super(cause);
	}

}
