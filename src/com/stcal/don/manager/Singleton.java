package com.stcal.don.manager;

import com.stcal.control.DBsettings;
import com.stcal.control.exceptions.NoSuchSettingException;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * User: Jean
 * Date: 22/01/2014
 */

public class Singleton {

    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost/stcal";

    private DBsettings dbsettings;
    public final DataSource DS =  new BasicDataSource();

    public Singleton(DBsettings dbsettings){
        this.dbsettings = dbsettings;
        System.out.println("Initializing connection");
        BasicDataSource ds = (BasicDataSource) DS;
        ds.setDriverClassName(DRIVER);
        try {
            ds.setPassword(dbsettings.get("DBPassword"));
            ds.setUsername(dbsettings.get("DBUser"));
        } catch (NoSuchSettingException e) {
            System.err.println("Singleton: no db user or no db password");
        }
        ds.setUrl(URL);
        ds.setDefaultAutoCommit(false);
        ds.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
    }

}
