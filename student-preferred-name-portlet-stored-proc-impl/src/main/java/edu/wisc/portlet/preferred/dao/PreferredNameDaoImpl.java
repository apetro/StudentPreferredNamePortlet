package edu.wisc.portlet.preferred.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;

import edu.wisc.portlet.preferred.form.PreferredName;
import edu.wisc.portlet.preferred.form.PreferredNameExtended;

@Repository
public class PreferredNameDaoImpl implements PreferredNameDao  {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
    private NamedParameterJdbcOperations jdbcTemplate;
    private NamedParameterJdbcOperations jdbcTemplateAdmin;
    private UpdatePreferredNameProcedure updatePreferredName;
    private DeletePreferredNameUserFunction deletePreferredNameUserFunction;
    private DeletePreferredNameAdminFunction deletePreferredNameAdmin;
    private HideSourceFunction hideSourceFunction;
    private UnhideSourceFunction unhideSourceFunction;
    private GetPviByNetIdFunction getPviByNetIdFunction;
	
    @Autowired
    public void setDeletePreferredNameUserFunction(DeletePreferredNameUserFunction dpnuf) {
        this.deletePreferredNameUserFunction = dpnuf;
    }
	@Autowired
    public void setJdbcTemplate(@Qualifier("prefname") NamedParameterJdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
	
	@Autowired
    public void setAdminJdbcTemplate(@Qualifier("prefnameAdmin") NamedParameterJdbcOperations jdbcTemplateAdmin) {
        this.jdbcTemplateAdmin = jdbcTemplateAdmin;
    }
	
	@Autowired
    public void setUpdatePreferredName(UpdatePreferredNameProcedure updatePrefNameProc) {
        this.updatePreferredName = updatePrefNameProc;
    }
	
	@Autowired
	public void setDeletePreferredName(DeletePreferredNameAdminFunction deletePrefNameProc) {
		this.deletePreferredNameAdmin = deletePrefNameProc;
	}
	
	@Autowired
	public void setHideSourceFunction(HideSourceFunction hideSF) {
		this.hideSourceFunction = hideSF;
	}
	
	@Autowired
	public void setUnhideSourceFunction(UnhideSourceFunction unhideSF) {
		this.unhideSourceFunction = unhideSF;
	}
	
	@Autowired
	public void setGetPviFromNetId(GetPviByNetIdFunction dao) {
		this.getPviByNetIdFunction = dao;
	}

	@Override
	@Cacheable(cacheName = "prefname")
	public PreferredName getPreferredName(String pvi) {
		final Map<String, String> args = new LinkedHashMap<String, String>();
        args.put("pvi", pvi);
        
        List<PreferredName> query = jdbcTemplate.query("select first_name, middle_name, last_name, pvi, HIDE_LEGAL_NAME from msnprefname.msn_preferred_name where pvi = :pvi", 
                args, 
                PreferredNameRowMapper.INTANCE);
        
        return DataAccessUtils.singleResult(query);
	}
	
	@Override
	public PreferredNameExtended getPreferredNameExtended(String pvi) {
		final Map<String, String> args = new LinkedHashMap<String, String>();
        args.put("pvi", pvi);
        
        List<PreferredNameExtended> query = jdbcTemplateAdmin.query("SELECT PVI,PREF_FNAME,PREF_MNAME,PREF_LNAME,HIDE_LEGAL_NAME,FIRST_NAME,MIDDLE_NAME,LAST_NAME FROM MSNPREFNAME.PREFERRED_NAME_SEARCH where pvi = :pvi", 
                args, 
                PreferredNameExtendedRowMapper.INTANCE);
        
        return DataAccessUtils.singleResult(query);
	}

	@Override
	@Transactional
	@TriggersRemove(cacheName="prefname")
	public void setPreferredName(PreferredName pn) {
		updatePreferredName.updatePrefferedName(pn);
	}

	@Override
	@Transactional
	@TriggersRemove(cacheName="prefname")
	public void deletePreferredName(String pvi) {
		deletePreferredNameUserFunction.deletePreferredNameUser(pvi);
	}
	
	@Override
	@Transactional
	@TriggersRemove(cacheName="prefname")
	public boolean deletePreferredNameAdmin(String pvi) {
		return deletePreferredNameAdmin.deletePreferredNameAdmin(pvi);
	}
	
	@Override
	@Transactional
	public void updateHideSource(PreferredName pn) {
		if(pn.isHideSource()) {
			hideSourceFunction.hideSource(pn.getPvi());
		} else {
			unhideSourceFunction.unhideSource(pn.getPvi());
		}
	}

	@Override
	public String getPviFromNetId(String netId) {
		return getPviByNetIdFunction.getPviFromNetId(netId);
	}

}
