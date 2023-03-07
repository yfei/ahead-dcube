package cn.ahead.dcube.system.org.service;

import java.util.List;
import java.util.Map;

import cn.ahead.dcube.base.dto.SysOrg;
import cn.ahead.dcube.base.service.IService;

public interface IOrgService extends IService {

	/**
	 * 根据id获取org信息
	 * 
	 * @param id
	 * @return
	 */
	public SysOrg getOrg(Long id, boolean withChildren);

	/**
	 * 查询当前org以及下属org的code列表
	 * 
	 * @param orgCode
	 * @return
	 */
	public List<String> getOrgCodeDescade(String orgCode);

	/**
	 * 查询org的map,key为orgCode或者name
	 * 
	 * @param orgCode
	 * @param nameAsKey
	 * @return
	 */
	public Map<String, String> getOrgMapDescade(String orgCode, boolean nameAsKey);

	/**
	 * 当前组织机构
	 * 
	 * @param orgCode
	 * @return
	 */
	public List<SysOrg> getOrgDescade(String orgCode);

	/**
	 * 查询所有org的code和name的映射
	 * 
	 * @return
	 */
	public Map<String, String> getOrgMap(boolean nameAsKey);

	/**
	 * 得到父级组织
	 * 
	 * @param orgCode
	 * @return
	 */
	public SysOrg getParentOrg(String orgCode);

	/**
	 * 根据code获取org信息
	 * 
	 * @param orgCode
	 * @return
	 */
	public SysOrg getOrgByCode(String orgCode);

	/**
	 * 获取授权的组织代码
	 * 
	 * @return
	 */
	public List<SysOrg> getAuthedOrgs();
}
