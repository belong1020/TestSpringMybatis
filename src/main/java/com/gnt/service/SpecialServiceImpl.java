package com.gnt.service;

import com.gnt.entity.User;
import com.gnt.mapper.BaseMapper;
import com.gnt.mapper.UserMapper;

public class SpecialServiceImpl<K, V> extends BaseServiceImpl<K, V>{

	public SpecialServiceImpl () {
		
	}
	
	public SpecialServiceImpl (BaseMapper<K, V> baseMapper) {
		super(baseMapper);
	}
	
	
	public User selectBySelective(User user) {
		return ((UserMapper)this.baseMapper).selectBySelective(user);
	}
	
	
	
}
