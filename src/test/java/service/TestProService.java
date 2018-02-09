package service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gnt.entity.ProduceKey;
import com.gnt.mapper.BaseMapper;
import com.gnt.mapper.ProduceMapper;
import com.gnt.service.BaseServiceImpl;

@MapperScan(value="com.gnt.mapper")
public class TestProService {

	
	BaseServiceImpl baseServiceImpl;
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-mybatis.xml");
		baseServiceImpl = new BaseServiceImpl((BaseMapper) context.getBean(ProduceMapper.class));
	
	}

	@Test
	public void testSelectByPrimaryKey() {
		ProduceKey proKey = new ProduceKey();
		proKey.setId(1);
		proKey.setType(1);
		System.out.println(baseServiceImpl.selectByPrimaryKey(proKey));
	}
	
	@Test
	public void testSelectAll() {

		List selectAll = baseServiceImpl.selectAll();
		
		System.out.println(selectAll);
	}

}
