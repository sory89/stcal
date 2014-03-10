package com.stcal.control;

import com.stcal.don.manager.Singleton;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Jean
 * @version 01/02/2014
 */

public class DBsettings extends Settings {

    protected Connection con = null;

    public DBsettings(){
        super("dbsettings");
        settings.put("DBUser","stcal");
        settings.put("DBPassword","stcal");
        settings.put("DBServer","localhost");
        settings.put("DBPort","3306");
    }

    /**
     * cree une nouvel connection a partir des parametre de connection
     * @return SQL Connection
     * @throws SQLException
     */
    public Connection getNewConnection() throws SQLException {
        Singleton sing = new Singleton(this);
        if (con!=null) con.close();
        con = sing.DS.getConnection();
        return con;
    }

    /**
     * renvoie toujours la meme connection
     * @return SQL Connection
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        if (con == null){
            getNewConnection();
        }
        return con;
    }

}
