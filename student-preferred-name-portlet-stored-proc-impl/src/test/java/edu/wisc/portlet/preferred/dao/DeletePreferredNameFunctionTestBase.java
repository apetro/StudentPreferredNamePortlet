package edu.wisc.portlet.preferred.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DeletePreferredNameFunctionTestBase {

    private DeletePreferredNameAdminFunction dao;
    protected String pvi;

    public void before() {
        pvi = "admin";
    }

    @Autowired
    public void setDao(DeletePreferredNameAdminFunction dao) {
        this.dao = dao;
    }

    @Test
    public void testDelete() throws Exception {
        assertTrue(dao.deletePreferredNameAdmin(pvi));
    }

}
