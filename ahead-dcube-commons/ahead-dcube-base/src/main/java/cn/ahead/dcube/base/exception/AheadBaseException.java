package cn.ahead.dcube.base.exception;

import cn.ahead.dcube.base.response.code.StatusCode;

/**
 * 
 * 异常Base类
 * 
 * @author：yangfei<br>
 * @date：2021年3月24日上午9:07:32
 * @since 1.0
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
