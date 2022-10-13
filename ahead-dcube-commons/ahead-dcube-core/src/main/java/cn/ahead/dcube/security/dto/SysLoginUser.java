package cn.ahead.dcube.security.dto;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cn.ahead.dcube.base.dto.CommonLoginUser;
import lombok.Data;

@Data
public class SysLoginUser extends CommonLoginUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * 权限列表
	 */
	private Set<String> permissions;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return super.getAccount();
	}

	/**
	 * userDetails接口,不做处理
	 */
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
