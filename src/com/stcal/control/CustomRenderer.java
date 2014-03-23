package com.stcal.control;

import com.stcal.Main;
import com.stcal.don.DCreneau;
import com.stcal.don.Soutenance;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Mehdi
 * Date: 14/02/14
 * Time: 07:58
 * To change this template use File | Settings | File Templates.
 */
public class CustomRenderer extends DefaultTableCellRenderer
{
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        DCreneau creneau = (DCreneau)value;

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(new JLabel(creneau.toString() ));
        for(Soutenance sout : creneau.getListSoutenance()){
            panel.add(new JLabel(sout.getCpl().toString()));
        }

        for (int y=0;y<table.getRowCount();y++){
            for(int i=0;i<table.getColumnCount();i++)  {

                if(Main.colors[y][i]=="red"){
                    if(column==i && row==y)
                        panel.setBackground(Color.red);


                }else if(Main.colors[y][i]=="green"){

                    if(column==i && row==y)
                        panel.setBackground(Color.green);
                }else{
                    if(hasFocus==true){
                        System.out.println("c'est focus");
                        panel.setBackground(Color.orange);
                    }

                    if(column==i && row==y)
                        panel.setBackground(Color.white);
                }
            }
        }
        return panel;
    }
}