package com.stcal.control;

import com.stcal.don.DPersonne;
import com.stcal.don.DProf;
import com.stcal.don.DSalle;

import javax.swing.*;
import java.util.List;

/**
 * @author Jean
 * @version 24/03/2014
 */

public class ListTools {

    public static DefaultListModel<DPersonne> list_to_default_prof(List<DProf> list){
        DefaultListModel<DPersonne> inter = new DefaultListModel<DPersonne>();
        for(DProf truc : list){
            inter.addElement(truc);
        }
        return inter;
    }

    public static DefaultListModel<String> list_to_default_salle(List<DSalle> list){
        DefaultListModel<String> inter = new DefaultListModel<String>();
        for(DSalle truc : list){
            inter.addElement(truc.toString());
        }
        return inter;
    }

}
