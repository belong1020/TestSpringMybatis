package com.gnt.controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.gnt.mapper.BaseMapper;

/**
 * used in only mybatis framework, and
 * because spring manage Sqlsession , 
 * so this class has useless. 
 * 
 * @author Belong.
 * @param <K>
 *            xxxKey
 * @param <V>
 *            xxx (entity)
 */
public class BaseController {

	public static SqlSessionFactory sqlSessionFactory;

//	static {
//		try {
//			reader = Resources.getResourceAsReader("MybatisConfig.xml");
//			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	public static <K, T> int deleteByPrimaryKey(K k, Class<T> clazz) {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			T mapper = (T) session.getMapper(clazz);
			return  ((BaseMapper) mapper).deleteByPrimaryKey(k);
		} catch (Exception e) {
			e.getStackTrace();
			return -1;
		} finally {
			session.close();
		}
	}
	
	public static <V, T> int insert(V v, Class<T> clazz) {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			T mapper = (T) session.getMapper(clazz);
			return ((BaseMapper) mapper).insert(v);
		} catch (Exception e) {
			e.getStackTrace();
			return -1;
		} finally {
			session.close();
		}
	}
	
	public static <V, T> int insertSelective(V v, Class<T> clazz) {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			T mapper = (T) session.getMapper(clazz);
			return ((BaseMapper) mapper).insert(v);
		} catch (Exception e) {
			e.getStackTrace();
			return -1;
		} finally {
			session.close();
		}
	}

	public static <K, T> T selectByPrimaryKey(K k, Class<T> clazz) {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			T mapper = (T) session.getMapper(clazz);
			return (T) ((BaseMapper) mapper).selectByPrimaryKey(k);
		} catch (Exception e) {
			e.getStackTrace();
			return null;
		} finally {
			session.close();
		}
	}
	
	public static <V, T> int updateByPrimaryKeySelective(V v, Class<T> clazz) {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			T mapper = (T) session.getMapper(clazz);
			return  ((BaseMapper) mapper).updateByPrimaryKeySelective(v);
		} catch (Exception e) {
			e.getStackTrace();
			return -1;
		} finally {
			session.close();
		}
	}
	
	public static <V, T> int updateByPrimaryKey(V v, Class<T> clazz) {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			T mapper = (T) session.getMapper(clazz);
			return  ((BaseMapper) mapper).updateByPrimaryKey(v);
		} catch (Exception e) {
			e.getStackTrace();
			return -1;
		} finally {
			session.close();
		}
	}

	public static <V, T extends BaseMapper> List<V> selectAll(Class<T> clazz) {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			T mapper = session.getMapper(clazz);
			return ((T) mapper).selectAll();
		} catch (Exception e) {
			e.getStackTrace();
			//NullPorintException  对应mapper.xml 中不存在 selectAll 方法sql 实现时
			//会报NullPointException 异常
			return null;
		} finally {
			session.close();
		}
	}

}
