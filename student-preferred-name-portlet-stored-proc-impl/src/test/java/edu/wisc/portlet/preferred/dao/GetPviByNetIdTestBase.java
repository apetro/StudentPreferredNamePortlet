package edu.wisc.portlet.preferred.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class GetPviByNetIdTestBase {
	private GetPviByNetIdFunction dao;
	protected String netId;
	protected String expectedResult;
	
	public void before() {
		netId = "admin";
		expectedResult = "admin";
		
	}
	
	@Autowired
	public void setDao (GetPviByNetIdFunction dao) {
		this.dao = dao;
	}

	@Test
	public void testGetPvi() throws Exception {
		assertEquals(expectedResult,dao.getPviFromNetId(netId));
	}
}
