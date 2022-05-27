package cn.dcube.ahead.commons.log.enums;

/**
 * 操作类型
 * 
 * @author yangfei
 *
 */
public enum OperationType {

	/**
	 * 其他
	 */
	OTHER("OTHER", 0),

	/**
	 * 新增
	 */
	ADD("ADD", 1),

	/**
	 * 修改
	 */
	UPDATE("UPDATE", 2),

	/**
	 * 删除
	 */
	DELETE("DELETE", 3),

	/**
	 * 查询
	 */
	QUERY("QUERY", 4),

	/**
	 * 授权
	 */
	GRANT("GRANT", 5),

	/**
	 * 导入
	 */
	IMPORT("IMPORT", 6),

	/**
	 * 导出
	 */
	EXPORT("EXPORT", 7),

	/**
	 * 登陆
	 */
	LOGIN("LOGIN", 98),

	/**
	 * 退出
	 */
	LOGOUT("LOGOUT", 99);

	private final String name;
	private final Integer code;

	private OperationType(String name, Integer code) {
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
