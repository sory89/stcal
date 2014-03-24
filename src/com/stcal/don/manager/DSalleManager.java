package com.stcal.don.manager;

import com.stcal.control.Message;
import com.stcal.don.DSalle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ismail on 18/03/14.
 */

public class DSalleManager implements Manager<DSalle> {
    private PreparedStatement pstm=null;
    private Connection con=null;
    private ResultSet resultSet=null;
    private Statement statement=null;

    public DSalleManager(Connection connection) {
        con=connection;
    }

    @Override
    public int create(DSalle nouveau) {
        int id=0;
        ResultSet key;
        String sql="INSERT INTO Salle(id_salle,num_salle) VALUES(NULL,?)";
        try {
            pstm=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1,nouveau.getNum());
            pstm.executeUpdate();
            key=pstm.getGeneratedKeys();
            if (key!=null && key.next()) id = key.getInt(1);
            con.commit();
            nouveau.setId(id);

        } catch (SQLException e) {
            Message.err.println(e.getMessage());
        }
        return id;
    }

    @Override
    public DSalle read(int id) {
        String sql="SELECT id_salle,num_salle FROM Salle WHERE id_salle=?";
        try {
            pstm=con.prepareStatement(sql);
            pstm.setInt(1, id);
            resultSet=pstm.executeQuery();
            resultSet.next();
            con.commit();
            return new DSalle(resultSet.getInt("id_salle"),resultSet.getInt("num_salle"));
        }  catch (SQLException e){
            try {
                con.rollback();
            }
            catch (Exception ignore){}
            Message.err.println(e.getMessage());
        }
        return null;

    }

    @Override
    public List<DSalle> readall() {
        List<DSalle> resultats = new ArrayList<DSalle>();
        String sql = "SELECT `id_salle`,`num_salle` FROM Salle";
        try {
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sql);
            con.commit();
            while (resultSet.next()){
                DSalle ok = new DSalle(resultSet.getInt("id_salle"),resultSet.getInt("num_salle"));
                ok.setId(resultSet.getInt("id_salle"));
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
    public int update(DSalle table) {
        int n = -1;
        PreparedStatement pstm = null;
        try {
            String sql = "UPDATE `Salle` SET `num_salle`=? WHERE `id_salle`=?";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, table.getNum());
            pstm.setInt(2, table.getId());
            n = pstm.executeUpdate();
            con.commit();
            if (1 != n) Message.err.println("erreur update on DSalle id: " + table.getId());
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
            String sql = "delete from `Salle` where `id_salle`=?";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, id);
            n = pstm.executeUpdate();
            con.commit();
            if (1 != n) Message.err.println("erreur delete salle id: " + id);
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