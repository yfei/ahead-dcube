package cn.ahead.dcube.system.entity;

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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import cn.ahead.dcube.base.constant.AheadSysConstant;
import cn.ahead.dcube.jpa.entity.IAuditEntity;
import lombok.Data;

@Entity
@Table(name = "tb_sys_user")
@Data
public class UserEntity extends IAuditEntity {

	// 姓名
	private String name;

	// 昵称
	@Column(name = "nick_name")
	private String nickName;

	// 登陆账号
	@Column(name = "account")
	private String account;

	@Column(name = "org_id", nullable = false)
	private Long orgId;

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "org_id", insertable = false, updatable = false, nullable = true, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private OrgEntity org;

	// 密码
	private String password;

	// 手机
	private String phone;

	// 邮箱
	private String email;

	// 性别
	private Integer sex = AheadSysConstant.USER_SEX_UNKOWN;

	// 头像
	private String avtar;

	// 用户类型
	@Column(name = "user_type")
	private Integer userType = AheadSysConstant.USER_TYPE_COMMON;

	// 注册时间
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "register_time", nullable = true)
	private Date registerTime;

	// 最后登录时间
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lst_login_time", nullable = true)
	private Date lstLoginTime;
	
	// 登陆失败次数
	@Column(name = "login_failure_times", nullable = true)
	private Integer loginFailureTimes;

	// 状态 0 正常 1 禁用
	private Integer status = AheadSysConstant.USER_STATUS_NORMAL;

	// 备注
	private String remark;

}
