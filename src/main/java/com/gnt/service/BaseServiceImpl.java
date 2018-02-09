package com.gnt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gnt.mapper.BaseMapper;

@Repository
@Scope(value="prototype")
public class BaseServiceImpl<K, V> implements BaseService<K, V> {

	private BaseMapper<K, V> baseMapper;

//	@Autowired
	public void setBaseMapper(BaseMapper<K, V> baseMapper) {
		this.baseMapper = baseMapper;
	}

	@Override
	public V selectByPrimaryKey(K k) {
		return this.baseMapper.selectByPrimaryKey(k);
	}

	@Override
	public int deleteByPrimaryKey(K k) {
		try {
			return this.baseMapper.deleteByPrimaryKey(k);
		} catch (Exception e) {
			e.getStackTrace();
			return -1;
		}
	}

	@Override
	public int insert(V v) {
		try {
			return this.baseMapper.insert(v);
		} catch (Exception e) {
			e.getStackTrace();
			return -1;
		}
	}

	@Override
	public int insertSelective(V v) {
		try {
			return this.baseMapper.insert(v);
		} catch (Exception e) {
			e.getStackTrace();
			return -1;
		}
	}

	@Override
	public int updateByPrimaryKeySelective(V v) {
		try {
			return this.baseMapper.updateByPrimaryKeySelective(v);
		} catch (Exception e) {
			e.getStackTrace();
			return -1;
		}
	}

	public int updateByPrimaryKey(V v) {
		try {
			return this.baseMapper.updateByPrimaryKey(v);
		} catch (Exception e) {
			e.getStackTrace();
			return -1;
		}
	}

	public List<V> selectAll() {
		try {
			return this.baseMapper.selectAll();
		} catch (Exception e) {
			e.getStackTrace();
			// NullPorintException 对应mapper.xml 中不存在 selectAll 方法sql 实现时
			// 会报NullPointException 异常
			return null;
		}
	}
}
