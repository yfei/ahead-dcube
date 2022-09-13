package cn.ahead.dcube.base.dto;

import java.util.ArrayList;
import java.util.List;

import cn.ahead.dcube.base.constant.AheadSysConstant;
import lombok.Data;

@Data
public class SysOrg implements IDTO {

	private Long id;

	private String code;

	private String name;
	
	private Long parentId;
	
	private String parentOrgCode;

	private String shortName;

	/** 行政区划 */
	private String region;

	/** 负责人 */
	private String leader;

	/** 联系电话 */
	private String phone;

	/** 邮箱 */
	private String email;

	/** 部门状态:0正常,1停用 */
	private Integer status = AheadSysConstant.ORG_STATUS_NORMAL;
	/** 子部门 */
	private List<SysOrg> children = new ArrayList<SysOrg>();

}
