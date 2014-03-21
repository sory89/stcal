package com.stcal.don.manager;

import com.stcal.control.Message;
import com.stcal.don.DEtudiant;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Jean
 * @version 15/03/2014
 */

public class DEtudiantManager implements Manager<DEtudiant> {
    private PreparedStatement pstm=null;
    private Connection con=null;
    private ResultSet resultSet=null;
    private Statement statement;

    public DEtudiantManager(Connection con){
        this.con=con;
    }

    public DEtudiantManager() {

    }

    @Override
    public int create(DEtudiant nouveau) {
        int id=0;
        ResultSet key;
        String sql="INSERT INTO `etudiants`(`etu_id`, `nom_etu`, `pre_etu`, `info_etu`, `lie_etu`) VALUES (NULL,?,?,?,?)";
        try {
            pstm=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1,nouveau.getNom());
            pstm.setString(2,nouveau.getPrenom());
            pstm.setString(3,nouveau.getInfos().toString());
            pstm.setBoolean(4,nouveau.getLie());
            pstm.executeUpdate();
            key=pstm.getGeneratedKeys();
            if (key!=null && key.next()) id = key.getInt(1);
            con.commit();
            nouveau.setDb_id(id);

        } catch (SQLException e) {
            Message.err.println(e.getMessage());
        }
        return id;
    }

    @Override
    public DEtudiant read(int id) {


        String sql="SELECT `etu_id`, `nom_etu`, `pre_etu`, `info_etu`, `lie_etu` FROM `etudiants` WHERE `etu_id`=?";
        try {
            pstm=con.prepareStatement(sql);
            pstm.setInt(1, id);
            resultSet=pstm.executeQuery();
            resultSet.next();
            con.commit();
            return new DEtudiant(resultSet.getString("nom_etu"),resultSet.getString("pre_etu"), Arrays.asList(resultSet.getString("info_etu").split(";")),resultSet.getBoolean("lie_etu"));
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
    public List<DEtudiant> readall() {
        List <DEtudiant> resultats=new ArrayList<DEtudiant>();
        String sql="SELECT `etu_id`, `nom_etu`, `pre_etu`, `info_etu`, `lie_etu` FROM `etudiants`";
        try {
            statement=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            resultSet=statement.executeQuery(sql);
            con.commit();
            while (resultSet.next()){
                resultats.add(new DEtudiant(resultSet.getString("nom_etu"),resultSet.getString("pre_etu"), Arrays.asList(resultSet.getString("info_etu").split(";")),resultSet.getBoolean("lie_etu")));
            }
        }
        catch (SQLException e) {
            try {
                con.rollback();
            }
            catch (Exception ignore){}
            Message.err.println(e.getMessage());
        }
        return resultats;
    }

    @Override
    public int update(DEtudiant table) {
        int n=-1;
        PreparedStatement preparedStatement=null;

        try {
            String sql="UPDATE `etudiants` SET `nom_etu`=?,`pre_etu`=?,`info_etu`=?,`lie_etu`=? WHERE `etu_id`=?";
            preparedStatement=con.prepareStatement(sql);
            int i=1;
            preparedStatement.setString(1,table.getNom());
            preparedStatement.setString(2, table.getPrenom());
            StringBuilder sb = new StringBuilder();
            for (int j=0;j<table.getInfos().size();j+=1) sb.append(table.getInfos().get(j) + ";");
            preparedStatement.setString(3, sb.toString());
            preparedStatement.setBoolean(4,table.getLie());
            preparedStatement.setInt(5,table.getDb_id());
            n = preparedStatement.executeUpdate();
            con.commit();
            if (1 != n) Message.err.println("erreur update on DSalle id: " + table.getDb_id());
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
        int n=-1;
        PreparedStatement preparedStatement=null;
        try{
            String sql="DELETE FROM `etudiants` WHERE `etu_id`=?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            n = preparedStatement.executeUpdate();
            con.commit();
            if (1 != n) Message.err.println("erreur delete salle id: " + id);

        }
        catch(SQLException e){
            Message.out.print(e.getMessage());
            try {
                con.rollback();
                preparedStatement.close();
            }
            catch (Exception localException1){}
        }
        finally {
            try {
                pstm.close();
            }
            catch (Exception localException2){}
        }
        return n;
    }
}
