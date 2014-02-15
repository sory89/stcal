package com.stcal.don;

import com.stcal.Main;

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
          int i;
        int y;
        for (y=0;y<table.getRowCount();y++){
            for(i=0;i<table.getColumnCount();i++)  {

                if(Main.colors[y][i]=="red"){
                 if(column==i && row==y)
                     c.setBackground(Color.red);


                }else if(Main.colors[y][i]=="green"){

                    if(column==i && row==y)
                        c.setBackground(Color.green);
                }else{


                    if(column==i && row==y)
                        c.setBackground(Color.white);
                }





            }}

        return c;
    }
}