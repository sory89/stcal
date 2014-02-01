package com.stcal.control;

/**
 * @author Jean
 * @version 01/02/2014
 */

public class DBsettings extends Settings {

    public DBsettings(){
        super("dbsettings");
        settings.put("DBUser","stcal");
        settings.put("DBPassword","stcal");
        settings.put("DBServer","localhost");
        settings.put("DBPort","3306");
    }

}
