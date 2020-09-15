package com.crm.exception;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/*
 * 全局异常处理
 */
public class GlobalException implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) {
		System.out.println("exception:"+exception);
		
		ModelAndView mav = new ModelAndView();
		
		Map<String, Object> model = mav.getModel();
		model.put("status", 500);
		model.put("exception",exception);
		model.put("msg","系统异常，请重试!");
		/*
		 需要传递到页面上的内容：
		 	1. 错误信息
		 */
		mav.setViewName("error");
		return mav;
	}

}
