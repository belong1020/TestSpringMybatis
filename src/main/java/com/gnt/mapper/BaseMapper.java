package com.gnt.mapper;

import java.util.List;

/**
 * BaseMapper of other Mapper class.
 * All of Sql Metohd Interface statement in this class, 
 * other subclass(Mapper class) extend this with empty, 
 * and the Mapper.xml that correspond of subclass choose 
 * which SQL statement of SQL method have to achieve. 
 * 
 * @author Belong.
 * @param <K>		xxxKey
 * @param <V>		xxx
 */
public interface BaseMapper<K, V> {
	/**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table produce
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(K k);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table produce
     *
     * @mbg.generated
     */
    int insert(V v);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table produce
     *
     * @mbg.generated
     */
    int insertSelective(V v);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table produce
     *
     * @mbg.generated
     */
    V selectByPrimaryKey(K k);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table produce
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(V v);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table produce
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(V v);
    
    List<V> selectAll();
}
