
package cn.dcube.ahead.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * HttpServlet工具类，获取当前request和response
 * 
 * @author yangfei
 *
 */
public class HttpServletUtil {

	/**
	 * 获取当前请求的request对象
	 *
	 */
	public static HttpServletRequest getRequest() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		return requestAttributes.getRequest();
	}

	/**
	 * 获取当前请求的response对象
	 *
	 */
	public static HttpServletResponse getResponse() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		return requestAttributes.getResponse();
	}
}
