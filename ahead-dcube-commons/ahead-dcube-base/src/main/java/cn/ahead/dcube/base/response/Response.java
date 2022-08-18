package cn.ahead.dcube.base.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

	/**
	 * 返回状态
	 */
	private boolean success = true;
	/**
	 * 响应代码
	 */
	private Integer code;

	/**
	 * 数据,如果success为true,此项需要有值
	 */
	private Object data;

	/**
	 * 错误信息,如果success为true,此项没有值
	 */
	private String message;
	
	
	public Response(boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	
}
