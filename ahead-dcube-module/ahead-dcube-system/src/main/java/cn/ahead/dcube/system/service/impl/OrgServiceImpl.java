package cn.ahead.dcube.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ahead.dcube.base.constant.AheadSysConstant;
import cn.ahead.dcube.base.dto.SysOrg;
import cn.ahead.dcube.jpa.fei.query.filter.IFilter;
import cn.ahead.dcube.jpa.fei.query.filter.impl.SimpleFilter;
import cn.ahead.dcube.jpa.fei.service.impl.FeiServiceImpl;
import cn.ahead.dcube.system.dao.OrgRepository;
import cn.ahead.dcube.system.entity.OrgEntity;
import cn.ahead.dcube.system.service.IOrgService;

@Service
public class OrgServiceImpl extends FeiServiceImpl implements IOrgService {

	@Autowired
	private OrgRepository repository;

	@Override
	public SysOrg getOrg(Long id, boolean withChildren) {
		OrgEntity org = repository.getById(id);
		SysOrg orgDTO = new SysOrg();
		BeanUtils.copyProperties(org, orgDTO);
		if (withChildren) {
			// TODO 
		}
		return orgDTO;
	}

	@Override
	public List<String> getOrgCodeDescade(String orgCode) {
		IFilter filter = new SimpleFilter("code", orgCode)
				.appendAnd(new SimpleFilter("status", AheadSysConstant.ORG_STATUS_NORMAL));
		List<OrgEntity> orgs = this.get(OrgEntity.class, filter);
		List<String> orgCodes = new ArrayList<String>();
		for (OrgEntity org : orgs) {
			orgCodes.add(org.getCode());
			IFilter filterSub = new SimpleFilter("parentOrg.code", orgCode)
					.appendAnd(new SimpleFilter("status", AheadSysConstant.ORG_STATUS_NORMAL));
			List<OrgEntity> orgsSub = this.get(OrgEntity.class, filterSub);
			for (OrgEntity orgSub : orgsSub) {
				List<String> sub = this.getOrgCodeDescade(orgSub.getCode());
				orgCodes.addAll(sub);
			}
		}
		return orgCodes;
	}

	@Override
	public Map<String, String> getOrgMapDescade(String orgCode, boolean nameAsKey) {
		IFilter filter = new SimpleFilter("code", orgCode)
				.appendAnd(new SimpleFilter("status", AheadSysConstant.ORG_STATUS_NORMAL));
		List<OrgEntity> orgs = this.get(OrgEntity.class, filter);
		Map<String, String> orgMaps = new HashMap<String, String>();
		for (OrgEntity org : orgs) {
			if (nameAsKey) {
				orgMaps.put(org.getName(), org.getCode());
			} else {
				orgMaps.put(org.getCode(), org.getName());
			}
			IFilter filterSub = new SimpleFilter("parentOrg.code", orgCode)
					.appendAnd(new SimpleFilter("status", AheadSysConstant.ORG_STATUS_NORMAL));
			List<OrgEntity> orgsSub = this.get(OrgEntity.class, filterSub);
			for (OrgEntity orgSub : orgsSub) {
				Map<String, String> sub = this.getOrgMapDescade(orgSub.getCode(), nameAsKey);
				orgMaps.putAll(sub);
			}
		}
		return orgMaps;
	}

	@Override
	public List<SysOrg> getOrgDescade(String orgCode) {
		IFilter filter = new SimpleFilter("code", orgCode)
				.appendAnd(new SimpleFilter("status", AheadSysConstant.ORG_STATUS_NORMAL));
		List<OrgEntity> orgs = this.get(OrgEntity.class, filter);
		List<SysOrg> orgDtos = new ArrayList<SysOrg>();
		for (OrgEntity org : orgs) {
			SysOrg orgDto = new SysOrg();
			BeanUtils.copyProperties(org, orgDto);
			orgDtos.add(orgDto);

			List<SysOrg> children = new ArrayList<SysOrg>();
			// 查询子
			IFilter filterSub = new SimpleFilter("parentOrg.code", orgCode)
					.appendAnd(new SimpleFilter("status", AheadSysConstant.ORG_STATUS_NORMAL));
			List<OrgEntity> orgsSub = this.get(OrgEntity.class, filterSub);
			for (OrgEntity orgSub : orgsSub) {
				List<SysOrg> sub = this.getOrgDescade(orgSub.getCode());
				children.addAll(sub);
			}
			orgDto.setChildren(children);
		}
		return orgDtos;
	}

	@Override
	public Map<String, String> getOrgMap() {
		Map<String, String> nameCodeMap = new HashMap<String, String>();
		IFilter filter = new SimpleFilter("status", AheadSysConstant.ORG_STATUS_NORMAL);
		List<OrgEntity> orgs = this.get(OrgEntity.class, filter);
		for (OrgEntity org : orgs) {
			nameCodeMap.put(org.getCode(), org.getName());
		}
		return nameCodeMap;
	}

}
