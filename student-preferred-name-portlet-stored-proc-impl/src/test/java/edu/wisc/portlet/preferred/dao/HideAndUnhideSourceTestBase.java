package edu.wisc.portlet.preferred.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class HideAndUnhideSourceTestBase {

    private HideSourceFunction hideDao;
    private UnhideSourceFunction unhideDao;
    protected String pvi;

    public void before() {
        pvi = "admin";
    }

    @Autowired
    public void setHideDao(HideSourceFunction hd) {
        this.hideDao = hd;
    }

    @Autowired
    public void setUnhideDao(UnhideSourceFunction uhd) {
        this.unhideDao = uhd;
    }

    @Test
    public void testHideAndUnhide() throws Exception {
        assertTrue(hideDao.hideSource(pvi));
        assertTrue(unhideDao.unhideSource(pvi));
    }

}
