package com.stcal.fen;

import com.stcal.Stcal;
import com.stcal.control.Datas;
import com.stcal.control.Message;
import com.stcal.don.DCouple;
import com.stcal.don.DCreneau;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class DCoupleTransferHandler extends TransferHandler {

    DataFlavor DCFlavor = new DataFlavor(com.stcal.don.DCouple.class, "DCouple");
    DataFlavor[] flavors = new DataFlavor[] {DCFlavor};
    JList liste = null;
    JTable jt=null;

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



                 javax.swing.SwingUtilities.invokeLater(new Runnable() {
                     public void run() {
                 FCal.newContentPane.getTreePanel().DraggedDCoupleFromJtree(Datas.stages);
                         FStage.newContentPane.populateTree(FStage.newContentPane.getTreePanel());
                         FStage.newContentPane.getTreePanel().expandAll();
                         FCal.newContentPane.getTreePanel().expandAll();
                     }});








             }
                else{
                 Message.err.println("Nombre de soutenances dans ce créneau atteint !");
             }
            }
            else{
                Message.err.println("Impossible d'insérer un même professeur pour un même creneau");
            }
            int i;
            int y;


            for (y=0;y<jt.getRowCount();y++){
                for(i=0;i<jt.getColumnCount();i++)  {

                    Stcal.colors[y][i]="white";
                }     }
            jt.revalidate();
            jt.repaint();

            return true;
        } catch (UnsupportedFlavorException e) {
            Message.err.println(e.getMessage());
            Message.err.println("non j'crois pas5");
        } catch (IOException e) {
            Message.err.println(e.getMessage());
            Message.err.println("non j'crois pas6");
        }
        return false;
    }


    @Override
    protected Transferable createTransferable(JComponent c) {



        if (!(c instanceof JTree)) return null;
        final JTree list = (JTree) c;
         if(!(((DefaultMutableTreeNode) list.getLastSelectedPathComponent()).isLeaf()))  return null;
        final DCouple person = (DCouple) ((DefaultMutableTreeNode)(list.getSelectionPath().getLastPathComponent())).getUserObject();



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
            if (dataFlavor.getRepresentationClass() != com.stcal.don.DCouple.class) return false;

        }

        return true;
    }

}