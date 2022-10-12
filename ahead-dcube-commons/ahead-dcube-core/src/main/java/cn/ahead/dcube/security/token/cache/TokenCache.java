package cn.ahead.dcube.security.token.cache;

import java.util.Map;

import cn.ahead.dcube.base.dto.CommonLoginUser;

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
	public CommonLoginUser get(String token);

	/**
	 * 设置token
	 * 
	 * @param loginUser
	 */
	public String set(CommonLoginUser loginUser);

	/**
	 * 删除token
	 * 
	 * @param token
	 */
	public void remove(String token);
	
	/**
	 * 更新
	 * @param token
	 * @param loginUser
	 */
	public void update(String token,CommonLoginUser loginUser);

	/**
	 * 列表所有在线的用户
	 * 
	 * @return
	 */
	public Map<String, CommonLoginUser> list();

}
