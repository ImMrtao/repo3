package com.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.entity.Customer;
import com.crm.entity.CustomerPlan;
import com.crm.entity.CustomerPlanExample;
import com.crm.entity.SaleChance;
import com.crm.entity.User;
import com.crm.mapper.CustomerMapper;
import com.crm.mapper.CustomerPlanMapper;
import com.crm.mapper.SaleChanceMapper;

@Service
public class CustomerPlanService {

	@Autowired
	private CustomerPlanMapper mapper;
	@Autowired
	private SaleChanceMapper salechanceMapper;
	@Autowired
	private CustomerMapper customerMapper;
	//添加开发计划
	public int addCustomerPlan(CustomerPlan plan) {
		return mapper.insertSelective(plan);
	}
	
	//查询开发计划
	public List<CustomerPlan> selectCustomerPlan(int scId) {
		CustomerPlanExample example = new CustomerPlanExample();
		example.or().andScIdEqualTo(scId);
		return mapper.selectByExample(example);
	}
	
	//执行开发计划
	public int doCustomerPlan(CustomerPlan plan) {
		return mapper.updateByPrimaryKeySelective(plan);
	}
	
	//开发完成
	public int finishCustomerPlan(CustomerPlan plan,User user,String newCustomer) {
		//修改计划表
		mapper.updateByPrimaryKeySelective(plan);
		//修改机会表-机会状态修改，成功为2，失败为3
		SaleChance chance = new SaleChance();
		chance.setScId(plan.getScId());
		if (plan.getCpPlancase().equals("开发成功")) {
			chance.setScStatus(2);
		} else {
			chance.setScStatus(3);
		}
		salechanceMapper.updateByPrimaryKeySelective(chance);//将销售机会的状态修改为开发成功或者失败
		if (newCustomer.equals("yes")) {//当用户选择添加为客户时，执行if语句
			//将销售机会转换为客户
			SaleChance sc = salechanceMapper.selectByPrimaryKey(chance.getScId());
			//创建客户
			Customer customer = new Customer();
			customer.setCusStatus(1);//	客户的状态--默认为正常
			customer.setCusGrade(5);
			customer.setCusName(sc.getScCusname());
			customer.setuId(user.getuId());
			customer.setCusArea("");
			customer.setCusSatisfaction(1);
			customer.setCusCredit(1);
			//将创建好的客户保存到数据库中
			customerMapper.insertSelective(customer);
		}
		return 1;
	}
}
