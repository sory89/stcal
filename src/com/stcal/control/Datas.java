package com.stcal.control;

import com.stcal.don.DCouple;
import com.stcal.don.DCreneau;
import com.stcal.don.DListe;
import com.stcal.don.DPersonne;
import com.stcal.fen.DynamicTreeDemo;

import javax.swing.*;

/**
 * @author Jean
 * @version 12/02/2014
 */

public class Datas {

    public static final DefaultListModel<String> salles;
    public static final DefaultListModel<DCouple> stages;
    public static final DefaultListModel<DPersonne> etu;
    public static final DefaultListModel<DPersonne> prof;
    public static char Csv;

    static {
        salles = new DefaultListModel<String>();
        stages = new DefaultListModel<DCouple>();
        etu    = new DefaultListModel<DPersonne>();
        prof   = new DefaultListModel<DPersonne>();
        Csv    = ';';
    }

    public static DCreneau[][] o ;

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
