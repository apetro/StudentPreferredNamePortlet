package edu.wisc.portlet.preferred.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.wisc.portlet.preferred.form.PreferredName;
import edu.wisc.portlet.preferred.form.PreferredNameExtended;

public class PreferredNameExtendedRowMapper  implements RowMapper<PreferredNameExtended> {
    public static final PreferredNameExtendedRowMapper INTANCE = new PreferredNameExtendedRowMapper();
    
    @Override
    public PreferredNameExtended mapRow(ResultSet rs, int rowNum) throws SQLException {
        final PreferredNameExtended preferredName = new PreferredNameExtended();
        
        preferredName.setFirstName(rs.getString("pref_fname"));
        preferredName.setMiddleName(rs.getString("pref_mname"));
        preferredName.setLastName(rs.getString("pref_lname"));
        preferredName.setPvi(rs.getString("pvi"));
        preferredName.setHideSource("Y".equalsIgnoreCase(rs.getString("HIDE_LEGAL_NAME")));
        
        preferredName.setLegalFirstName(rs.getString("first_name"));
        preferredName.setLegalMiddleName(rs.getString("middle_name"));
        preferredName.setLegalLastName(rs.getString("last_name"));
        
        
        return preferredName;
    }
}
