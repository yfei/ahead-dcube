package cn.ahead.dcube.sns.weixin.service;

import java.util.List;

import cn.ahead.dcube.base.response.Response;
import cn.ahead.dcube.sns.dto.WxUserInfo;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage.MsgData;

/**
 * @since 2022/7/27 22:47
 */
public interface IWechatService {

	/**
	 * 登录
	 * 
	 * @param code
	 *            code
	 * @return WxMaJscode2SessionResult
	 */
	Response login(String code);

	/**
	 * 获取用户信息
	 * 
	 * @param userInfo
	 *            包含一些加密的信息
	 * @return WxMaUserInfo
	 */
	Response getUserInfo(WxUserInfo userInfo);

	/**
	 * 获取微信手机号
	 * 
	 * @param code
	 * @return
	 */
	public WxMaPhoneNumberInfo getPhoneNumber(String code);

	/**
	 * 发送模板消息
	 * @param datas
	 * @param toUser
	 * @param templateId
	 * @param page
	 */
	public void sendSubscribeMsg(List<MsgData> datas, String toUser, String templateId, String page);
}
