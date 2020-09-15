package com.crm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.entity.User;
import com.crm.entity.UserExample;
import com.crm.mapper.UserMapper;

@Service
public class UserService {
	@Autowired
	private UserMapper mapper;
	UserExample example = new UserExample();

	public User selectUser(User user) {
		example.clear();
		example.or().andUNameEqualTo(user.getuName()).andUPasswordEqualTo(user.getuPassword());
		List<User> list = mapper.selectByExample(example);
		if (list.size() != 0) {
			return list.get(0);
		}
		return null;
	}
	//根据提供的角色ID进行查询，返回查询到的用户名的list
	public List<String> selectName(int rId) {
		example.clear();
		example.or().andRIdEqualTo(rId);
		List<User> list = mapper.selectByExample(example);
		List<String> users = new ArrayList<String>();
		for(int i = 0 ; i<list.size();i++) {
			users.add(list.get(i).getuName());
		}
		return users;
	}
}
