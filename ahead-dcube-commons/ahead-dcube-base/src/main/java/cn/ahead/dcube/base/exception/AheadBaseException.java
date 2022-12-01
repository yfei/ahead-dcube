package cn.ahead.dcube.base.exception;

import cn.ahead.dcube.base.response.code.StatusCode;

/**
 * 
 * @desc:异常Base类
 * @date: 2022年11月28日 上午9:19:25<br>
 * @author:yangfei<br> 
 * @since 1.0.0
 */
public class AheadBaseException extends AheadRuntimeException {

	private static final long serialVersionUID = -4354966462710846311L;

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
