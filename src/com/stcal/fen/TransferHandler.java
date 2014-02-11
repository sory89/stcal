package com.stcal.fen;

import javax.swing.*;
import javax.swing.TransferHandler.TransferSupport;
import javax.swing.table.DefaultTableModel;
import java.awt.datatransfer.DataFlavor;

/**
 * Created with IntelliJ IDEA.
 * User: ismail
 * Date: 12/02/14
 * Time: 00:05
 * To change this template use File | Settings | File Templates.
 */
public class TransferHandler{
    protected DefaultTableModel tableModel;
    protected JTable table;

    public boolean canImport(TransferSupport support) {
            // for the demo, we'll only support drops (not clipboard paste)
        if (!support.isDrop()) {
           return false;
        }

        // we only import Strings
        if (!support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return false;
        }
        return true;
    }

   /* public boolean importData(TransferSupport support) {
        // if we can't handle the import, say so
        if (!canImport(support)) {
            return false;
        }
        // fetch the drop location
        JTable.DropLocation dl = (JTable.DropLocation) support
        .getDropLocation();
        int row = dl.getRow();
        // fetch the data and bail if this fails
        String data;
        try {
            data = (String) support.getTransferable().getTransferData(
            DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
        String[] rowData = data.split(",");
        tableModel.insertRow(row, rowData);
        Rectangle rect = table.getCellRect(row, 0, false);
        if (rect != null) {
            table.scrollRectToVisible(rect);
        }

                // demo stuff - remove for blog
        model.removeAllElements();
        model.insertElementAt(getNextString(count++), 0);
        // end demo stuff
        return true;
        }                 */
    }
