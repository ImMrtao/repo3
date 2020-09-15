package com.crm.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//获取登录信息
		HttpSession session = request.getSession();
		Object object = session.getAttribute("user");
		
		boolean flag = false;
		if (object!=null) {	//用户已登录
			flag = true;
		} else {	//用户未登录
			response.sendRedirect(request.getContextPath()+"/login.html");
		}
		return flag;
	}

}
