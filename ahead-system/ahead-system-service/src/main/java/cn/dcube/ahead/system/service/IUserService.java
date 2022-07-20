package cn.dcube.ahead.system.service;

import cn.dcube.ahead.base.page.Pagination;
import cn.dcube.ahead.system.base.dto.SysUserDTO;

/**
 * 用户service
 * 
 * @author yangfei
 *
 */
public interface IUserService {

	/**
	 * 新增用户
	 * 
	 * @param user
	 */
	public void saveUser(SysUserDTO user);

	/**
	 * 更新用户
	 * 
	 * @param user
	 */
	public void updateUser(SysUserDTO user);

	/**
	 * 删除用户
	 * 
	 * @param userId
	 */
	public void deleteUser(Long userId);

	/**
	 * 查询用户
	 * 
	 * @param userId
	 * @return
	 */
	public SysUserDTO getUser(Long userId);

	/**
	 * 通过账号查询用户
	 * 
	 * @param account
	 * @return
	 */
	public SysUserDTO getUserByAccount(String account);

	/**
	 * 查询user列表
	 * 
	 * @param user 查询条件
	 * @return
	 */
	// FIXME 排序如何处理?可以从request中获取信息,进行处理
	public Pagination<SysUserDTO> listUser(SysUserDTO user);
}
