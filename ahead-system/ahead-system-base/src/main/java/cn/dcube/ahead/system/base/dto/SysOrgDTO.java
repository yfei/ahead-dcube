package cn.dcube.ahead.system.base.dto;

import cn.dcube.ahead.base.dto.AheadDTO;
import cn.dcube.ahead.system.base.constant.OrgStatusEnum;

/**
 * 组织dto
 * 
 * @author yangfei
 *
 */
public class SysOrgDTO extends AheadDTO<Long> {
	
	// 组织名称
	private String name;

	// 组织简称
	private String shortName;

	// 组织代码
	private String code;

	// 状态
	private int status = OrgStatusEnum.NORMAL.value();

	// 负责人
	private String principal;

}
