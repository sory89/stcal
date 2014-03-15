package com.stcal.control;

import com.stcal.don.DCouple;
import com.stcal.don.DListe;

import javax.swing.*;

/**
 * @author Jean
 * @version 12/02/2014
 */

public class Datas {

    public static DefaultListModel<DCouple> stages = new DefaultListModel<DCouple>();
    public static DListe etu = new DListe();
    public static DListe prof = new DListe();

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
