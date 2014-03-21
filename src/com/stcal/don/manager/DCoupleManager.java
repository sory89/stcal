package com.stcal.don.manager;

import com.stcal.control.Message;
import com.stcal.don.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ismail on 21/03/14.
 */
public class DCoupleManager implements ManagerpourCouple<DCouple> {
    private Connection con;
    private Statement stmt = null;
    private PreparedStatement pstm = null;
    private ResultSet rset = null;

    public DCoupleManager(Connection connection) {
        con=connection;
    }

    @Override
    public int create(DCouple nouveau, DCandide dCandide, Soutenance soutenance) {
        int id = 0;
        ResultSet key;
        String sql = "INSERT INTO `couple`(`id_etu`, `id_prof_candide`, `id_prof_tuteur`, `id_soutenance`) VALUES (?,?,?,?)";
        try {
            pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, nouveau.getEtu().getDb_id());
            pstm.setInt(2,dCandide.getDb_id());
            pstm.setInt(3, nouveau.getTut().getDb_id());
            pstm.setInt(4,soutenance.getId());
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
    public DCouple read(int id) {
        String sql = "SELECT  `id_etu`,`id_prof_candide`, `id_prof_tuteur`, `id_soutenance` FROM `couple` WHERE `id_etu`=?";
        try {
            pstm=con.prepareStatement(sql);
            pstm.setInt(1,id);
            rset = pstm.executeQuery();
            rset.next();
            con.commit();
            DTuteur dTuteur=new DTuteurManager(con).read(rset.getInt("id_prof_tuteur"));
            DEtudiant dEtudiant=new DEtudiantManager(con).read(rset.getInt("id_etu"));
            //  DCandide dCandide=new DCandideManager(con).read(rset.getInt("id_prof_candide"));
            // Soutenance soutenance=new Soutenance1Manager(con).read(rset.getInt(c));
            DCouple ok = new DCouple(dEtudiant,dTuteur);
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
    public List<DCouple> readall() {
        List<DCouple> resultats = new ArrayList<DCouple>();
        String sql = "SELECT  `id_etu`,`id_prof_candide`, `id_prof_tuteur`, `id_soutenance` FROM `couple`";
        try {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rset = stmt.executeQuery(sql);
            con.commit();
            while (rset.next()){
                DTuteur dTuteur=new DTuteurManager(con).read(rset.getInt("id_prof_tuteur"));
                DEtudiant dEtudiant=new DEtudiantManager(con).read(rset.getInt("id_etu"));
                DCouple ok = new DCouple(dEtudiant,dTuteur);
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
    public int update(DCouple table, DCandide dCandide, Soutenance soutenance) {
        int n=-1;
        PreparedStatement preparedStatement=null;
        try {
            String sql="UPDATE `couple` SET `id_etu`=?,`id_prof_candide`=?,`id_prof_tuteur`=?,`id_soutenance`=? WHERE `id_etu`=?";
            preparedStatement =con.prepareStatement(sql);
            preparedStatement.setInt(1,table.getEtu().getDb_id());
            preparedStatement.setInt(2, dCandide.getDb_id());
            preparedStatement.setInt(3,table.getTut().getDb_id());
            preparedStatement.setInt(4,soutenance.getId());
            preparedStatement.setInt(5,table.getEtu().getDb_id());
            preparedStatement.executeUpdate();
            n = preparedStatement.executeUpdate();
            con.commit();
            if (1 != n) Message.err.println("erreur update on couple id: " + table.getEtu().getDb_id());
        }
        catch (Exception e) {
            Message.out.println(e.getMessage());
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
            String sql = "delete from `couple` where `id_etu`=?";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, id);
            n = pstm.executeUpdate();
            con.commit();
            if (1 != n) Message.err.println("erreur delete couple id: " + id);
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
