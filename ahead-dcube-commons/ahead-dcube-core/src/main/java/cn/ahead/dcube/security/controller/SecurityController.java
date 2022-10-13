package cn.ahead.dcube.security.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.ahead.dcube.base.constant.AheadSysConstant;
import cn.ahead.dcube.base.response.ErrorResponse;
import cn.ahead.dcube.base.response.Response;
import cn.ahead.dcube.base.response.SuccessResponse;
import cn.ahead.dcube.base.response.code.ResponseCode;
import cn.ahead.dcube.base.response.code.SecurityResponseCode;
import cn.ahead.dcube.security.config.CaptchaConfig;
import cn.ahead.dcube.security.log.LoginRecordFactory;
import cn.ahead.dcube.security.service.IUserSecurityService;
import cn.ahead.dcube.security.token.service.TokenService;
import cn.ahead.dcube.task.AsyncTaskScheduler;

@RestController
@RequestMapping("/v1/security")
public class SecurityController {

	@Autowired
	private IUserSecurityService service;

	@Autowired
	private CaptchaConfig config;

	@Autowired
	private TokenService tokenService;

	/**
	 * 登录方法
	 * 
	 * @param loginBody 登录信息
	 * @return 结果
	 */
	@RequestMapping(path = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public Response login(HttpServletRequest request, @RequestParam(value = "account") String account,
			@RequestParam(value = "passwd") String passwd,
			@RequestParam(value = "captcha", required = false) String captcha) {
		// 1. 验证码校验
		if (config.isEnable()) {
			String captchaText = (String) request.getSession().getAttribute(AheadSysConstant.SESSION_CAPTCHA);
			if (captchaText == null) {
				AsyncTaskScheduler.me()
						.execute(LoginRecordFactory.recordLogininfo(account, LoginRecordFactory.OPER_LOGIN,
								LoginRecordFactory.RESULT_ERROR, SecurityResponseCode.CAPTCHATIMEOUT.getCode(),
								SecurityResponseCode.CAPTCHATIMEOUT.getMsg()));
				return Response.error(SecurityResponseCode.CAPTCHATIMEOUT);
			}
			if (!captchaText.equalsIgnoreCase(captcha)) {
				AsyncTaskScheduler.me()
						.execute(LoginRecordFactory.recordLogininfo(account, LoginRecordFactory.OPER_LOGIN,
								LoginRecordFactory.RESULT_ERROR, SecurityResponseCode.CAPTCHAERROR.getCode(),
								SecurityResponseCode.CAPTCHAERROR.getMsg()));
				return Response.error(SecurityResponseCode.CAPTCHAERROR);
			}
		}
		// 2.登陆
		String token = service.login(account, passwd);
		return Response.success(token);
	}

	/**
	 * 验证token,如果验证通过,返回token对应的用户信息.每次调用该方法,会对token进行延期
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(path = "/verify", method = RequestMethod.POST)
	public Response verifyToken(HttpServletRequest request) {
		Map<String, Object> response = new HashMap<String, Object>();
		String token = tokenService.getTokenFromHeaders(request);
		if (token == null) {
			return ErrorResponse.error(SecurityResponseCode.UNAUTHORIZED);
		} else {
			if (tokenService.getTokenCache().exist(token)) {
				response.put("code", ResponseCode.SUCCESS);
				response.put("userInfo", tokenService.getTokenCache().get(token));
				return SuccessResponse.success(response);
			} else {
				return ErrorResponse.error(SecurityResponseCode.UNAUTHORIZED);
			}
		}
	}

	/**
	 * 更改登陆人密码
	 */
	@RequestMapping(path = "/chpass", method = RequestMethod.POST)
	public Response changePasswd(HttpServletRequest request, @RequestParam(value = "op") String oldPass,
			@RequestParam(value = "np") String passwd) {
		service.chpass(oldPass, passwd);
		return SuccessResponse.success();
	}

	/**
	 * 根据SNS查询账户信息
	 */
	@RequestMapping(path = "/verify/sns", method = RequestMethod.POST)
	public Response verifySNS(HttpServletRequest request, @RequestBody Map<String,String> params) {
		String type = params.get("type");
		String unionid = params.get("unionid");
		return SuccessResponse.success(service.getBySNS(type, unionid));
	}

}
