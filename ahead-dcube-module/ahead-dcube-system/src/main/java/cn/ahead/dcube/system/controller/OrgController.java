package cn.ahead.dcube.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.ahead.dcube.base.constant.AheadSysConstant;
import cn.ahead.dcube.base.dto.LoginUserModel;
import cn.ahead.dcube.base.dto.OrgDTO;
import cn.ahead.dcube.base.response.Response;
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

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IOrgService service;

	@RequestMapping(path = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public Response getOrg(HttpServletRequest request) {
		LoginUserModel user = (LoginUserModel) request.getSession().getAttribute(AheadSysConstant.SESSION_USER);
		List<OrgDTO> orgs = service.getOrgDescade(user.getOrg().getCode());
		return Response.success(orgs);
	}

}
