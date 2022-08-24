package cn.ahead.dcube.base.dto;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cn.ahead.dcube.base.constant.AheadSysConstant;
import lombok.Data;

@Data
public class LoginUserModel implements IDTO, UserDetails {
	
	private Long id;

	// 姓名
	private String name;

	// 昵称
	private String nickName;

	// 登陆账号
	private String account;
	
	// 密码
	private String password;

	// 组织机构
	private OrgDTO org = new OrgDTO();

	// 手机
	private String phone;

	// 邮箱
	private String email;

	// 性别
	private Integer sex = AheadSysConstant.USER_SEX_UNKOWN;

	// 头像
	private String avtar;

	// 用户类型
	private Integer userType = AheadSysConstant.USER_TYPE_COMMON;

	// 注册时间
	private Date registerTime;

	// 最后登录时间
	private Date lstLoginTime;

	// 状态 0 正常 1 禁用
	private int status = AheadSysConstant.USER_STATUS_NORMAL;

	// 备注
	private String remark;
	
	
	/**
     * 权限列表
     */
    private Set<String> permissions;
    
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.account;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
		
}
