package cn.dcube.ahead.auth.context;

import java.util.List;

import cn.dcube.ahead.auth.model.LoginUser;

/**
 * 登录用户上下文
 *
 */
public interface LoginContext {

	/**
	 * 获取当前登录用户
	 *
	 * @return 当前登录用户信息
	 */
	LoginUser getSysLoginUser();

	/**
	 * 获取当前登录用户，如未登录，则返回null，不抛异常
	 *
	 * @return 当前登录用户信息
	 */
	LoginUser getSysLoginUserWithoutException();

	/**
	 * 获取当前登录用户的id
	 *
	 * @return 当前登录用户的id
	 */
	Long getSysLoginUserId();

	/**
	 * 判断用户是否登录
	 *
	 * @return 是否登录，true是，false否
	 */
	boolean hasLogin();

	/**
	 * 获取当前登录用户的账户
	 *
	 * @return 当前登陆用户的账户account
	 */
	String getSysLoginUserAccount();

	/**
	 * 判断当前登录用户是否有某资源的访问权限
	 *
	 * @param requestUri 请求的url
	 * @return 是否有访问权限，true是，false否
	 */
	boolean hasPermission(String requestUri);

	/**
	 * 判断当前登录用户是否包含某个角色
	 *
	 * @param roleCode 角色编码
	 * @return 是否包含该角色，true是，false否
	 */
	boolean hasRole(String roleCode);

	/**
	 * 判断当前登录用户是否包含任意一个角色
	 *
	 * @param roleCodes 角色集合，逗号拼接
	 * @return 是否包含任一角色，true是，false否
	 */
	boolean hasAnyRole(String roleCodes);

	/**
	 * 判断当前登录用户是否是超级管理员
	 *
	 * @return 当前登录用户是否是超级管理员
	 */
	boolean isSuperAdmin();

	/**
	 * 判断当前登录用户是否包含所有角色
	 *
	 * @param roleCodes 角色集合，逗号拼接
	 * @return 是否包含所有角色，true是，false否
	 */
	boolean hasAllRole(String roleCodes);

	/**
	 * 获取当前登录用户的数据范围集合（组织机构id集合）
	 *
	 * @return 数据范围集合（组织机构id集合）
	 */
	List<Long> getLoginUserDataScopeIdList();

	/**
	 * 获取当前登录用户的组织机构id
	 *
	 * @return 当前登录用户的组织机构id
	 */
	Long getSysLoginUserOrgId();

	/**
	 * 获取当前登录用户的角色id集合
	 *
	 * @return 当前登录用户角色id集合
	 */
	List<String> getLoginUserRoleIds();

	/**
	 * 获取最新的用户信息，用于修改之后前端获取
	 *
	 * @return 最新的用户信息
	 **/
	LoginUser getSysLoginUserUpToDate();
}
