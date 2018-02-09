package service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gnt.entity.ProduceKey;
import com.gnt.entity.User;
import com.gnt.entity.UserKey;
import com.gnt.mapper.BaseMapper;
import com.gnt.mapper.ProduceMapper;
import com.gnt.mapper.UserMapper;
import com.gnt.service.BaseServiceImpl;

@MapperScan(value="com.gnt.mapper")
public class TestProService {

//	@Resource(name="userServiceImpl")
//	BaseService baseService ;
	
	BaseServiceImpl baseServiceImpl;
//	UserService userService ;
	
//	UserServiceImpl userServiceImpl;
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-mybatis.xml");
//		baseService = (BaseService) context.getBean("userServiceImpl");
		baseServiceImpl = new BaseServiceImpl();
		baseServiceImpl.setBaseMapper((BaseMapper) context.getBean(ProduceMapper.class));
	
	}

	@Test
	public void testSelectByPrimaryKey() {
//		System.out.println(userService.SelectByPrimaryKey(1));
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
