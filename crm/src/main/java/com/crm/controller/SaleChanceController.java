package com.crm.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.crm.entity.SaleChance;
import com.crm.entity.User;
import com.crm.service.SaleChanceService;
import com.crm.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Controller
@CrossOrigin
public class SaleChanceController {

	@Autowired
	private SaleChanceService service;
	@Autowired
	private UserService userService;
	//添加
	@ResponseBody
	@RequestMapping("addSaleChance.do")
	public String addSaleChance(SaleChance sc,HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object object = session.getAttribute("user");
		
		User user = (User)object;
		String flag = "fail";
		//销售主管或客户经理可以创建
		if(user.getrId()==1||user.getrId()==2) {
			sc.setScUser(user.getuName());
			sc.setScCreatetime(new Date());
			sc.setScStatus(0);
			int row = service.insertSaleChance(sc);
			if(row==1) {
				flag = "success";
			}
		}
		return flag;			
	}
	//展示
	@ResponseBody
	@RequestMapping("listSaleChance.do")
	public Map<String, Object> listSaleChance(@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "1")Integer rows,HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object object = session.getAttribute("user");
		User user = (User)object;
		
		PageHelper.startPage(page, rows);
		
		List<SaleChance> list = new ArrayList<SaleChance>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(user.getrId()==1) {
			//销售主管展示所有
			list = service.selectSaleChance();
		}else if(user.getrId()==2) {
			//客户经理展示与自己相关
			list = service.selectSaleChance(user);			
		}
		
		PageInfo<SaleChance> pageinfo = new PageInfo<SaleChance>(list);
		map.put("total", pageinfo.getTotal());
		map.put("rows", list);
		
		return map;
	}
	//删除
	@ResponseBody
	@RequestMapping("delSaleChance.do")
	public String delSaleChance(int scId) {
		//机会状态已在客户端判断
		
		/*
		 * 客户端判断机会状态
		 * 将可删除的机会id提交到服务器
		 */
		int flag = service.delSaleChance(scId);
		String ret = "fail";
		if(flag==1) {
			ret="success";
		}
		return ret;		
	}
	//查找可以分配的人
	@ResponseBody
	@RequestMapping("selectfenpeiMan.do")
	public List<Map<String, String>> fenpeiSaleChance(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object object = session.getAttribute("user");
		User user = (User)object;
		if(user.getrId()==1) {
			//销售主管展示所有客户经理
			List<String> list = userService.selectName(2);
			List<Map<String, String>> cusmans = new ArrayList<Map<String, String>>();
			for (String string : list) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("uName", string);
				cusmans.add(map);
			}
			return cusmans;
		}else {
			return null;
		}
	}
	//分配
	@ResponseBody
	@RequestMapping("fenpeiSaleChance.do")
	public String fenpeiSaleChance(SaleChance sc,HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object object = session.getAttribute("user");
		User user = (User)object;
		//未选择想分配的人，返回直接刷新页面
		if(sc.getScGiveuser().equals("请选择")) {
			return "fail";
		}
		if(user.getrId()==1) {
			//销售主管可以分配
			sc.setScGivetime(new Date());
			sc.setScStatus(1);
			service.updateSaleChance(sc);
			return "success";
		}else {
			return "fail";
		}
	}
	//更新
	@ResponseBody
	@RequestMapping("updateSaleChance.do")
	public String updateSaleChance(SaleChance sc,HttpServletRequest request) {
		//未分配的可以进行修改
//		if(sc.getScStatus()==0) {
			service.updateSaleChance(sc);
			return "success";			
//		}else {
//			return "fail";
//		}
	}
}
