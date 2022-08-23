package cn.ahead.dcube.security.token.cache;

import java.util.Map;

import cn.ahead.dcube.base.dto.LoginUserModel;

public interface TokenCache {

	/**
	 * 是否存在
	 * 
	 * @param token
	 * @return
	 */
	public boolean exist(String token);

	/**
	 * 获取用户信息
	 * 
	 * @param token
	 * @return
	 */
	public LoginUserModel get(String token);

	/**
	 * 设置token
	 * 
	 * @param loginUser
	 */
	public String set(LoginUserModel loginUser);

	/**
	 * 删除token
	 * 
	 * @param token
	 */
	public void remove(String token);

	/**
	 * 列表所有在线的用户
	 * 
	 * @return
	 */
	public Map<String, LoginUserModel> list();

}
