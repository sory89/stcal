package com.stcal.control.manager;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * User: Jean
 * Date: 22/01/2014
 */

public class Singleton {

    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost/vente";
    public static final String USER = "stcal";
    public static final String PASSWORD = "stcal";

    public static final DataSource DS =  new BasicDataSource();
    static {
        System.out.println("Initializing connection");
        BasicDataSource ds = (BasicDataSource) DS;
        ds.setDriverClassName(DRIVER);
        ds.setUsername(USER);
        ds.setPassword(PASSWORD);
        ds.setUrl(URL);
        ds.setDefaultAutoCommit(false);
        ds.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
    }

}
