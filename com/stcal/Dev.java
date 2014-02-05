package com.stcal;

import com.stcal.control.DBsettings;
import com.stcal.control.exceptions.NoSettingFileException;
import com.stcal.control.exceptions.NoSuchSettingException;
import com.stcal.control.exceptions.UnopenableSettingException;
import com.stcal.don.manager.Singleton;

import java.sql.SQLException;

/**
 * @author Jean
 * @version 05/02/2014
 */

public class Dev {

    public static void main(String[] args) {

        DBsettings dbset = new DBsettings();
        Singleton singleton;

        try {
            dbset.loadfile();
            //dbset.set("DBUser","root");
            //dbset.set("DBPassword","root");
        } catch (NoSettingFileException e) {
            e.printStackTrace();
        } catch (UnopenableSettingException e) {
            e.printStackTrace();
        }

        singleton = new Singleton(dbset);

        try {
            singleton.DS.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
