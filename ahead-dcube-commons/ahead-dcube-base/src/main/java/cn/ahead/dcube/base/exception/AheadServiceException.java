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
public class AheadServiceException extends AheadBaseException {

	public AheadServiceException() {
		super();
	}

	
	public AheadServiceException(StatusCode statusCode) {
		super(statusCode.getMsg());
		this.code = statusCode.getCode();
		this.msg = statusCode.getMsg();
	}

	/**
	 * @param message
	 */
	public AheadServiceException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public AheadServiceException(Throwable cause) {
		super(cause);
	}

}
