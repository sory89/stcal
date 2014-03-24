package com.stcal.control;

import com.stcal.don.DCouple;
import com.stcal.don.DCreneau;
import com.stcal.don.DPersonne;
import com.stcal.don.manager.DSalleManager;

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
        try {
            //DProfManager profManager = new DProfManager(DBTools.dbsettings.getConnection());
            //prof = ListTools.list_to_default_prof(profManager.readall());
            DSalleManager salleManager = new DSalleManager(DBTools.dbsettings.getConnection());
            salles = ListTools.list_to_default_salle(salleManager.readall());
        } catch (SQLException e) {
            Message.poperror("Impossible de charger la base de données.\n" + e.getMessage());
        }
    }

    /**
     * Sauvegarde les objet de la classe dans la base de donne
     */
    public static void save(){
    }

}
