package cn.dcube.ahead.system.base.constant;

/**
 * 用户状态
 * 
 * @author yangfei
 *
 */
public enum UserSexEnum {

	// 男 male
	MALE(0),

	// 女 female
	FEMALE(1),

	// 未知/其他
	UNKOWN(2);

	private final int sex;

	private UserSexEnum(int sex) {
		this.sex = sex;
	}

	public int value() {
		return sex;
	}

}
