package cn.ahead.dcube.system.service;

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
	public Map<String, String> getOrgMap();
}
