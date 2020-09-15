package com.crm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.entity.Customer;
import com.crm.entity.User;
import com.crm.service.CustomerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@CrossOrigin//允许跨域访问
public class CustomerController {

	@Autowired
	private CustomerService service;

	// 查询客户信息
	@ResponseBody
	@RequestMapping("listCustomer.do")
	public Map<String,Object> listCustomer(
			@RequestParam(defaultValue = "1")Integer page,
			@RequestParam(defaultValue = "10")Integer rows,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object object = session.getAttribute("user");
		
		List<Customer> list = new ArrayList<Customer>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		User user = (User) object;
		
		/*
		 * 销售主管查看所有，
		 * 客户经理查看属于自己的客户信息
		 * */
		Integer uId = null;
		if (user.getrId() == 2) {
			uId = user.getuId();
		}
		//分页查询
		PageHelper.startPage(page, rows);
		list = service.getCustomer(uId);
		PageInfo<Customer> pageinfo = new PageInfo<Customer>(list);
		map.put("total", pageinfo.getTotal());
		map.put("rows", list);
		
		return map;
	}
	
	
	// 添加客户信息
	@ResponseBody
	@RequestMapping("addCustomer.do")
	public String addCustomer(Customer cus,HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object object = session.getAttribute("user");
		int flag = 0;
		if (object != null) {
			User user = (User) object;
			// 客户经理可以进行添加操作
			if (user.getrId() == 2) {
				cus.setuId(user.getuId());
				flag = service.addCustomer(cus);
			}
		}
		if(flag == 1) {
			return "success";
		}else {
			return "fail";			
		}
	}
	// 修改客户信息
	@ResponseBody
	@RequestMapping("updateCustomer.do")
	public String updateCustomer(Customer cus,HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object object = session.getAttribute("user");
		int flag = 0;
		if (object != null) {
			User user = (User) object;
			// 客户经理可以进行修改操作
			if (user.getrId() == 2) {
				flag = service.updateCustomer(cus);
			}
		}
		if(flag == 1) {
			return "success";
		}else {
			return "fail";			
		}
	}
	// 删除客户信息
	@ResponseBody
	@RequestMapping("delCustomer.do")
	public String delCustomer(int cusId,HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object object = session.getAttribute("user");
		int flag = 0;
		if (object != null) {
			User user = (User) object;
			// 客户经理可以进行修改操作
			if (user.getrId() == 2) {
				flag = service.deleteCustomer(cusId);
			}
		}
		if(flag == 1) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	//统计报表客户信息
	@ResponseBody
	@RequestMapping("customerreport.do")
	public Map<String, Object> customerreport(@RequestParam("gcd") String pfreport){
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Map<String,Object>> list = service.customerreport(pfreport);
		List<String> xlist = new ArrayList<String>();//分组字段
		List<Integer> slist = new ArrayList<Integer>();//数量
		
		Map<String, String[]> properties=new HashMap<String, String[]>(); 
		
		String[] status = {"不正常","正常"};//客户状态
		String[] grade = {"","战略合作伙伴","合作伙伴","大客户","重点开发客户","普通客户","VIP客户"};//客户等级
		String[] satisfaction = {"","很满意","比较满意","一般","不满意","很不满意"};//客户满意度
		String[] credit = {"","很诚信","比较诚信","一般","不诚信","很不诚信"};//客户信誉度
		
		properties.put("客户状态", status);//客户状态
		properties.put("客户等级", grade);//客户等级
		properties.put("客户满意度", satisfaction);//客户满意度
		properties.put("客户信誉度", credit);//客户信誉度
		
		//获取分组属性对应的数组
		String[] propertyCN = {};
		if (!pfreport.equals("客户地区")) {
			propertyCN = properties.get(pfreport);
		}

		for (Map<String, Object> map2 : list) {
			if (!pfreport.equals("客户地区")) {
				int g = Integer.parseInt(map2.get("property").toString());
				xlist.add(propertyCN[g]);//保存分组属性到集合中
				slist.add(Integer.parseInt(map2.get("number").toString()));//保存该等级客户数量到集合中
			} else {
				xlist.add(map2.get("property").toString());//保存分组属性到集合中
				slist.add(Integer.parseInt(map2.get("number").toString()));//保存该等级客户数量到集合中
			}
			
		}
		map.put("x", xlist);
		map.put("s", slist);
		
		return map;
	}
	
}
