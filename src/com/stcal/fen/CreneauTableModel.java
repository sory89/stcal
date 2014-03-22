package com.stcal.fen;

import com.stcal.don.DCreneau;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: val
 * Date: 22/03/14
 * Time: 09:06
 * To change this template use File | Settings | File Templates.
 */
public class CreneauTableModel extends AbstractTableModel{
    public DCreneau[][] crn;
    public ArrayList<Calendar> datesDesJours;
    public int nbJoursColumn;
    public int creneauxJourRow;

    public CreneauTableModel(DCreneau[][] crn, int creneauxJour, ArrayList<Calendar> jours){
        datesDesJours = jours;
        this.creneauxJourRow = creneauxJour;
        this.nbJoursColumn = jours.size();
        this.crn = new DCreneau[creneauxJourRow][nbJoursColumn];
        for (int k = 0; k < nbJoursColumn; k++) {
            for (int i = 0; i < creneauxJourRow; i++) {
                this.crn[i][k] = crn[i][k];
            }
        }

    }

    public String getColumnName(int columnIndex){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy" );
        String str = sdf.format(datesDesJours.get(columnIndex).getTime())+"\n";
        return str;
    }
    public Class getColumnClass(int columnIndex){ return DCreneau.class; }
    public int getRowCount() { return creneauxJourRow;  }
    public int getColumnCount() { return nbJoursColumn; }
    public Object getValueAt(int rowIndex, int columnIndex) { return crn[rowIndex][columnIndex]; }
    public boolean isCellEditable(int row, int column) {
        return false;
    }

}
