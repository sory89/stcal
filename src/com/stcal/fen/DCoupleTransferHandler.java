package com.stcal.fen;

import com.stcal.Main;
import com.stcal.control.Datas;
import com.stcal.control.Message;
import com.stcal.don.DCouple;
import com.stcal.don.DCreneau;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class DCoupleTransferHandler extends TransferHandler {

    DataFlavor DCFlavor = new DataFlavor(com.stcal.don.DCouple.class, "DCouple");
    DataFlavor[] flavors = new DataFlavor[] {DCFlavor};
    JList liste = null;
    JTable jt=null;
    /**
     * Implemented to return true if the support can provide string values.
     */
    @Override
    public boolean canImport(TransferSupport support) {
        if (!support.isDrop()) {

            return false;
        }
        if (!(support.getComponent() instanceof JTable)) {

            return false;
        }
        return isSupportedType(support);
    }


    @Override
    public boolean importData(TransferSupport support) {
        if (!canImport(support)) {
            return false;
        }
        JTable.DropLocation dl = (JTable.DropLocation)support.getDropLocation();
        try {

            DCouple dc = (DCouple) support.getTransferable().getTransferData(DCFlavor);

            JTable table = (JTable) support.getComponent();
           jt=table;
            DCreneau dcc = ( DCreneau) table.getValueAt(dl.getRow(),dl.getColumn());







            if(dcc.isProfIn(dc.getTut())  ){
             if (dcc.toStringtest().size() < dcc.getMax_sout()){
            dcc.addSBC(dc);

            Datas.stages.removeElement(dc);  }
                else{
                 Message.err.println("Nombre de soutenances dans ce créneau atteint !");
             }
            }
            else{
                Message.err.println("Impossible : le professeur assiste déjà à une soutenance à cet horaire.");
            }
            int i;
            int y;
            for (y=0;y<jt.getRowCount();y++){
                for(i=0;i<jt.getColumnCount();i++)  {

                    Main.colors[y][i]="white";
                }     }
            jt.revalidate();
            jt.repaint();

            return true;
        } catch (UnsupportedFlavorException e) {
            Message.err.println(e.getMessage());
        } catch (IOException e) {
            Message.err.println(e.getMessage());
        }
        return false;
    }


    @Override
    protected Transferable createTransferable(JComponent c) {
        if (!(c instanceof JList)) return null;
        final JList list = (JList) c;
        final DCouple person = (DCouple) list.getSelectedValue();


        Transferable t = new Transferable() {

            @Override
            public DataFlavor[] getTransferDataFlavors() {
                return flavors;
            }

            @Override
            public boolean isDataFlavorSupported(DataFlavor flavor) {
                return DCFlavor.equals(flavor);
            }

            @Override
            public Object getTransferData(DataFlavor flavor)
                    throws UnsupportedFlavorException, IOException {

                return person;
            }

        };



        return t;
    }



    @Override
    public int getSourceActions(JComponent c) {
        return MOVE;
    }


    private boolean isSupportedType(TransferSupport support) {
        DataFlavor[] flavors = support.getDataFlavors();

        for (DataFlavor dataFlavor : flavors) {
            if (dataFlavor.getRepresentationClass() == com.stcal.don.DCouple.class) return true;
            Message.out.println(dataFlavor.getRepresentationClass());
        }
        Message.err.println("non j'crois pas3");
        return true;
    }

}