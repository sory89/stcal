package com.stcal;

import com.stcal.don.DCreneau;
import com.stcal.don.DSalle;
import com.stcal.don.manager.DSalleManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ismail on 18/03/14.
 */
public class Deb {
    public static void main(String args[]) throws SQLException, ClassNotFoundException {
        String driverName = "org.gjt.mm.mysql.Driver";
        Class.forName(driverName);

        String serverName = "localhost";
        String mydatabase = "stcal";
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase+"?autoReconnect=true&relaxAutoCommit=true";

        String username = "root";
        String password = "";
        Connection connection = DriverManager.getConnection(url, username, password);
        String DATE_FORMAT = "yyyy-MM-dd";
        SimpleDateFormat sdf =new SimpleDateFormat(DATE_FORMAT);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.set(2010,4,12);
        c2.set(2010,4,16);
        DCreneau dCreneau=new DCreneau(4,c1,c2);
        DSalleManager dSalleManager=new DSalleManager(connection);
        dSalleManager.update(new DSalle(4,105));
        System.out.print(dSalleManager.readall().get(4).getNum());
    }
}

