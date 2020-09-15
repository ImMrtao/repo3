package com.crm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.entity.User;
import com.crm.service.UserService;

@Controller
@CrossOrigin
public class UserController {
	@Autowired
	UserService service;
	
	//用户登录
	@RequestMapping("login.do")
	@ResponseBody
	public String login(User user,HttpServletRequest request) {
		User user2 = service.selectUser(user);
		if(user2==null) {
			return "fail";
		}else {
			HttpSession session = request.getSession();
			String appname = request.getContextPath();
			session.setAttribute("user", user2);
			session.setAttribute("appname", appname);
			return "success";
		}
	}
	
	//获取用户登录信息
	@ResponseBody
	@RequestMapping("getUser.do")
	public User getUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object object = session.getAttribute("user");
		User user = (User)object;
		
		return user;
	}
	
	//退出登录
	@RequestMapping("exit.do")
	public String exit(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/login.html";
	}
}
