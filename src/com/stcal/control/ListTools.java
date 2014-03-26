package com.stcal.control;

import com.stcal.don.DPersonne;
import com.stcal.don.DProf;
import com.stcal.don.DSalle;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jean
 * @version 24/03/2014
 */

/**
 * Cette class permet de faire la conversion entre les liste pour la db et les liste pour le swing
 */
public class ListTools {

    public static void list_to_default_prof(List<DProf> list){
        for(DProf truc : list){
            Datas.prof.addElement(truc);
        }
    }

    public static List<DProf> default_to_list_prof(DefaultListModel<DPersonne> all){
        List<DProf> re = new ArrayList<DProf>();
        for (int i=0;i<all.getSize();i+=1){
            re.add((DProf)all.get(i));
        }
        return re;
    }

    public static DefaultListModel<String> list_to_default_salle(List<DSalle> list){
        DefaultListModel<String> inter = new DefaultListModel<String>();
        for(DSalle truc : list){
            inter.addElement(truc.toString());
        }
        return inter;
    }

}
