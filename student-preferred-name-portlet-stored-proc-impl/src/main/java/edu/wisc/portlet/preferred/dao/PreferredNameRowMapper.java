package edu.wisc.portlet.preferred.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.wisc.portlet.preferred.form.PreferredName;

public class PreferredNameRowMapper  implements RowMapper {
    public static final PreferredNameRowMapper INTANCE = new PreferredNameRowMapper();
    
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        final PreferredName preferredName = new PreferredName();
        
        preferredName.setFirstName(rs.getString("firstname"));
        preferredName.setMiddleName(rs.getString("middlename"));
        preferredName.setPvi(rs.getString("pvi"));
        
        return preferredName;
    }
}
