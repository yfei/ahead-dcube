package cn.ahead.dcube.system.user.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import cn.ahead.dcube.jpa.entity.IAuditEntity;
import lombok.Data;

/**
 * 账号与第三方账号绑定关系,一个第三方账号只能绑定一个账号
 * @author yangfei
 *
 */
@Entity
@Table(name = "tb_sys_user_sns")
@Data
public class UserSNSEntity extends IAuditEntity {

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "user_id", insertable = false, updatable = false, nullable = true, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private UserEntity user;

	@Column(name = "user_id")
	private Long userId;

	// SNS类型 wx/qq/alipay等
	private String type;

	@Column(name = "union_id")
	private String unionid;

	@Comment("是否禁用 Y/N")
	private String disabled;

	@Comment("禁用日期")
	@Column(name = "disabled_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date disabledDate;
	
	@Comment("绑定日期")
	@Column(name = "bind_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date bindDate;


	
	public boolean isDisabled() {
		return "Y".equals(disabled);
	}
}
