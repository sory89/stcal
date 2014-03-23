package com.stcal.don.manager;

import com.stcal.control.Message;
import com.stcal.don.DProf;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: val
 * Date: 23/03/14
 * Time: 00:08
 * To change this template use File | Settings | File Templates.
 */
public class SalleManager implements Manager<String> {
    private Connection con;
    private Statement stmt = null;
    private PreparedStatement pstm = null;
    private ResultSet rset = null;

    public SalleManager(Connection con) {
        this.con = con;
    }

    @Override
    public int create(String nouveau) {
        int id = 0;
        ResultSet key;
        String sql = "INSERT INTO `Salle`(`id_salle`, `libelle_salle`) VALUES (NULL,?)";
        try {
            pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, nouveau);
            pstm.executeUpdate();
            key = pstm.getGeneratedKeys();
            if (key!=null && key.next()) id = key.getInt(1);
            con.commit();
        }
        catch (SQLException e){
            try {
                con.rollback();
            }
            catch (Exception ignore){}
            Message.err.println(e.getMessage());
        }
        return id;
    }

    @Override
    public String read(int id) {
        String sql = "SELECT `id_salle`, `libelle_salle` FROM `Salle` WHERE `id_salle`=?";
        try {
            pstm = con.prepareStatement(sql);
            pstm.setInt(1,id);
            rset = pstm.executeQuery();
            rset.next();
            con.commit();
            ArrayList<String> listInfos = new ArrayList<String>();
            for (String str : Arrays.asList(rset.getString("info_prof").split(";"))){
                listInfos.add(str);
            }
            String ok = rset.getString("libelle_salle");
            return ok;
        }
        catch (SQLException e){
            try {
                con.rollback();
            }
            catch (Exception ignore){}
            Message.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<String> readall() {
        List<String> resultats = new ArrayList<String>();
        String sql = "SELECT `id_salle`, `libelle_salle` FROM `Salle` WHERE 1";
        try {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rset = stmt.executeQuery(sql);
            con.commit();
            while (rset.next()){
                String ok = rset.getString("libelle_salle");
                resultats.add(ok);
            }
        }
        catch (SQLException e){
            try {
                con.rollback();
            }
            catch (Exception ignore){}
            Message.err.println(e.getMessage());
        }
        return resultats;
    }

    @Override
    public int update(String table) {
        return Integer.parseInt(null);
    }

    @Override
    public int delete(int id) {
        int n = -1;
        PreparedStatement pstm = null;
        try {
            String sql = "delete from `Salle` where `id_salle`=?";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, id);
            n = pstm.executeUpdate();
            con.commit();
            if (1 != n) Message.err.println("erreur delete DProfManager id: " + id);
        }
        catch (SQLException e) {
            Message.err.println(e.getMessage());
            try {
                con.rollback();
                pstm.close();
            }
            catch (Exception localException1) {  }
        }
        finally {
            try {
                pstm.close();
            }
            catch (Exception localException2) { }
        }
        return n;
    }
}
