package cn.dcube.ahead.system.jpa.entity;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import cn.dcube.ahead.jpa.entity.IAuditEntity;
import cn.dcube.ahead.system.base.constant.OrgStatusEnum;

/**
 * 组织结构(单位)
 * @author yangfei
 *
 */
@Table
@Entity(name = "tb_sys_org")
public class SysOrgEntity extends IAuditEntity {

	// 编码
	private String code;

	// 名称
	private String name;
	
	@Column(name = "short_name")
	private String shortName;

	// 父级组织
	@Column(name = "p_org_id")
	private String parentOrgId;

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "p_org_id", insertable = false, updatable = false, nullable = true, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private SysOrgEntity parentOrg;
	
	// 负责人
	private String principal;
	
	// 状态
	private int status = OrgStatusEnum.NORMAL.value();

	private String remark;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public SysOrgEntity getParentOrg() {
		return parentOrg;
	}

	public void setParentOrg(SysOrgEntity parentOrg) {
		this.parentOrg = parentOrg;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
