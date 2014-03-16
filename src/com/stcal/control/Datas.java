package com.stcal.control;

import com.stcal.don.DCouple;
import com.stcal.don.DPersonne;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jean
 * @version 12/02/2014
 */

public class Datas {

    public static final DefaultListModel<DCouple> stages;
    public static final List<DPersonne> etu;
    public static final List<DPersonne> prof;

    static {
        stages = new DefaultListModel<DCouple>();
        etu    = new ArrayList<DPersonne>();
        //DEtudiantManager etuman = new DEtudiantManager();
        // etu    = etuman.readall();
        // TODO Imcompatiblité a cause de List
        prof   = new ArrayList<DPersonne>();
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
