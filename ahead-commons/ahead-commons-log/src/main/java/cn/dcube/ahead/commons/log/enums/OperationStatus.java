package cn.dcube.ahead.commons.log.enums;

/**
 * 操作返回状态 0 成功  1 失败
 * 
 * @author yangfei
 *
 */
public enum OperationStatus {
	/**
	 * 成功
	 */
	SUCCESS("SUCCESS", 0),

	/**
	 * 失败
	 */
	FAILURE("FAILURE", 1);

	private final String name;

	private final Integer code;

	private OperationStatus(String name, Integer code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public Integer getCode() {
		return code;
	}
}
