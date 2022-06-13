package com.test.pro.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.test.pro.entity.T_Content;

/**
 * 留言记录
 * 
 * @author hai
 *
 */
@Mapper
public interface TContentMapper {
	/**
	 * 获取所有留言记录
	 * 
	 * @return
	 */
	public List<T_Content> getAllContent();

	/**
	 * 插入留言记录
	 * 
	 * @return
	 */
	public int insertContent(T_Content t_Content);

}
