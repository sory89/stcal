package com.stcal.control;

import com.stcal.don.DPersonne;

import javax.swing.*;
import java.util.List;

/**
 * @author Jean
 * @version 18/03/2014
 */

public class ListTools {

    public static DefaultListModel<DPersonne> listModel(List<DPersonne> data){
        DefaultListModel<DPersonne> model = new DefaultListModel<DPersonne>();
        for(int i=0;i<data.size();i+=1) model.addElement(data.get(i));
        return model;
    }

}
