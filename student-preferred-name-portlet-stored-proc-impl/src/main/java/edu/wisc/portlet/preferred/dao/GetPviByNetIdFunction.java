package edu.wisc.portlet.preferred.dao;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

public class GetPviByNetIdFunction extends StoredProcedure {
    private static final String SQL = "PASE.get_pvi_by_netid";
    private static final String NETID = "p_netid_i";
    protected static final String PVI = "pvi";

    public GetPviByNetIdFunction(DataSource dataSource) {
        setDataSource(dataSource);
        setFunction(true);
        setSql(SQL);
        //Note that the return statement must be declared first
        declareParameter(new SqlOutParameter(PVI, Types.VARCHAR));
        declareParameter(new SqlParameter(NETID, Types.VARCHAR));

        this.compile();
    }

    public String getPviFromNetId(String netId) {
        final Map<String, String> args = new HashMap<String, String>();
        args.put(NETID, netId);
        Map<String, Object> results = execute(args);
        return (String) results.get(PVI);
    }

}
