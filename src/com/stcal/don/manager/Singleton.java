package com.stcal.don.manager;

import com.stcal.control.DBsettings;
import com.stcal.control.Message;
import com.stcal.control.exceptions.NoSuchSettingException;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * User: Jean
 * Date: 22/01/2014
 */

public class Singleton {

    public static final String DRIVER   = "com.mysql.jdbc.Driver";
    public static final String PREFIX = "jdbc:mysql://";

    private DBsettings dbsettings;
    public static final DataSource DS =  new BasicDataSource();

    public Singleton(DBsettings dbsettings){
        this.dbsettings = dbsettings;
        Message.out.println("Initializing connection");
        BasicDataSource ds = (BasicDataSource) DS;
        ds.setDriverClassName(DRIVER);
        try {
            ds.setPassword(dbsettings.get("DBPassword"));
            ds.setUsername(dbsettings.get("DBUser"));
        } catch (NoSuchSettingException e) {
            Message.err.println(e);
        }
        try {
            ds.setUrl(PREFIX + dbsettings.get("DBServer") + ":" + dbsettings.get("DBPort"));
        } catch (NoSuchSettingException e) {
            Message.err.println(e.getMessage());
        }
        ds.setDefaultAutoCommit(false);
        ds.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
    }

}
