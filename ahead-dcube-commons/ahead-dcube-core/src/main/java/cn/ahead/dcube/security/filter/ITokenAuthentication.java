package cn.ahead.dcube.security.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 
 * @desc: token的处理策略
 * @date: 2023年4月4日 下午1:53:46<br>
 * @author:yangfei<br>
 * @since 1.0.0
 */
public interface ITokenAuthentication {

	/**
	 * token认证
	 * 
	 * @param request
	 * @param session
	 * @param token
	 */
	public void handle(HttpServletRequest request, HttpSession session, String token);

	/**
	 * 是否需要处理的token类型
	 * 
	 * @param token
	 * @return
	 */
	public boolean ofType(String token);
	
	/**
	 * 是否生效
	 * @return
	 */
	public boolean enable();

	/**
	 * 类型
	 * @return
	 */
	public String type();
}
