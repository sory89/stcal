package com.stcal.control;

import com.stcal.don.DPersonne;
import com.stcal.fen.FLier;

import javax.swing.*;
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
        askdbsetting();
        try {
            isset();
        } catch (SQLException e) {
            Message.poperror(e);
        }
        Datas.load();
        System.out.println(Datas.prof);
        //FLier.Fprof = new JList<DPersonne>(Datas.prof);


        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                Datas.save();
            }
        }));
        try {
            if (!isset()) resetDatabase();
        } catch (SQLException e) {
            Message.poperror(e);
        } catch (IOException eio){
            Message.poperror(eio);
        }
    }

    /**
     * (re)cree la base de donnees
     * @return zero si pas d'erreur
     */
    public static void resetDatabase() throws IOException, SQLException {
        Message.popnotice(" La base de données va être réinitialisée.");
        ScriptRunner runner = new ScriptRunner(dbsettings.getConnection(),false,true);
        runner.runScript(new BufferedReader(new FileReader("res/stcal.sql")));
    }

    /**
     * verfie si la base de donne est cree
     * @return renvoie vraie si la base existe
     */
    public static boolean isset() throws SQLException {
        Connection con = dbsettings.getConnection();
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
     */
    public static void askdbsetting(){
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
                if(!Message.popquestion(e.getMessage()+"\nRéessayer? (cancel fermera l'application)")) System.exit(1);
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
