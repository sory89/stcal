package com.stcal.control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Jean
 * @version 12/03/2014
 */

public class DBTools {

    /**
     * Traitement de base de donne au lancement de l'application
     * @return les parametre de connections;
     */
    public static DBsettings startup(){
        final DBsettings param = new DBsettings();
        try {
            param.loadfile();
        } catch (FileNotFoundException e) {
            Message.out.print(e.getMessage() + " -> demande des infos à l'utilisateur.");
            askdbsetting(param);
        }
        Datas.load(param);
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                Datas.save(param);
            }
        }));
        try {
            if (!isset(param)) reset(param);
        } catch (SQLException e) {
            Message.poperror(e);
        } catch (IOException eio){
            Message.poperror(eio);
        }
        return param;
    }

    /**
     * (re)cree la base de donne
     * @param param paramettres de connection
     * @return zero si pas d'erreure
     */
    public static void reset(DBsettings param) throws IOException, SQLException {
        Message.popnotice(" La base de donnée va être réinitialisée.");
        ScriptRunner runner = new ScriptRunner(param.getConnection(),false,true);
        runner.runScript(new BufferedReader(new FileReader("res/stcal.sql")));
    }

    /**
     * verfie si la base de donne est cree
     * @param param paramettres de connection
     * @return renvoie vraie si la base existe
     */
    public static boolean isset(DBsettings param) throws SQLException {
        Connection con = param.getConnection();
        try {
            String sql = "use stcal;";
            PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.executeUpdate();
            con.commit();
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }

    public static void askdbsetting(Settings dbsettings){
        try {
            dbsettings.loadfile();
        } catch (FileNotFoundException e) {
            dbsettings.ask();
        }
    }
}
