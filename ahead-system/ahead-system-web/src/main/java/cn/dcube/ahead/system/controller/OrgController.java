package cn.dcube.ahead.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.dcube.ahead.response.Response;
import cn.dcube.ahead.system.service.IOrgService;

/**
 * org controller
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
		return new Response(true, null);
	}

}
