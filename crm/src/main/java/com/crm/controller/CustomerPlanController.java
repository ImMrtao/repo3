package com.crm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.entity.CustomerPlan;
import com.crm.entity.SaleChance;
import com.crm.entity.User;
import com.crm.service.CustomerPlanService;
import com.crm.service.SaleChanceService;

@Controller
@CrossOrigin
public class CustomerPlanController {

	@Autowired
	private CustomerPlanService service;
	@Autowired
	private SaleChanceService saleChanceService;

	// 查找机会去制订执行计划
	@ResponseBody
	@RequestMapping("listSaleChanceToPlan.do")
	public List<SaleChance> listSaleChance(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object object = session.getAttribute("user");
		if (object != null) {
			User user = (User) object;
			// 制订开发计划的可能是主管，或被是分配到机会的人
			List<SaleChance> list = new ArrayList<SaleChance>();
			if (user.getrId() == 1) {
				list = saleChanceService.selectSaleChanceToPlan();
			} else if (user.getrId() == 2) {
				list = saleChanceService.selectSaleChanceToPlan(user.getuName());
			}
			if (list.size() != 0) {
				return list;
			}
		}
		return null;
	}

	// 制订执行计划
	@ResponseBody
	@RequestMapping("addCustomerPlan.do")
	public String addCustomerPlan(CustomerPlan plan) {
		service.addCustomerPlan(plan);
		return "success";
	}

	// 展示执行计划
	@ResponseBody
	@RequestMapping("listCustomerPlan.do")
	public List<CustomerPlan> listCustomerPlan(CustomerPlan plan,HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object object = session.getAttribute("user");
		User user = (User) object;
		//当前用户分配到且没有结束的机会
		List<SaleChance> list = saleChanceService.selectSaleChanceToPlan(user.getuName());
		List<CustomerPlan> plans = new ArrayList<CustomerPlan>();
		if(list.size()!=0) {
			//根据机会的ID查询到已制订的计划
			for(SaleChance sc:list) {
				plans.addAll(service.selectCustomerPlan(sc.getScId()));
			}
		}
		if(plans.size()!=0) {
			return plans;
		}
		return plans;
	}
	
	//执行销售计划
	@RequestMapping("doCustomerPlan.do")
	@ResponseBody
	public String doCustomerPlan(CustomerPlan plan) {
		int flag = service.doCustomerPlan(plan);
		String ret = "fail";
		if (flag == 1) {
			ret = "success";
		}
		return ret;
	}
	
	//开发完成
	@ResponseBody
	@RequestMapping("finishCustomerPlan.do")
	public String finishCustomerPlan(CustomerPlan plan,String newCustomer,HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object object = session.getAttribute("user");
		User user = (User)object;
		int flag = service.finishCustomerPlan(plan,user,newCustomer);
		String ret = "fail";
		if (flag == 1) {
			ret = "success";
		}
		return ret;
	}
}
