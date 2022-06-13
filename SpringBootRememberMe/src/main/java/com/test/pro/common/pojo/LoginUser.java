package com.test.pro.common.pojo;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 登录用户身份权限
 * 
 * @author hai
 */
public class LoginUser implements UserDetails {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户信息
	 */
	private SysUserInfo user;

	public SysUserInfo getUser() {
		return user;
	}

	public void setUser(SysUserInfo user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 权限列表
	 */
	private Set<String> permissions;


	public LoginUser() {
	}

	public LoginUser(Set<String> permissions) {
		this.permissions = permissions;
	}

	/**
	 * 账户是否未过期,过期无法验证
	 */
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * 指定用户是否解锁,锁定的用户无法进行身份验证
	 * 
	 * @return
	 */
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
	 * 
	 * @return
	 */
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 是否可用 ,禁用的用户不能身份验证
	 * 
	 * @return
	 */
	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}


	public Set<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<String> permissions) {
		this.permissions = permissions;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}
}
