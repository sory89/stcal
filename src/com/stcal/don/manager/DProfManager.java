package com.stcal.don.manager;

import com.stcal.don.DPersonne;
import com.stcal.don.DProf;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Jean
 * @version 16/03/2014
 */

public class DProfManager implements Manager<DProf> {

    private Connection con;
    private Statement stmt = null;
    private PreparedStatement pstm = null;
    private ResultSet rset = null;


    @Override
    public int create(DProf nouveau) {
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
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public DProf read(int id) {
        String sql = "SELECT `id_prof`, `nom_prof`, `pre_prof`, `info_prof` FROM `Professeur` WHERE `id_prof`=?";
        try {
            pstm = con.prepareStatement(sql);
            pstm.setInt(1,id);
            rset = pstm.executeQuery();
            rset.next();
            con.commit();
            DProf ok = new DProf(rset.getString("nom_prof"),rset.getString("pre_prof"), Arrays.asList(rset.getString("info_prof").split(";")));
            ok.setDb_id(rset.getInt("id_prof"));
            return ok;
        }
        catch (SQLException e){
            try {
                con.rollback();
            }
            catch (Exception ignore){}
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DProf> readall() {
        return null;
    }

    @Override
    public int update(DProf table) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }
}
