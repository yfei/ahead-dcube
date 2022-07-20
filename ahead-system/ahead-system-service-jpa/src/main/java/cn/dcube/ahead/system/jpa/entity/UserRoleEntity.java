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

@Table
@Entity(name = "tb_user_role")
public class UserRoleEntity extends IAuditEntity {

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "role_id", nullable = false)
	private Long roleId;

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "user_id", insertable = false, updatable = false, nullable = true, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private SysUserEntity user;

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "role_id", insertable = false, updatable = false, nullable = true, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private RoleEntity rule;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public SysUserEntity getUser() {
		return user;
	}

	public void setUser(SysUserEntity user) {
		this.user = user;
	}

	public RoleEntity getRule() {
		return rule;
	}

	public void setRule(RoleEntity rule) {
		this.rule = rule;
	}

}
