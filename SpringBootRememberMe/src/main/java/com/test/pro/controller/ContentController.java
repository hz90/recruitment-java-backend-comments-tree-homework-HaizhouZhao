package com.test.pro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.pro.service.ContentService;
import com.test.pro.vo.ContentVo;
import com.test.pro.vo.ResponseVo;
/**
 * 留言记录
 * 
 * @return
 */
@RestController
public class ContentController {
	
	@Autowired
	ContentService contentService;
	/**
	 * 获取所有留言
	 * 
	 * @return
	 */
	@PostMapping("/getAllContent")
	@ResponseBody
	public ResponseVo<List<ContentVo>> getAllcontent(){
		ResponseVo<List<ContentVo>> res=new ResponseVo<List<ContentVo>>();
		res.setData(contentService.getAllContent());
		return res;
	}
	/**
	 * 追加留言
	 * 
	 * @return
	 */
	@PostMapping("/addContent")
	@ResponseBody
	public ResponseVo<String> addContent(@RequestBody ContentVo contentVo) {
		String result=contentService.addContent(contentVo);
		ResponseVo<String> responseVo=new ResponseVo<>();
		responseVo.setData(result);
		return responseVo;
	}
}
