package cn.ahead.dcube.system.datascope.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.ahead.dcube.jpa.entity.IAuditEntity;
import lombok.Data;

@Entity
@Table(name = "tb_sys_data_scope")
@Data
public class DataScopeEntity extends IAuditEntity {

	// 数据权限类别
	@Column(name = "data_scope")
	private int dataScope;

	// 数据权限对应的模块
	private String module;

	// 授权对象 USER/ORG/ROLE
	@Column(name = "auth_object_type")
	private String authObjectType;

	// 授权对象 USER/ORG/ROLE
	@Column(name = "auth_object_id")
	private String authObjectId;

	// 0 正常 1 停用
	private int status;

	// 备注
	private String remark;
}
