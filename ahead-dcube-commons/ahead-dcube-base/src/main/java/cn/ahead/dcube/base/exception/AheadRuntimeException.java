package cn.ahead.dcube.base.exception;

/**
 * 
 * 运行时异常
 * @author：yangfei<br> 
 * @date：2021年3月24日上午9:07:32
 * @since 1.0
 */
public class AheadRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	public AheadRuntimeException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public AheadRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AheadRuntimeException(String message, Throwable cause) {
		super(message, cause);
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
