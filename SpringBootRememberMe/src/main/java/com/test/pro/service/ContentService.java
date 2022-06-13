package com.test.pro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.pro.entity.T_Content;
import com.test.pro.exception.AppException;
import com.test.pro.mapper.TContentMapper;
import com.test.pro.vo.ContentVo;

/**
 * 留言
 * 
 * @author hai
 *
 */
@Service
public class ContentService {
	@Autowired
	TContentMapper tContentMapper;

	/**
	 * 获取所有留言记录
	 * @return
	 */
	public List<ContentVo> getAllContent() {
		List<ContentVo> lstContentVo = new ArrayList<>();
		List<T_Content> lstContent = tContentMapper.getAllContent();
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(lstContent);
			lstContentVo = mapper.readValue(json, new TypeReference<List<ContentVo>>() {
			});
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return lstContentVo;
	}
	/**
	 * 插入留言记录
	 * @return
	 */
	@Transactional(rollbackFor = AppException.class)
	public String addContent(ContentVo contentVo) {
		T_Content t_Content=new T_Content();
		BeanUtils.copyProperties(contentVo, t_Content);
		tContentMapper.insertContent(t_Content);
		return "OK";
	}
}
