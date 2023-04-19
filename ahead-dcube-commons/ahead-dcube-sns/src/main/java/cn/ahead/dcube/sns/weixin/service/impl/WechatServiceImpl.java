package cn.ahead.dcube.sns.weixin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ahead.dcube.base.exception.AheadServiceException;
import cn.ahead.dcube.base.response.Response;
import cn.ahead.dcube.sns.dto.WxUserInfo;
import cn.ahead.dcube.sns.weixin.service.IWechatService;
import cn.binarywang.wx.miniapp.api.WxMaMsgService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage.MsgData;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage.WxMaSubscribeMessageBuilder;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.binarywang.wx.miniapp.util.WxMaConfigHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * @since 2022/7/27 22:48
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class WechatServiceImpl implements IWechatService {

	private final WxMaService wxMaService;


	/**
	 * 登录
	 * 
	 * @param code
	 *            code
	 * @return WxMaJscode2SessionResult
	 */
	@Override
	public Response login(String code) {
		try {
			WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
			log.info(session.getSessionKey());
			log.info(session.getOpenid());
			// TODO 可以增加自己的逻辑，关联业务相关数据
			return Response.success(session);
		} catch (WxErrorException e) {
			log.error(e.getMessage(), e);
			return Response.error(e.toString());
		} finally {
			WxMaConfigHolder.remove();// 清理ThreadLocal
		}
	}

	@Override
	public Response getUserInfo(WxUserInfo userInfo) {

		// 用户信息校验
		if (!wxMaService.getUserService().checkUserInfo(userInfo.getSessionKey(), userInfo.getRawData(),
		        userInfo.getSignature())) {
			WxMaConfigHolder.remove();// 清理ThreadLocal
			return Response.error("user check failed");
		}
		// 解密用户信息
		WxMaUserInfo wxMaUserInfo = wxMaService.getUserService().getUserInfo(userInfo.getSessionKey(),
		        userInfo.getEncryptedData(), userInfo.getIv());
		WxMaConfigHolder.remove();// 清理ThreadLocal
		return Response.success(wxMaUserInfo);
	}

	@Override
	public WxMaPhoneNumberInfo getPhoneNumber(String code) {
		// 解密用户信息
		WxMaPhoneNumberInfo wxMaPhoneInfo;
		try {
			wxMaPhoneInfo = wxMaService.getUserService().getNewPhoneNoInfo(code);
		} catch (WxErrorException e) {
			throw new AheadServiceException(e);
		}
		WxMaConfigHolder.remove();// 清理ThreadLocal
		return wxMaPhoneInfo;
	}

	public void sendSubscribeMsg(List<MsgData> datas, String toUser, String templateId, String page) {
		log.info("send SubscribeMsg to user {}, templateId is {}", toUser, templateId);
		WxMaSubscribeMessageBuilder message = WxMaSubscribeMessage.builder();
		message.toUser(toUser);
		message.page(page);
		message.data(datas);
		message.templateId(templateId);
		try {
			wxMaService.getMsgService().sendSubscribeMsg(message.build());
		} catch (Exception e) {
			log.error("", e);
			throw new AheadServiceException(e);
		}
	}

}
