package com.crm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.entity.Customer;
import com.crm.entity.CustomerExample;
import com.crm.mapper.CustomerMapper;

@Service
public class CustomerService {

	@Autowired
	private CustomerMapper mapper;
	
	//添加客户
	public int addCustomer(Customer cus) {
		return mapper.insertSelective(cus);
	}
	
	//修改客户
	public int updateCustomer(Customer cus) {
		CustomerExample example = new CustomerExample();
		example.or().andCusIdEqualTo(cus.getCusId());
		return  mapper.updateByExampleSelective(cus, example);
	}
	
	//查询所有客户
	public List<Customer> getCustomer(Integer uId) {
		if (uId!=null) {
			CustomerExample example = new CustomerExample();
			example.or().andUIdEqualTo(uId);
			return mapper.selectByExample(example);
		} else {
			return mapper.selectByExample(null);
		}
	}
	
	//删除客户
	public int deleteCustomer(int cusId) {
		CustomerExample example = new CustomerExample();
		example.or().andCusIdEqualTo(cusId);
		return mapper.deleteByExample(example);
	}
	
	//统计客户数据
	public List<Map<String,Object>> customerreport(String pfreport){
		return mapper.customerreport(pfreport);
	}
}
