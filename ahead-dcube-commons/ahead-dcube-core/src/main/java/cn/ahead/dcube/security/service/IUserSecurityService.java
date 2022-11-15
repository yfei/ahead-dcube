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
	 * 
	 * @return
	 */
	public String login(String account, String password);

	/**
	 * 注销
	 * 
	 * @param token
	 * @return
	 */
	public String logout(String token);

	/**
	 * 修改密码
	 * 
	 * @param oldPass
	 * @param newPass
	 */
	public void chpass(String oldPass, String newPass);

	/**
	 * 绑定第三方账号
	 * @param type
	 * @param account
	 * @param passwd
	 * @param unionid
	 */
	public void bindSNS(String type, String account, String passwd, String unionid);

	/**
	 * 解绑第三方账号
	 * @param type
	 * @param passwd
	 * @param unionid
	 */
	public void unbindSNS(String type, String passwd, String unionid);
	
	/**
	 * 根据SNS社交账号得到用户信息
	 * @param type
	 * @param unionid
	 * @return
	 */
	public SysLoginUser getBySNS(String type, String unionid);

}
