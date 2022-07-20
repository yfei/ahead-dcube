package cn.dcube.ahead.system.base.constant;

/**
 * 用户状态
 * 
 * @author yangfei
 *
 */
public enum UserStatusEnum {

	// 正常
	NORMAL(0),

	// 禁用
	DISABLED(1),
	
	// 锁定,错误次数过多导致锁定
	LOCKED(2),

	// 删除
	DELETED(99);

	private final int status;

	private UserStatusEnum(int status) {
		this.status = status;
	}

	public int value() {
		return status;
	}

}
