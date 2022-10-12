package cn.ahead.dcube.sns.weixin.service;

import cn.ahead.dcube.base.response.Response;
import cn.ahead.dcube.sns.dto.WxUserInfo;

/**
 * @since 2022/7/27 22:47
 */
public interface IWxUserService {

    /**
     * 登录
     * @param code code
     * @return   WxMaJscode2SessionResult
     */
    Response login(String code);

    /**
     * 获取用户信息
     * @param userInfo  包含一些加密的信息
     * @return  WxMaUserInfo
     */
    Response getUserInfo(WxUserInfo userInfo);
}


