package com.crm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.entity.Customer;
import com.crm.entity.CustomerContact;
import com.crm.entity.CustomerContactExample;
import com.crm.mapper.CustomerContactMapper;
import com.crm.mapper.CustomerMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class CustomerContactService {

	@Autowired
	private CustomerContactMapper mapper;
	@Autowired
	private CustomerMapper cusmapper;
	
	private CustomerContactExample example = new CustomerContactExample();
	
	//查看联系人
	public List<CustomerContact> selectCustomerContact(Customer cus){
		example.clear();
		example.or().andCusIdEqualTo(cus.getCusId());
		return mapper.selectByExample(example);
	}
	
	//查看登录用户对应的客户联系人
	public PageInfo<CustomerContact> getMyCustomerContact(Integer uId,Integer page,Integer rows){
		//分页查询
		PageHelper.startPage(page, rows);
		//查询用户的客户有哪些
		List<Customer> customers = new ArrayList<Customer>();
		customers = cusmapper.selectCustomerAndContact(uId);
		List<CustomerContact> contacts = new ArrayList<CustomerContact>();
		//遍历客户列表，取出联系人信息
		for(Customer cus:customers) {
			for (CustomerContact con : cus.getContacts()) {
				con.setCusName(cus.getCusName());
				contacts.add(con);
			}
		}
		
		return new PageInfo<CustomerContact>(contacts);
	}
	
	//添加客户联系人
	public int insertCustomerContact(CustomerContact contact) {
		return mapper.insertSelective(contact);
	}
	
	//修改客户联系人
	public int updateCustomerContact(CustomerContact contact) {
		return mapper.updateByPrimaryKeySelective(contact);
	}
	
	//删除客户联系人
	public int delCustomerContact(Integer cusconId) {
		return mapper.deleteByPrimaryKey(cusconId);
	}
}
