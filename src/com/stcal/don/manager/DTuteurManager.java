package com.stcal.don.manager;

import com.stcal.control.Message;
import com.stcal.don.DTuteur;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ismail on 21/03/14.
 */
public class DTuteurManager implements Manager<DTuteur> {
    private Connection con=null;
    private Statement stmt=null;
    private ResultSet rset=null;
    private PreparedStatement pstm=null;

    public DTuteurManager(Connection con) {
        this.con=con;
    }

    @Override
    public int create(DTuteur nouveau) {
        int id = 0;
        ResultSet key;
        String sql = "INSERT INTO `Professeur`(`id_prof`, `nom_prof`, `pre_prof`, `info_prof`) VALUES (NULL,?,?,?)";
        try {
            pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, nouveau.getNom());
            pstm.setString(2, nouveau.getPrenom());
            StringBuilder sb = new StringBuilder();
            for (int i=0;i<nouveau.getInfos().size();i+=1) sb.append(nouveau.getInfos().get(i) + ";");
            pstm.setString(3, sb.toString());
            pstm.executeUpdate();
            key = pstm.getGeneratedKeys();
            if (key!=null && key.next()) id = key.getInt(1);
            con.commit();
            nouveau.setDb_id(id);
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
    public DTuteur read(int id) {
        String sql = "SELECT `id_prof`, `nom_prof`, `pre_prof`, `info_prof` FROM `Professeur` WHERE `id_prof`=?";
        try {
            pstm=con.prepareStatement(sql);
            pstm.setInt(1,id);
            rset = pstm.executeQuery();
            rset.next();
            con.commit();
            DTuteur ok = new DTuteur(rset.getString("nom_prof"),rset.getString("pre_prof"), Arrays.asList(rset.getString("info_prof").split(";")));
            ok.setDb_id(rset.getInt("id_prof"));
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
    public List<DTuteur> readall() {
        List<DTuteur> resultats = new ArrayList<DTuteur>();
        String sql = "SELECT `id_prof`, `nom_prof`, `pre_prof`, `info_prof` FROM `Professeur` ";
        try {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rset = stmt.executeQuery(sql);
            con.commit();
            while (rset.next()){
                DTuteur ok = new DTuteur(rset.getString("nom_prof"),rset.getString("pre_prof"), Arrays.asList(rset.getString("info_prof").split(";")));
                ok.setDb_id(rset.getInt("id_prof"));
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
    public int update(DTuteur table) {
        int n = -1;
        PreparedStatement pstm = null;
        try {
            String sql = "UPDATE `Professeur` SET `nom_prof`=?,`pre_prof`=?,`info_prof`=? WHERE `id_prof`=?";
            pstm = con.prepareStatement(sql);
            pstm.setString(1,table.getNom());
            pstm.setString(2,table.getPrenom());
            StringBuilder sb = new StringBuilder();
            for (int i=0;i<table.getInfos().size();i+=1) sb.append(table.getInfos().get(i) + ";");
            pstm.setString(3, sb.toString());
            pstm.setInt(4, table.getDb_id());
            n = pstm.executeUpdate();
            con.commit();
            if (1 != n) Message.err.println("erreur update on DProfManager id: " + table.getDb_id());
        }
        catch (Exception e) {
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

    @Override
    public int delete(int id) {
        int n = -1;
        PreparedStatement pstm = null;
        try {
            String sql = "delete from `Professeur` where `id_prof`=?";
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
