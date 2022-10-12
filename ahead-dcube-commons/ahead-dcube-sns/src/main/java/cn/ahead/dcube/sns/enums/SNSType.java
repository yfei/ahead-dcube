package cn.ahead.dcube.sns.enums;

import lombok.Getter;

@Getter
public enum SNSType {

	WEIXIN("WEIXIN", "微信"),

	QQ("QQ", "微信"),

	ALIPAY("ALIPAY", "支付宝");

	private String code;

	private String name;

	SNSType(String code, String name) {
		this.code = code;
		this.name = name;
	}
}
