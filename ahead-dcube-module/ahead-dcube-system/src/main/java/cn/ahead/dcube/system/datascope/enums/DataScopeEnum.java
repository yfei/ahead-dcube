package cn.ahead.dcube.system.datascope.enums;

public enum DataScopeEnum {

	OWNER("本用户", 0b0001), ORG("同组织", 0b0011), ORG_CASCADE("父组织", 0b0111), ALL("所有", 0b1111);

	private String name;

	private int value;

	private DataScopeEnum(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
