package cn.ahead.dcube.base.exception;

import cn.ahead.dcube.base.response.code.StatusCode;

/**
 * 
 * @desc:Service运行时异常
 * @date: 2022年11月28日 上午9:19:47<br>
 * @author:yangfei<br> 
 * @since 1.0.0
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
