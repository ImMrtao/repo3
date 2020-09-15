package com.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.entity.SaleChance;
import com.crm.entity.SaleChanceExample;
import com.crm.entity.User;
import com.crm.mapper.SaleChanceMapper;

@Service
public class SaleChanceService {
	@Autowired
	private SaleChanceMapper mapper;
	SaleChanceExample example = new SaleChanceExample();
	//创建销售机会
	public int insertSaleChance(SaleChance sc) {
		return mapper.insertSelective(sc);
	}
	//展示销售机会
	public List<SaleChance> selectSaleChance(User user) {
		example.clear();
		example.or().andScUserEqualTo(user.getuName());
		example.or().andScGiveuserEqualTo(user.getuName());
		return mapper.selectByExample(example);
	}
	public List<SaleChance> selectSaleChance() {
		return mapper.selectByExample(null);
	}
//	public SaleChance selectSaleChance(int scId) {
//		return mapper.selectByPrimaryKey(scId);
//	}
	public List<SaleChance> selectSaleChanceToPlan(String name) {
		example.clear();
		//根据被分配的人来查找销售机会
		example.or().andScGiveuserEqualTo(name).andScStatusEqualTo(1);
		return mapper.selectByExample(example);
	}
	public List<SaleChance> selectSaleChanceToPlan() {
		example.clear();
		//来查找已分配的销售机会
		example.or().andScStatusEqualTo(1);
		return mapper.selectByExample(example);
	}
	//删除销售机会
	public int delSaleChance(int scId) {
		return mapper.deleteByPrimaryKey(scId);
	}
	//修改销售机会
	public int updateSaleChance(SaleChance sc) {
		return mapper.updateByPrimaryKeySelective(sc);
	}
}