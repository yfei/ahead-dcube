package cn.ahead.dcube.sns.weixin.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "wx.miniapp")
public class WxMaProperties {

	private List<Config> configs;

	@Data
	public static class Config {
		/**
		 * 设置微信小程序的appid
		 */
		private String appid;

		/**
		 * 设置微信小程序的Secret
		 */
		private String secret;

		/**
		 * 设置微信小程序消息服务器配置的token
		 */
		private String token;

		/**
		 * 设置微信小程序消息服务器配置的EncodingAESKey
		 */
		private String aesKey;

		/**
		 * 消息格式，XML或者JSON
		 */
		private String msgDataFormat;
		
		/**
		 * 商户号
		 */
		private String mchId;

	    /**
	     * 商户秘钥
	     */
		private String mchKeyV2;
		
		/**
	     * 商户秘钥
	     */
		private String mchKeyV3;
		
		private String payNotifyUrl;
		
		private String refundNotifyUrl;
		
		private String payP12Path;
		
		private String payCertPath;
		
		private String payKeyPath;
	}

}
