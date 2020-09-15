package com.crm.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
@SuppressWarnings("all")
public class ExceptionController {
	@RequestMapping("/runtime")
	public void runtimeException() {
		int a = 10/0;
	}
}
