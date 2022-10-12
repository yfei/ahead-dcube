package cn.ahead.dcube.sns.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WxUserInfo implements Serializable {
	private String appid;
	private String sessionKey;
	/**
	 * 签名信息
	 */
	private String signature;
	/**
	 * 非敏感的用户信息
	 */
	private String rawData;
	/**
	 * 加密的数据
	 */
	private String encryptedData;
	/**
	 * 加密密钥
	 */
	private String iv;
}
