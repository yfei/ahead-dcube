package cn.ahead.dcube.security.handler;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import cn.ahead.dcube.base.response.Response;
import cn.ahead.dcube.base.response.code.SecurityResponseCode;
import cn.ahead.dcube.commons.util.StringFormatter;

/**
 * 认证失败处理类 返回未授权
 * 
 * @author yangfei
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
	private static final long serialVersionUID = -8970718410437077606L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
			throws IOException {
		response.setStatus(200);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		int code = SecurityResponseCode.UNAUTHORIZED.getCode();
		String msg = StringFormatter.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
		Response res = Response.error(code, msg);
		response.getWriter().print(JSON.toJSONString(res));
	}
}
