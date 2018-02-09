package com.gnt.service;

import java.util.List;

import com.gnt.mapper.BaseMapper;

public interface BaseService<K, V> {

    int deleteByPrimaryKey(K k);

    int insert(V v);

    int insertSelective(V v);

    V selectByPrimaryKey(K k);

    int updateByPrimaryKeySelective(V v);

    int updateByPrimaryKey(V v);
    
    List<V> selectAll();

	// <T extends BaseMapper<K, V>> List<V> selectAll();

	// <K, T> int deleteByPrimaryKey(K k, Class<T> clazz);
}
