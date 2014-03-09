package com.stcal.don.manager;

import com.stcal.don.DCandide;
import com.stcal.don.DCouple;
import com.stcal.don.Soutenance;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ismail on 09/03/14.
 */
public class SoutenanceManager implements Manager<Soutenance> {

    private Connection con;
    private Statement stmt = null;
    private PreparedStatement pstm = null;
    private ResultSet rset = null;

    public SoutenanceManager(Connection con){
        this.con=con;
    }

    @Override
    public int create(Soutenance nouveau) {
        int id=0;
        ResultSet key;
        String sql="INSERT INTO `soutenance`('id_soutenance`,`couple`,`candide`,`salle`) VALUES(?,?,?,?)";
        try {
            pstm=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1,nouveau.getCpl().toString());
            pstm.setString(2,nouveau.getCdd().toString());
            pstm.setInt(3,nouveau.getSalle());
            pstm.executeUpdate();
            key=pstm.getGeneratedKeys();
            if(key!=null && key.next()) id=key.getInt(1);
            con.commit();
            nouveau.setId(id);
        }
        catch (SQLException e){
            try {
                con.rollback();
            }
            catch (Exception ignore){}
            e.printStackTrace();
        }

        return id;
    }

    @Override
    public Soutenance read(int id) {
        String sql = "SELECT id_soutenance,couple,candide,salle FROM soutenance WHERE id_soutenance=?";
        try {
            pstm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1,id);
            rset = pstm.executeQuery();
            rset.next();
            con.commit();
            Manager cat = new SoutenanceManager(con);
            return new Soutenance(rset.getInt("id_soutenance"),(DCouple)rset.getObject("couple"),(DCandide)rset.getObject("candide"),rset.getInt("salle"));
        }
        catch (SQLException e){
            try {
                con.rollback();
            }
            catch (Exception ignore){}
            e.printStackTrace();
        }
        return null;    }

    @Override
    public List<Soutenance> readall() {
        List<Soutenance> resultats = new ArrayList<Soutenance>();
        String sql = "SELECT id_soutenance,couple,candide,salle FROM soutenance";
        try {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rset = stmt.executeQuery(sql);
            con.commit();
            while (rset.next()){
                resultats.add(new Soutenance(rset.getInt("id_soutenance"),(DCouple) rset.getObject("couple"), (DCandide) rset.getObject("candide"),rset.getInt("salle")));
            }
        }
        catch (SQLException e){
            try {
                con.rollback();
            }
            catch (Exception ignore){}
            e.printStackTrace();
        }
        return resultats;
    }

    @Override
    public int update(Soutenance table) {
        int n = -1;
        PreparedStatement pstm = null;
        try {
            String sql = "UPDATE article SET couple=?, candide=?, salle=? WHERE id_soutenance=?";
            pstm = con.prepareStatement(sql);
            int i = 1;
            pstm.setString(1,table.getCpl().toString());
            pstm.setString(2, table.getCdd().toString());
            pstm.setInt(3,table.getSalle());
            pstm.setInt(4,table.getId());
            n = pstm.executeUpdate();
            con.commit();
            if (1 != n) System.out.println("erreur update");
        }
        catch (Exception e) {
            e.printStackTrace();
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
            String sql = "delete from soutenance where id_soutenance=?";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1,id);
            n = pstm.executeUpdate();
            con.commit();
            if (1 != n) System.err.println("erreur delete soutenance manager");
        }
        catch (SQLException e) {
            e.printStackTrace();
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