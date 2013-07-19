package edu.wisc.portlet.preferred.web;

import org.springframework.stereotype.Service;

import edu.wisc.portlet.preferred.form.PreferredName;
import edu.wisc.portlet.preferred.service.PreferredNameService;

@Service
public class PreferredNameServiceImpl implements PreferredNameService {

	public PreferredNameServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PreferredName getPreferredName() {
		return new PreferredName("super","awesome");
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
