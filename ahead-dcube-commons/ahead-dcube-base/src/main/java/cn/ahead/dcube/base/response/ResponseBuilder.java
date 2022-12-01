package cn.ahead.dcube.base.response;

/**
 * 
 * @desc: ResponseBuilder.已弃用
 * @date: 2022年11月28日 上午9:21:28<br>
 * @author:yangfei<br> 
 * @since 1.0.0
 */
@Deprecated
public class ResponseBuilder {

    private Response response;

    public ResponseBuilder() {
	response = new Response();
    }

    public ResponseBuilder setStatus(boolean status) {
	response.setSuccess(status);
	return this;
    }

    public ResponseBuilder setMessage(String message) {
	response.setMessage(message);
	return this;
    }

    public ResponseBuilder setData(Object data) {
	response.setData(data);
	return this;
    }

    public ResponseBuilder setCode(Integer code) {
	response.setCode(code);
	return this;
    }

    public Response build() {
	return response;
    }

}
