package com.crm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.entity.Customer;
import com.crm.entity.CustomerContact;
import com.crm.entity.User;
import com.crm.service.CustomerContactService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@CrossOrigin//配置可以跨域访问
@SuppressWarnings("all")
public class CustomerContactController {

	@Autowired
	private CustomerContactService service;
	
	//查询客户联系人
	@ResponseBody
	@RequestMapping("listCustomerContact.do")
	public Map<String, Object> listCustomerContact(@RequestParam(defaultValue = "1")Integer page,
			@RequestParam(defaultValue = "1")Integer rows,@RequestParam(defaultValue = "1")Integer cusId,
			HttpServletRequest request ) {
		//根据客户id查找对应的客户联系人
		HttpSession session = request.getSession();
		Object object = session.getAttribute("user");
		
		Customer cus = new Customer();
		cus.setCusId(cusId);
		
		//分页查询
		PageHelper.startPage(page, rows);
		
		List<CustomerContact> list = service.selectCustomerContact(cus);
		PageInfo<CustomerContact> pageinfo = new PageInfo<CustomerContact>(list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", pageinfo.getTotal());
		map.put("rows", list);
		return map;
	}
	
	/**
	 * 根据用户的id查询到对应客户的联系人
	 * @return 查询到的用户对应的联系人
	 */
	@ResponseBody
	@RequestMapping("listMyCustomerContact.do")
	public Map<String, Object> listMyCustomerContact(HttpServletRequest request,
			@RequestParam(defaultValue = "1")Integer page,
			@RequestParam(defaultValue = "10")Integer rows) {
		HttpSession session = request.getSession();
		Object object = session.getAttribute("user");
		User user = (User)object;
		
		Map< String, Object> map = new HashMap<String, Object>();
		Integer uId = null;
		if (user.getrId()!=1) {//销售主管，不需要uId条件
			uId = user.getuId();
		}
		System.err.println("service之前");
		PageInfo<CustomerContact> pageinfo = service.getMyCustomerContact(uId,page,rows);
		System.err.println("service之后");
		map.put("total", pageinfo.getTotal());
		map.put("rows", pageinfo.getList());
		return map;
	}
		
	//添加客户联系人
	@ResponseBody
	@RequestMapping("addCustomerContact.do")
	public String addCustomerContact(CustomerContact contact,HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object object = session.getAttribute("user");
		int flag = 0;
		if (object!=null) {
			//客户经理添加客户联系人
			User user = (User)object;
			if (user.getrId()==2) {
				flag = service.insertCustomerContact(contact);
			}
		}
		//返回结果
		if (flag==1) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	//修改客户联系人
	@ResponseBody
	@RequestMapping("updateCustomerContact.do")
	public String updateCustomerContact(CustomerContact contact,HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object object = session.getAttribute("user");
		
		int flag = 0;
		if (object!=null) {
			User user = (User)object;
			//客户经理能修改客户联系人
			if (user.getrId()==2) {
				flag = service.updateCustomerContact(contact);
			}
		}
		//返回结果
		if (flag==1) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	
	//删除联系人
	@ResponseBody
	@RequestMapping("delCustomerContact.do")
	public String delCustomerContact(Integer cusconId,HttpServletRequest request) {
		//根据联系人id删除指定联系人信息
		HttpSession session = request.getSession();
		Object object = session.getAttribute("user");
		int flag=0;
		
		if (object!=null) {
			User user = (User)object;
			if (user.getrId()==2) {
				flag = service.delCustomerContact(cusconId);
			}
		}
		//返回结果
		if (flag==1) {
			return "success";
		} else {
			return "fail";
		}
	}
	
}
