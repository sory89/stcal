package com.stcal.control;

import com.stcal.don.DCouple;
import com.stcal.don.DListe;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Jean
 * @version 12/02/2014
 */

public class Datas {

    public static DefaultListModel<DCouple> stages = new DefaultListModel<DCouple>();
    public static DListe etu = new DListe();
    public static DListe prof = new DListe();

    /**
     * Charge la base de donne et les place dans les objet de la classe
     * @param param paramettres de connection
     */
    public static void load(DBsettings param){
        if (!isset(param)){
            try {
                init(param);
            }
            catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Sauvegarde les objet de la classe dans la base de donne
     * @param param paramettres de connection
     */
    public static void save(DBsettings param){
        try {
            param.getConnection().close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * (re)cree la base de donne
     * @param param paramettres de connection
     * @return zero si pas d'erreure
     */
    private static void init(DBsettings param) throws IOException, SQLException {
        ScriptRunner runner = new ScriptRunner(param.getConnection(),false,true);
        runner.runScript(new BufferedReader(new FileReader("res/stcal.sql")));
    }

    /**
     * verfie si la base de donne est cree
     * @param param paramettres de connection
     * @return renvoie vraie si la base existe
     */
    public static boolean isset(DBsettings param){
        Connection con = null;
        try {
            con = param.getConnection();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        // return true;
        return false;
    }

}
