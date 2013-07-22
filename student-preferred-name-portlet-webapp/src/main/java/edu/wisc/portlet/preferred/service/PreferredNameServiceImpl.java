package edu.wisc.portlet.preferred.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.wisc.portlet.preferred.dao.PreferredNameDao;
import edu.wisc.portlet.preferred.form.PreferredName;
import edu.wisc.portlet.preferred.service.PreferredNameService;

@Service
public class PreferredNameServiceImpl implements PreferredNameService {

	private PreferredNameDao dao;

	@Autowired
	public void setPreferredNameDao(PreferredNameDao dao) {
		this.dao = dao;
	}
	
	@Override
	public PreferredName getPreferredName(String pvi) {
		dao.getPreferredName(pvi)
	}

	@Override
	public boolean isPending() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setPreferredName(PreferredName pn) {
		// TODO Auto-generated method stub

	}

}
