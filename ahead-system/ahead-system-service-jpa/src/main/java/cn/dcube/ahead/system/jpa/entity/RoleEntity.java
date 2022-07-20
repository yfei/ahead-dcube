package cn.dcube.ahead.system.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.dcube.ahead.jpa.entity.IAuditEntity;

/**
 * 角色
 * 
 * @author yangfei
 *
 */
@Table
@Entity(name = "tb_sys_role")
public class RoleEntity extends IAuditEntity {

	private String name;

	private String remark;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
