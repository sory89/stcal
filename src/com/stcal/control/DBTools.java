package com.stcal.control;

import com.stcal.Main;
import com.stcal.control.exceptions.NoSettingFileException;
import com.stcal.control.exceptions.NothingToSaveException;
import com.stcal.control.exceptions.UncreatableSettingException;
import com.stcal.control.exceptions.UnopenableSettingException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

/**
 * @author Jean
 * @version 12/03/2014
 */

public class DBTools {

    /**
     * fonction appellé au lancement du programe
     */
    public static DBsettings startup(){
        final DBsettings param = new DBsettings();
        try {
            param.loadfile();
        } catch (NoSettingFileException e) {
            askdbsetting(param);
        } catch (UnopenableSettingException e) {
            Message.poperror("Impossible de charger le fichier de config");
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
        }
        catch (UnopenableSettingException e) {
            Main.fenStatut("Impossible de charger le fichier de config");
        } catch (NoSettingFileException e) {
            try {
                dbsettings.save();
            } catch (UncreatableSettingException e1) {
                Main.fenStatut("Impossible de creer le fichier de config");
            } catch (NothingToSaveException e1) {}
        }
        dbsettings.ask();
    }
}
