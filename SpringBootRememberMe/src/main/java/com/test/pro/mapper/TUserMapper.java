package com.test.pro.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.test.pro.entity.T_User;

/**
 * 用户表
 * 
 * @author hai
 *
 */
@Mapper
public interface TUserMapper {
	/**
	 * 通过username和email查询用户
	 * 
	 * @return
	 */
	public List<T_User> queryUserByUsernameOrEmail(String username, String email, Integer userid);

	/**
	 * 查询用户
	 * 
	 * @return
	 */
	public T_User queryUserByUserId(Integer userid);

	/**
	 * 插入用户
	 * 
	 * @return
	 */
	public int insertUser(T_User t_User);

}
