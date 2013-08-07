package edu.wisc.portlet.preferred.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jvnet.jaxb2_commons.lang.StringUtils;
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
	public String getStatus(PreferredName ldapPn) {
		PreferredName jdbcPn = dao.getPreferredName(ldapPn.getPvi());
		if(ldapPn.getFirstName() == null && ldapPn.getMiddleName() == null 
				&& (jdbcPn == null 
					|| (StringUtils.isEmpty(jdbcPn.getFirstName()) && StringUtils.isEmpty(jdbcPn.getMiddleName()))
				   )
		) {
			return "(not set)";
		} else {
			
			if(ldapPn.equals(jdbcPn)) {
				return "";
			} else {
				return "(pending)";
			}
		}
	}

	@Override
	public void setPreferredName(PreferredName pn) {
		dao.setPreferredName(pn);

	}
	
	private boolean isValid(PreferredName pn) {
		
		String regx = "^[\\p{L} .'-]+$";
		Pattern ptrn = Pattern.compile(regx);
		Matcher fnameMatcher = ptrn.matcher(pn.getFirstName());
		Matcher mnameMatcher = ptrn.matcher(pn.getMiddleName());
		
		return fnameMatcher.find() && mnameMatcher.find();
	}

}
