package cn.ahead.dcube.system.function.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.ahead.dcube.jpa.entity.IAuditEntity;
import lombok.Data;

@Entity
@Table(name = "tb_sys_function_auth")
@Data
public class FunctionAuthenticationEntity extends IAuditEntity {

	// 功能id
	@Column(name = "function_id")
	private Long functionId;

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
