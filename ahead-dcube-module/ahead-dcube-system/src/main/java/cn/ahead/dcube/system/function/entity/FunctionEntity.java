package cn.ahead.dcube.system.function.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.ahead.dcube.jpa.entity.IAuditEntity;
import lombok.Data;

@Entity
@Table(name = "tb_sys_function")
@Data
public class FunctionEntity extends IAuditEntity {

	// 名称
	private String name;

	// 编码
	private String code;

	// MENU/FUNCTION
	private String type;

	// 对应的请求url
	private String url;

	// 对应的icon
	private String icon;

	// 备注
	private String remark;

	// 0 正常 1 停用
	private int status;

}
