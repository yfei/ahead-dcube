package cn.ahead.dcube.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.ahead.dcube.jpa.entity.IAuditEntity;
import lombok.Data;

@Entity
@Table(name = "tb_sys_user_sns")
@Data
public class UserSNSEntity extends IAuditEntity {

	@Column(name = "user_id")
	private Long userId;
	
	// SNS类型 wx/qq/alipay等
	private String type;
	
	@Column(name="union_id")
	private String unionid;
	
}
