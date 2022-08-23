package cn.ahead.dcube.security.service;

import cn.ahead.dcube.base.dto.LoginUserModel;

/**
 * 用户认证service的实现
 * 
 * @author yangfei
 *
 */
public interface IUserSecurityService {

	/**
	 * 根据账号查询用户信息
	 * 
	 * @param account
	 * @return 登录用户信息
	 */
	public LoginUserModel selectUserByUserName(String account);
	
	/**
	 * 登陆
	 * @return
	 */
	public String login(String account,String password);
	
	/**
	 * 注销
	 * @param token
	 * @return
	 */
	public String logout(String token);

}
