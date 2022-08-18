package cn.ahead.dcube.base.exception;

/**
 * 
 * service运行时异常
 * 
 * @author：yangfei<br>
 * @date：2021年3月24日上午9:07:32
 * @since 1.0
 */
public class AheadServiceException extends AheadRuntimeException {

	/**
	 * 
	 */
	public AheadServiceException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public AheadServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AheadServiceException(String message, Throwable cause) {
		super(message, cause);
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
