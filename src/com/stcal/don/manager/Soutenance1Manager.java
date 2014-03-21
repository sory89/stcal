package com.stcal.don.manager;

import com.stcal.control.Message;
import com.stcal.don.*;

import java.sql.*;
import java.util.List;

/**
 * Created by ismail on 19/03/14.
 */
public class Soutenance1Manager implements newManager<Soutenance> {
    private Connection con;
    private Statement stmt = null;
    private PreparedStatement pstm = null;
    private ResultSet rset = null;

    public Soutenance1Manager(Connection connection) {
        this.con=connection;
    }

//DCouple couple, DCandide candide,int salle
    @Override
    public int create(Soutenance nouveau, DSalle salle, DCreneau dCreneau) {
        int id=0;
        ResultSet key;
        String sql="INSERT INTO `soutenance`(`id_sout`, `id_salle`, `id_crenau`) VALUES (NULL,?,?)";
        try {
            pstm=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1,salle.getId());
            pstm.setInt(2,dCreneau.getId());
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
    //sout salle creneau
    public Soutenance read(int id) {
        String sql = "SELECT c.`id_etu`,c.`id_prof_candide`,c.`id_prof_tuteur`,c.`id_soutenance`,s.`id_sout`,s.`id_salle`,s.`id_crenau` " +
                     "FROM `soutenance` s,`couple` c " +
                     "WHERE s.`id_sout`=? AND c.`id_soutenance`=s.`id_sout`\n";
        try {
            pstm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1,id);
            rset = pstm.executeQuery();
            rset.next();
            con.commit();
            DEtudiant dEtudiant=new DEtudiantManager(con).read(rset.getInt("id_etu"));
            DTuteur dProf= (DTuteur) new DProfManager(con).read(rset.getInt("id_prof_tuteur"));
            DCouple dCouple=new DCouple(dEtudiant,dProf);
            DCandide dCandide= (DCandide) new DCandideManager(con).read(rset.getInt("id_prof_candide"));
            return new Soutenance(dCouple,(DCandide)rset.getObject("id_prof_candide"),rset.getInt("id_alle"));
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
    public List<Soutenance> readall() {
        return null;
    }

    @Override
        public int update(Soutenance table,DCreneau dcreneau) {
            int n = -1;
            PreparedStatement pstm = null;
            try {
                String sql = "UPDATE `soutenance` SET id_salle=?, id_crenau=? WHERE `id_sout`=?";
                pstm = con.prepareStatement(sql);
                int i = 1;
              //  pstm.setString(1,table.);
                pstm.setString(2, table.getCdd().toString());
                pstm.setInt(3,table.getSalle());
                pstm.setInt(4,table.getId());
                n = pstm.executeUpdate();
                con.commit();
                if (1 != n) Message.err.println("erreur update");
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
        return 0;
    }
}
