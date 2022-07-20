package cn.dcube.ahead.response;

public class Response {

	/**
	 * 返回状态
	 */
	private boolean success = true;
	
	/**
	 * 数据,如果success为true,此项需要有值
	 */
	private Object data;

	/**
	 * 错误信息,如果success为true,此项没有值
	 */
	private String message;

	public Response() {
		super();
	}

	public Response(boolean success) {
		super();
		this.success = success;
	}

	public Response(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public Response(boolean success, String message, Object responseBody) {
		super();
		this.success = success;
		this.message = message;
		this.data = responseBody;
	}

	public Response(boolean success, Object responseBody) {
		super();
		this.success = success;
		this.data = responseBody;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
