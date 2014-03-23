package com.stcal.control;

import com.stcal.Main;
import com.stcal.don.DCreneau;
import com.stcal.don.Soutenance;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
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

        JPanel panel = new JPanel(new BorderLayout());
        JPanel timePanel = new JPanel();
        JPanel soutPanel = new JPanel();
        timePanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        //timePanel.setOpaque(false);
        timePanel.add(new JLabel(creneau.toString()));
        soutPanel.setOpaque(false);
        soutPanel.setLayout(new BoxLayout(soutPanel,BoxLayout.Y_AXIS));
        for(Soutenance sout : creneau.getListSoutenance()){
            soutPanel.add(new JLabel(sout.getCpl().toString()));
        }

        panel.add(timePanel, BorderLayout.NORTH );
        panel.add(soutPanel, BorderLayout.CENTER);

        for (int y=0;y<table.getRowCount();y++){
            for(int i=0;i<table.getColumnCount();i++)  {

                if(Main.colors[y][i]=="red"){
                    if(column==i && row==y)
                        panel.setBackground(Color.red);


                }else if(Main.colors[y][i]=="green"){

                    if(column==i && row==y)
                        panel.setBackground(Color.green);
                }else{

                    if(column==i && row==y)
                        panel.setBackground(Color.white);
                    if(hasFocus){
                        panel.setBackground(new Color(82,166,250));
                        timePanel.setBackground(new Color(98,127,157));
                    }
                }
            }
        }
        return panel;
    }
}