package com.stcal.don.manager;

import com.stcal.control.Message;
import com.stcal.don.DCreneau;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ismail on 19/03/14.
 */
public class DCreneauManager implements Manager<DCreneau> {
    private PreparedStatement pstm=null;
    private Connection con=null;
    private ResultSet resultSet=null;
    private Statement statement=null;

    public DCreneauManager(Connection connection) {
        con=connection;
    }

    @Override
    public int create(DCreneau nouveau) {
        int id=0;
        ResultSet key;
        String sql="INSERT INTO `creneau`(`id_creneau`, `max_soutenance`, `date-debut`, `date-fin`) VALUES (NULL,?,?,?)";
        try {
            pstm=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, nouveau.getMax_sout());
            String DATE_FORMAT = "yyyy-MM-dd";
            SimpleDateFormat sdf =new SimpleDateFormat(DATE_FORMAT);
            pstm.setString(2,sdf.format(nouveau.getDate_debut().getTime()));
            pstm.setString(3,sdf.format(nouveau.getDate_fin().getTime()));
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
    /*
     *
     *TODO utiliser la funtion read de soutenance selon l'id du creneau
     *
     */


    @Override
    public DCreneau read(int id) {
        String sql = "SELECT `id_creneau`, `max_soutenance`, `date-debut`, `date-fin` FROM `creneau` WHERE `id_creneau`=?";
        try {
            pstm = con.prepareStatement(sql);
            pstm.setInt(1,id);
            resultSet = pstm.executeQuery();
            resultSet.next();
            con.commit();
            Calendar calendar=Calendar.getInstance();
            calendar.setTime((Date)resultSet.getObject("date-debut"));
            Calendar calendar1=Calendar.getInstance();
            calendar1.setTime((Date) resultSet.getObject("date-fin"));
            DCreneau ok = new DCreneau(resultSet.getInt("max_soutenance"),calendar,calendar1);
            ok.setId(resultSet.getInt("id_creneau"));
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
    public List<DCreneau> readall() {
        List<DCreneau> resultats = new ArrayList<DCreneau>();
        String sql = "SELECT `id_creneau`, `max_soutenance`, `date-debut`, `date-fin` FROM `creneau`";
        try {
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            resultSet= pstm.executeQuery(sql);
            con.commit();
            while (resultSet.next()){
                Calendar calendar=Calendar.getInstance();
                calendar.setTime((Date)resultSet.getObject("date-debut"));
                Calendar calendar1=Calendar.getInstance();
                calendar1.setTime((Date) resultSet.getObject("date-fin"));
                DCreneau ok = new DCreneau(resultSet.getInt("max_soutenance"),calendar,calendar1);
                ok.setId(resultSet.getInt("id_creneau"));
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
    public int update(DCreneau table) {
        int n = -1;
        PreparedStatement pstm = null;
        try {
            String sql = "UPDATE `creneau` SET `max_soutenance`=?,`date-debut`=?,`date-fin`=? WHERE `id_creneau`=?";
            pstm=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, table.getMax_sout());
            String DATE_FORMAT = "yyyy-MM-dd";
            SimpleDateFormat sdf =new SimpleDateFormat(DATE_FORMAT);
            pstm.setString(2,sdf.format(table.getDate_debut().getTime()));
            pstm.setString(3,sdf.format(table.getDate_fin().getTime()));
            pstm.executeUpdate();
            n = pstm.executeUpdate();
            con.commit();
            if (1 != n) Message.err.println("erreur update on DProfManager id: " + table.getId());
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
            String sql = "delete from `creneau` where `id_creneau`=?";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, id);
            n = pstm.executeUpdate();
            con.commit();
            if (1 != n) Message.err.println("erreur delete creneau id: " + id);
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
