package service;

import org.mybatis.spring.annotation.MapperScan;

@MapperScan(value="com.gnt.mapper")
public class TestProService {
/*
	
	BaseServiceImpl baseServiceImpl;
	
	@Autowired
	ProduceMapper mapper;
	
	{
		baseServiceImpl = new BaseServiceImpl(mapper);
	}
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
	}*/

}
