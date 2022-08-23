package cn.ahead.dcube.security.exception;

import cn.ahead.dcube.base.exception.AheadBaseException;
import cn.ahead.dcube.base.response.code.StatusCode;

public class CaptchaException extends AheadBaseException {

	public CaptchaException() {
		super();
	}
	
	public CaptchaException(StatusCode statusCode) {
		super(statusCode.getMsg());
		this.code = statusCode.getCode();
		this.msg = statusCode.getMsg();
	}
	/**
	 * @param message
	 */
	public CaptchaException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public CaptchaException(Throwable cause) {
		super(cause);
	}

}
