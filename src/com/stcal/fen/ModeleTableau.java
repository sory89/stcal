package com.stcal.fen;

import javax.swing.table.AbstractTableModel;

/**
 * Created with IntelliJ IDEA.
 * User: ismail
 * Date: 09/02/14
 * Time: 15:20
 * To change this template use File | Settings | File Templates.
 */

public class ModeleTableau extends AbstractTableModel {
    Object données[][];
    String titres[];
    public ModeleTableau(Object donnes[][],String titres[]){
        this.titres=titres;
        this.données=donnes;
    }

    @Override
    public int getRowCount() {
        return données.length;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getColumnCount() {
        return données[0].length;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return données[rowIndex][columnIndex];  //To change body of implemented methods use File | Settings | File Templates.
    }
    public String getValtitre(int i){
        return titres[i];
    }


}
