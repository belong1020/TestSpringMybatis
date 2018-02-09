package com.gnt.controller;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.gnt.entity.User;
import com.gnt.entity.UserKey;
import com.gnt.mapper.UserMapper;

/*
 * no used.
 */
@Deprecated
public class UserController {

	public static SqlSessionFactory sqlSessionFactory;
	public static Reader reader;
	static {
		try {
			reader = Resources.getResourceAsReader("MybatisConfig.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static User selectByPrimaryKey(int id) {
		SqlSession session = null;
		try {
			UserKey uKey = new UserKey();
			uKey.setId(id);
			session = sqlSessionFactory.openSession();
			UserMapper mapper = session.getMapper(UserMapper.class);
			return (User) mapper.selectByPrimaryKey(uKey);
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
	
	public static List<User> selectAll() {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			UserMapper mapper = session.getMapper(UserMapper.class);
			return  mapper.selectAll();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
	

}
