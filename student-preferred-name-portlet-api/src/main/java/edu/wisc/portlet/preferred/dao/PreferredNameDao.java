package edu.wisc.portlet.preferred.dao;

import edu.wisc.portlet.preferred.form.PreferredName;

public interface PreferredNameDao {
	public PreferredName getPreferredName(String pvi);
	
	public void setPreferredName(PreferredName pn);
	
	public void deletePreferredName(String pvi);

	public boolean deletePreferredNameAdmin(String pvi);

	public void updateHideSource(PreferredName pn);

	public String getPviFromNetId(String netId);
}
