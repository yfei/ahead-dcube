package cn.ahead.dcube.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.ahead.dcube.base.response.Response;
import cn.ahead.dcube.system.service.IUserService;

/**
 * user controller
 * 
 * @author yangfei
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService service;

	/**
	 * 绑定SNS账号
	 * 
	 * @param account
	 * @param passwd
	 * @param unionid
	 * @return
	 */
	@RequestMapping(path = "/sns/bind", method = { RequestMethod.POST })
	public Response bindSNS(String account, String passwd, String type, String unionid) {
		return Response.success();
	}

	/**
	 * 解绑SNS账号
	 * @param type
	 * @param account
	 * @param unionid
	 * @return
	 */
	@RequestMapping(path = "/sns/unbind", method = { RequestMethod.POST })
	public Response unbindSNS(String type, String account, String unionid) {
		return Response.success();
	}

}
