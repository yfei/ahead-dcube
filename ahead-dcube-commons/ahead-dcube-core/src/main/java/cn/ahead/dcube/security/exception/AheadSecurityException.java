package cn.ahead.dcube.security.exception;

import cn.ahead.dcube.base.exception.AheadBaseException;
import cn.ahead.dcube.base.response.code.StatusCode;

public class AheadSecurityException extends AheadBaseException {

	public AheadSecurityException() {
		super();
	}
	
	public AheadSecurityException(StatusCode statusCode) {
		super(statusCode.getMsg());
		this.code = statusCode.getCode();
		this.msg = statusCode.getMsg();
	}
	/**
	 * @param message
	 */
	public AheadSecurityException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public AheadSecurityException(Throwable cause) {
		super(cause);
	}

}
