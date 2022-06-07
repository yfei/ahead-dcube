package cn.dcube.ahead.jpa.fei.query.filter.exception;


import org.springframework.core.NestedRuntimeException;

/**
 * 
 * 
 * @author：yangfei<br> 
 * @date：2021年3月24日上午11:21:14
 * @since 1.0
 */
public class InvalidFilterException extends NestedRuntimeException {

	/**
	 * @param msg
	 * @param cause
	 */
	public InvalidFilterException(String msg, Throwable cause) {
		super(msg, cause);
	}

	/**
	 * @param msg
	 */
	public InvalidFilterException(String msg) {
		super(msg);
	}

}
