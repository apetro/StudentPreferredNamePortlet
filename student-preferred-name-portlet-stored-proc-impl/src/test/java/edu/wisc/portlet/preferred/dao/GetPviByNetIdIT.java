package edu.wisc.portlet.preferred.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/integration-test-prefnameAppContext.xml")
public class GetPviByNetIdIT extends GetPviByNetIdTestBase {
    @Before
    public void before() {
        super.before();
        this.netId = "SOMEREALNETID";
        this.expectedResult = "INSERTREALPVIHERE";
    }

    @Test
    public void testGetPvi() throws Exception {
        super.testGetPvi();
    }
}
