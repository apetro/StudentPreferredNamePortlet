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
		return dao.getPreferredName(pvi);
	}

	@Override
	public String getStatus(PreferredName pn) {
		if(pn.getFirstName() == null && pn.getMiddleName() == null) {
			return "n/a";
		} else {
			PreferredName preferredName = dao.getPreferredName(pn.getPvi());
			if(pn.equals(preferredName)) {
				return "Complete";
			} else {
				return "Pending";
			}
		}
	}

	@Override
	public void setPreferredName(PreferredName pn) {
		dao.setPreferredName(pn);

	}

}
