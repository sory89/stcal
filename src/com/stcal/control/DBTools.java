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

    public static final DBsettings dbsettings;

    static {
        dbsettings = new DBsettings();
    }

    /**
     * Traitement de base de donne au lancement de l'application
     * @return les parametre de connections;
     */
    public static void startup(){
        askdbsetting(dbsettings);
        Datas.load(dbsettings);
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                Datas.save(dbsettings);
            }
        }));
        try {
            if (!isset(dbsettings)) resetDatabase(dbsettings);
        } catch (SQLException e) {
            Message.poperror(e);
        } catch (IOException eio){
            Message.poperror(eio);
        }
    }

    /**
     * (re)cree la base de donne
     * @param param paramettres de connection
     * @return zero si pas d'erreure
     */
    public static void resetDatabase(DBsettings param) throws IOException, SQLException {
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

    /**
     * charge les parametres de la base de donne et les demandes à l'utilisateur si besoin
     * @param dbsettings
     */
    public static void askdbsetting(final DBsettings dbsettings){
        try {
            dbsettings.loadfile();
        } catch (FileNotFoundException e) {
            dbsettings.popup().pop();
        }
        do {
            try {
                dbsettings.getNewConnection();
                return;
            } catch (SQLException e){
                if(!Message.popquestion(e.getMessage()+"\nRessayer? (cancel fermera l'application)")) System.exit(1);
                dbsettings.popup().pop();
            }
        } while (true);
    }

    /**
     * detruit la connection à la base de donne precedente et en recree une autre
     */
    public static void resetConnection() throws SQLException {
        dbsettings.getNewConnection();
    }
}
