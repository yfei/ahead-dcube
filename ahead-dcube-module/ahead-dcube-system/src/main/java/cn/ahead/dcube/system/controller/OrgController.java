package cn.ahead.dcube.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.ahead.dcube.base.constant.AheadSysConstant;
import cn.ahead.dcube.base.dto.SysOrg;
import cn.ahead.dcube.base.response.Response;
import cn.ahead.dcube.security.dto.SysLoginUser;
import cn.ahead.dcube.system.service.IOrgService;

/**
 * auth controller
 * 
 * @author yangfei
 *
 */
@RestController
@RequestMapping("/org")
public class OrgController {

	@Autowired
	private IOrgService service;

	@RequestMapping(path = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public Response getOrg(HttpServletRequest request) {
		SysLoginUser user = (SysLoginUser) request.getSession().getAttribute(AheadSysConstant.SESSION_USER);
		List<SysOrg> orgs = service.getOrgDescade(user.getOrg().getCode());
		return Response.success(orgs);
	}

}
