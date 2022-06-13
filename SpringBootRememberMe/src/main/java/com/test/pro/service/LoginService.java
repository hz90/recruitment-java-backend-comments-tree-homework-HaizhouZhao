package com.test.pro.service;

import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.pro.entity.T_User;
import com.test.pro.exception.AppException;
import com.test.pro.exception.BusinessException;
import com.test.pro.mapper.TUserMapper;
import com.test.pro.vo.LoginVo;
import com.test.pro.vo.UserVo;

/**
 * 登录
 * 
 * @author hai
 *
 */
@Service
public class LoginService {
	@Autowired
	TUserMapper tUserMapper;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Resource
	private AuthenticationManager authenticationManager;

	/**
	 * 登录方法
	 * 
	 * @param loginVo
	 * @return
	 */
	public String login(LoginVo loginVo) {

		// 用户验证
		Authentication authentication = null;
		try {
			// 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginVo.getAccountId(), loginVo.getPassWord()));
		} catch (Exception e) {

			throw new BusinessException(null, e.getMessage());
		}
		// String str = jwtService.createToken(loginUserInfoDto);
		return "";
	}
	/**
	 * 注册用户
	 * @param accountID
	 * @return
	 */
	@Transactional(rollbackFor = AppException.class)
	public String register(UserVo userVo) {
		List<T_User> listUser = tUserMapper.queryUserByUsernameOrEmail(userVo.getUsername(), userVo.getEmail(), null);
		if (listUser != null && listUser.size() >= 1) {
			String msg = "";
			if (listUser.size() == 2) {
				msg = "对不起，当前用户名和邮箱已存在。";
			} else {
				if (listUser.get(0).getUserName().equals(userVo.getUsername())) {
					msg = "对不起，当前用户名已存在。";
				} else {
					msg = "对不起，当前邮箱已存在。";
				}
			}
			throw new BusinessException("002", msg);
		} else {
			T_User t_User = new T_User();
			t_User.setUserName(userVo.getUsername());
			t_User.setEmail(userVo.getEmail());
			t_User.setPassWord(bCryptPasswordEncoder.encode(userVo.getPassword()));
			int count = tUserMapper.insertUser(t_User);
		}
		return "OK";
	}
    
	/**
	 * 获取用户信息
	 * @param accountID
	 * @return
	 */
	@Transactional(rollbackFor = AppException.class)
	public T_User getUserInfo(String accountID) {
		List<T_User> listUser = tUserMapper.queryUserByUsernameOrEmail(accountID, accountID,
				Pattern.compile("^[-\\+]?[\\d]*$").matcher(accountID).matches() ? Integer.valueOf(accountID) : null);
		if (listUser != null && listUser.size() == 1) {
			return listUser.get(0);
		}
		return null;
	}

	public String queryUserByUserId(String userid) {
		T_User t_User = null;
		if (userid != null && !"".equals(userid)) {
			t_User = tUserMapper.queryUserByUserId(Integer.valueOf(userid));
		}
		if (t_User != null) {
			return "user=" + t_User.getUserName() + " email=" + t_User.getEmail();
		}
		return "未登录";
	}

}
