package cn.dcube.ahead.system.service;

import cn.dcube.ahead.base.page.Pagination;
import cn.dcube.ahead.system.base.dto.SysOrgDTO;

public interface IOrgService {
	
	/**
	 * 新增用户
	 * 
	 * @param org
	 */
	public void saveOrg(SysOrgDTO org);

	/**
	 * 更新用户
	 * 
	 * @param org
	 */
	public void updateOrg(SysOrgDTO org);

	/**
	 * 删除用户
	 * 
	 * @param orgId
	 */
	public void deleteOrg(Long orgId);

	/**
	 * 查询用户
	 * 
	 * @param orgId
	 * @return
	 */
	public SysOrgDTO getOrg(Long orgId);

	/**
	 * 查询org列表
	 * 
	 * @param org
	 * @return
	 */
	public Pagination<SysOrgDTO> listOrg(SysOrgDTO org);

}
