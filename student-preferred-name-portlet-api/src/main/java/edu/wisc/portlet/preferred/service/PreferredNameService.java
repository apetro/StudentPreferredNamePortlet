package edu.wisc.portlet.preferred.service;

import edu.wisc.portlet.preferred.form.PreferredName;

public interface PreferredNameService {
	public PreferredName getPreferredName();
	
	public boolean isPending();
	
	public void setPreferredName(PreferredName pn);
}
