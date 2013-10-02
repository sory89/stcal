package com.stcal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Open implements ActionListener {

    Container jp;
    JFrame fen;

    public Open(JFrame fen,Container jp){
        this.jp = jp;
        this.fen = fen;
    }

    public void actionPerformed(ActionEvent e) {
        System.err.println("open file...");
        File file1;
        File file2;
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(jp);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file1 = fc.getSelectedFile();
            System.err.println("Opening: " + file1.getPath());
            fc = new JFileChooser();
            returnVal = fc.showOpenDialog(jp);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file2 = fc.getSelectedFile();
                System.err.println("Opening: " + file2.getPath());
            } else {
                System.err.println("Open 2 command cancelled by user.");
                return;
            }
        } else {
            System.err.println("Open 1 command cancelled by user.");
            return;
        }
        try {
            JPanneau panneau = (JPanneau)fen.getContentPane();
            panneau.setPanneau(new Panneau(Splitcsv.splitcsvprof(file1.getPath()), Splitcsv.splitcsvetu(file2.getPath())));
            fen.setVisible(true);
        }
        catch (java.io.IOException exc){
            System.err.println("fail to use file");
        }
    }

}
