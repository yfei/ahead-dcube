package cn.ahead.dcube.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cn.ahead.dcube.base.exception.AheadRuntimeException;
import cn.ahead.dcube.base.response.Response;
import cn.ahead.dcube.base.response.code.ResponseCode;

/**
 * 
 * @desc:全局异常处理器。注意: 发生异常时,先通过@ExceptionHandler对异常进行封装,然后进入ExceptionAdvice
 * @date: 2022年11月28日 上午9:41:47<br>
 * @author:yangfei<br>
 * @since 1.0.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 异常处理
     * 
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Response handleException(Exception e) {
	log.error(e.getMessage(), e);
	return Response.error(ResponseCode.ERROR.getCode(), e.getMessage());
    }

    /**
     * 异常处理
     * 
     * @param e
     * @return
     */
    @ExceptionHandler(AheadRuntimeException.class)
    public Response handleAheadRuntimeException(AheadRuntimeException e) {
	log.error(e.getMessage(), e);
	if (e.getCode() != null) {
	    return Response.error(e.getCode(), e.getMsg());
	} else {
	    return Response.error(ResponseCode.ERROR.getCode(), e.getMessage());
	}
    }
}
