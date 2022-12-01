package cn.ahead.dcube.base.response;

import cn.ahead.dcube.base.response.code.ResponseCode;
import cn.ahead.dcube.base.response.code.StatusCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @desc: SuccessResponse
 * @date: 2022年11月28日 上午9:22:13<br>
 * @author:yangfei<br> 
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SuccessResponse extends Response {

    protected SuccessResponse() {
	super(true, ResponseCode.SUCCESS.getCode(), null, null);
    }

    protected SuccessResponse(boolean success, StatusCode status, Object data) {
	super(success, status, data);
    }

    protected SuccessResponse(Object object) {
	super(true, ResponseCode.SUCCESS.getCode(), object, null);
    }

    protected SuccessResponse(Integer code, Object object, String message) {
	super(true, code, object, message);
    }

}
