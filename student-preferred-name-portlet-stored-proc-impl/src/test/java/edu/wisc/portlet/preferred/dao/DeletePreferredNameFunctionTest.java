package edu.wisc.portlet.preferred.dao;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/test-prefnameAppContext.xml")
public class DeletePreferredNameFunctionTest extends DeletePreferredNameFunctionTestBase {
	@Mock
	private DataSource ds;
	
	@Mock
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		super.before();
		DeletePreferredNameAdminFunction dao = new DeletePreferredNameAdminFunction(ds);
		jdbcTemplate.setDataSource(ds);
		dao.setJdbcTemplate(jdbcTemplate);
		super.setDao(dao);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testDelete() throws Exception {
		HashMap<String,Object> mockResult = new HashMap<String,Object>();
		mockResult.put(DeletePreferredNameAdminFunction.FUNC_RETURN, "Success");
		
		when(jdbcTemplate.call(any(CallableStatementCreator.class), any(List.class))).thenReturn(mockResult);
		super.testDelete();
	}
}
