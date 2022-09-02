package cn.ahead.dcube.security.service;

import cn.ahead.dcube.security.dto.SysLoginUser;

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
	public SysLoginUser selectUserByUserName(String account);
	
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
	
	/**
	 * 修改密码
	 * @param oldPass
	 * @param newPass
	 */
	public void chpass(String oldPass, String newPass);

}
