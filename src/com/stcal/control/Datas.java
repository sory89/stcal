package com.stcal.control;

import com.stcal.don.*;
import com.stcal.don.manager.DEtudiantManager;
import com.stcal.don.manager.DProfManager;
import com.stcal.don.manager.DSalleManager;
import com.stcal.fen.FLier;

import javax.swing.*;
import java.sql.SQLException;

/**
 * @author Jean
 * @version 12/02/2014
 */

public class Datas {

    public static DefaultListModel<String> salles;
    public static DefaultListModel<DCouple> stages;
    public static DefaultListModel<DPersonne> etu;
    public static DefaultListModel<DPersonne> prof;
    public static char Csv;

    static {
        salles = new DefaultListModel<String>();
        stages = new DefaultListModel<DCouple>();
        etu    = new DefaultListModel<DPersonne>();
        prof   = new DefaultListModel<DPersonne>();
        Csv    = ';';
    }

    public static DCreneau[][] o;

    /**
     * Charge la base de donne et les place dans les objet de la classe
     */
    public static void load(){
        System.out.println("coucou" + prof);
        DProfManager profManager;
        DEtudiantManager etuManager;
        DSalleManager salleManager;
        try {
            salleManager = new DSalleManager(DBTools.dbsettings.getConnection());
            etuManager = new DEtudiantManager(DBTools.dbsettings.getConnection());
            profManager = new DProfManager(DBTools.dbsettings.getConnection());
            ListTools.list_to_default_prof(profManager.readall());
            etu = ListTools.list_to_default_etu(etuManager.readall());
            salles = ListTools.list_to_default_salle(salleManager.readall());
        } catch (SQLException e) {
            Message.poperror(e);
        }
        System.out.println("coucou " + prof);
    }

    /**
     * Sauvegarde les objet de la classe dans la base de donne
     */
    public static void save(){
        try {
            DProfManager mprof = new DProfManager(DBTools.dbsettings.getConnection());
            for(DProf tosave : ListTools.default_to_list_prof(prof)) {
                if (tosave.getDb_id()!=-1) mprof.update(tosave);
                if (tosave.getDb_id()==-1) mprof.create(tosave);
            };
            DEtudiantManager metu = new DEtudiantManager(DBTools.dbsettings.getConnection());
            for(DEtudiant tosave : ListTools.default_to_list_etu(etu)){
                if (tosave.getDb_id()!=-1) metu.update(tosave);
                if (tosave.getDb_id()==-1) metu.create(tosave);
            }
        } catch (SQLException e) {
            Message.poperror(e);
        }
    }

}
