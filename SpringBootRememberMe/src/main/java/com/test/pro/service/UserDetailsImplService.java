package com.test.pro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.test.pro.common.pojo.LoginUser;
import com.test.pro.common.pojo.SysUserInfo;
import com.test.pro.entity.T_User;
import com.test.pro.exception.BusinessException;

@Service
public class UserDetailsImplService implements UserDetailsService {

	@Autowired
	private LoginService loginService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		T_User tUserEntity = loginService.getUserInfo(username);
		if (tUserEntity != null) {
			SysUserInfo sysUserInfo = new SysUserInfo();
			sysUserInfo.setUsername(String.valueOf(tUserEntity.getId()));
			sysUserInfo.setPassword(tUserEntity.getPassWord());
			LoginUser loginUser = new LoginUser();
			loginUser.setUser(sysUserInfo);
			return loginUser;
		} else {
			throw new BusinessException("001", "对不起，您的账号：" + username + " 不存在");
		}
	}
}
