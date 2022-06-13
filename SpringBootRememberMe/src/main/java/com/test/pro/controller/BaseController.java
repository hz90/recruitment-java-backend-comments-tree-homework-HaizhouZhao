package com.test.pro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.pro.service.LoginService;
import com.test.pro.vo.LoginVo;
import com.test.pro.vo.ResponseVo;
import com.test.pro.vo.UserVo;
/**
 * 登录
 * 
 * @return
 */
@Controller
public class BaseController {
	@Autowired
	LoginService loginService;
	@RequestMapping(value={"/","/login"})
	public String login(@RequestParam(required = false) String message, final Model model) {
	    if (message != null && !message.isEmpty()) {
	      if (message.equals("logout")) {
	        model.addAttribute("message", "Logout!");
	      }
	      if (message.equals("error")) {
	        model.addAttribute("message", "Login Failed!");
	      }
	    }
	    return "login";
	  }
	/**
	 * 留言板
	 * 
	 * @return
	 */
	@RequestMapping("/admin")
	public String admin() {
		return "admin";
	}
	/**
	 * 用户注册
	 * 
	 * @return
	 */
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	/**
	 * 注册用户
	 * 
	 * @return
	 */
	@PostMapping("/postregister")
	@ResponseBody
	public ResponseVo<String> register(@RequestBody UserVo userVo) {
		String result=loginService.register(userVo);
		ResponseVo<String> responseVo=new ResponseVo<>();
		responseVo.setData(result);
		return responseVo;
	}
	/**
	 * 获取用户信息
	 * 
	 * @return
	 */
	@PostMapping("/getUserInfo")
	@ResponseBody
	public ResponseVo<String> getUserInfo(@RequestBody LoginVo loginVo) {
		String result=loginService.queryUserByUserId(loginVo.getAccountId());
		ResponseVo<String> responseVo=new ResponseVo<>();
		responseVo.setData(result);
		return responseVo;
	}
}
