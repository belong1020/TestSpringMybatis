package service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gnt.entity.User;
import com.gnt.entity.UserKey;
import com.gnt.mapper.UserMapper;
import com.gnt.service.BaseServiceImpl;
import com.gnt.service.UserServiceImpl;

@MapperScan(value="com.gnt.mapper")
public class TestUserService {

//	@Resource(name="userServiceImpl")
//	BaseService baseService ;
	@Autowired
	BaseServiceImpl baseServiceImpl;
	
	@Autowired
	UserMapper mapper;
	
//	UserService userService ;
	
//	UserServiceImpl userServiceImpl;
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-mybatis.xml");
		
		// first way , dont know why @autowired can't autowired parameter.
		// this way don't need other server class except BaseService class,
//		NoUniqueBeanDefinitionException
		mapper = (UserMapper) context.getBean(UserMapper.class);
		baseServiceImpl = (BaseServiceImpl) context.getBean(BaseServiceImpl.class);
		
		baseServiceImpl.setBaseMapper(mapper);
		
		// second way
//		userServiceImpl = context.getBean(UserServiceImpl.class);
		
		// third way
//		baseServiceImpl = context.getBean(UserServiceImpl.class);
	}

	@Test
	public void testSelectByPrimaryKey() {
//		System.out.println(userService.SelectByPrimaryKey(1));
		UserKey userKey = new UserKey();
		userKey.setId(1);
		System.out.println(baseServiceImpl.selectByPrimaryKey(userKey));
	}
	
	@Test
	public void testSelectAll() {

		List<User> selectAll = baseServiceImpl.selectAll();
		
		System.out.println(selectAll);
	}

}
