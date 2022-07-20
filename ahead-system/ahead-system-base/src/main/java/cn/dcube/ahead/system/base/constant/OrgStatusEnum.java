package cn.dcube.ahead.system.base.constant;

/**
 * 用户状态
 * 
 * @author yangfei
 *
 */
public enum OrgStatusEnum {

	// 正常
	NORMAL(0),

	// 禁用
	DISABLED(1),
	
	// 删除
	DELETED(99);

	private final int status;

	private OrgStatusEnum(int status) {
		this.status = status;
	}

	public int value() {
		return status;
	}

}
