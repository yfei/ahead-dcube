package cn.dcube.ahead.system.jpa.entity;

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

import cn.dcube.ahead.jpa.entity.IAuditEntity;
import cn.dcube.ahead.system.base.constant.UserSexEnum;
import cn.dcube.ahead.system.base.constant.UserStatusEnum;
import cn.dcube.ahead.system.base.constant.UserTypeEnum;

/**
 * 用户对象
 * 
 * @author yangfei
 *
 */
@Table
@Entity(name = "tb_sys_user")
public class SysUserEntity extends IAuditEntity {

	// 组织id
	@Column(name = "org_id", nullable = false)
	private Long orgId;

	// 组织关联对象
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "org_id", insertable = false, updatable = false, nullable = true, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private SysOrgEntity orgEntity;

	// 登陆账号
	@Column(name = "account")
	private String account;

	// 姓名
	private String name;

	// 密码
	private String password;
	
	// 证件类型
	private String idCardType;
	
	// 证件号码
	private String idCardNo;

	// 性别
	@Column(nullable = true)
	private int sex = UserSexEnum.UNKOWN.value();

	@Temporal(TemporalType.DATE)
	@Column(name = "birth", nullable = true)
	private Date birth;

	// 手机
	private String phone;
	
	// 电话(座机)
	private String tel;

	// 邮箱
	private String email;

	// 地址
	private String address;

	// 用户类型
	@Column(name = "user_type")
	private String userType = UserTypeEnum.COMMON.name();

	// 注册时间
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "register_time", nullable = true)
	private Date registerTime;

	// 最后登录成功时间
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lst_success_login_time", nullable = true)
	private Date lstSuccessLoginTime;
	
	// 登陆失败次数
	@Column(name = "failure_login_times", nullable = true)
	private int failureLoginTimes = 0;

	// 最后登录失败时间
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lst_failure_login_time", nullable = true)
	private Date lstFailureLoginTime;
	
	// 最后登陆的ip
	@Column(name = "lst_login_ip", nullable = true)
	private String lstLoginIp;
	

	// 状态 0 正常 1 禁用
	private int status = UserStatusEnum.NORMAL.value();

	// 备注
	private String remark;

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public SysOrgEntity getOrgEntity() {
		return orgEntity;
	}

	public void setOrgEntity(SysOrgEntity orgEntity) {
		this.orgEntity = orgEntity;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Date getLstSuccessLoginTime() {
		return lstSuccessLoginTime;
	}

	public void setLstSuccessLoginTime(Date lstSuccessLoginTime) {
		this.lstSuccessLoginTime = lstSuccessLoginTime;
	}

	public int getFailureLoginTimes() {
		return failureLoginTimes;
	}

	public void setFailureLoginTimes(int failureLoginTimes) {
		this.failureLoginTimes = failureLoginTimes;
	}

	public Date getLstFailureLoginTime() {
		return lstFailureLoginTime;
	}

	public void setLstFailureLoginTime(Date lstFailureLoginTime) {
		this.lstFailureLoginTime = lstFailureLoginTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIdCardType() {
		return idCardType;
	}

	public void setIdCardType(String idCardType) {
		this.idCardType = idCardType;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
}
