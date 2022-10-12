package cn.ahead.dcube.sns.weixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.ahead.dcube.base.response.Response;
import cn.ahead.dcube.sns.dto.WxUserInfo;
import cn.ahead.dcube.sns.weixin.service.IWxUserService;

/**
 * @since 2022/7/27 22:44
 */
@RestController
@RequestMapping("/sns/v1/wx")
public class WxLoginController {
	
	@Autowired
    private IWxUserService userInfoService;

    /**
     * 登陆接口
     */
    @GetMapping("/login")
    public Response login(@RequestParam("code") String code) {
        return userInfoService.login(code);
    }

    /**
     * <pre>
     * 获取用户信息接口
     * </pre>
     */
    @PostMapping("/getUserInfo")
    public Response getUserInfo(@RequestBody WxUserInfo userInfo) {
        return userInfoService.getUserInfo(userInfo);
    }
}
