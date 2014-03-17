package com.stcal.control;

import com.stcal.Main;
import com.stcal.don.DCouple;
import com.stcal.don.DEtudiant;
import com.stcal.don.DPersonne;
import com.stcal.don.DProf;
import com.stcal.don.manager.DProfManager;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jean
 * @version 12/02/2014
 */

public class Datas {

    public static final DefaultListModel<DCouple> stages;
    public static final List<DEtudiant> etu;
    public static final List<DProf> prof;

    static {
        stages = new DefaultListModel<DCouple>();
        etu    = new ArrayList<DEtudiant>();
        //DEtudiantManager etuman = new DEtudiantManager();
        // etu    = etuman.readall();
        // TODO Imcompatiblité a cause de List
        List<DProf> inter;
        try {
            DProfManager dpm = new DProfManager(DBTools.dbsettings.getConnection());
            inter   = dpm.readall();
        } catch (SQLException e) {
            e.printStackTrace();
            inter   = new ArrayList<DProf>();
        }
        prof = inter;
    }

    /**
     * Charge la base de donne et les place dans les objet de la classe
     * @param param paramettres de connection
     */
    public static void load(DBsettings param){
    }

    /**
     * Sauvegarde les objet de la classe dans la base de donne
     * @param param paramettres de connection
     */
    public static void save(DBsettings param){
    }

}
