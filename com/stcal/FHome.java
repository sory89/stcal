package com.stcal;

import javax.swing.*;
import java.awt.event.*;

public class FHome {

    protected JFrame fen;

    public FHome() {
        fen =  new JFrame();
        Main.mac(fen);
        FMenu menubar = new FMenu();
        fen.setContentPane(new JPanel());
        fen.setJMenuBar(menubar.getMenubar());
        fen.setLocationRelativeTo(null);
        fen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fen.setSize(200, 150);
        JButton etu = new JButton("importer etudiants");
        etu.setToolTipText("Ouvrire un fichier d'étudiant CSV");
        etu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
                Main.openFile(Main.ETU);
            }
        });
        fen.getContentPane().add(etu);
        JButton prof = new JButton("importer enseignants");
        prof.setToolTipText("Ouvrire un fichier d'enseignant CSV");
        prof.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
                Main.openFile(Main.PROF);
            }
        });
        fen.getContentPane().add(prof);
        JButton quit = new JButton("quitter");
        quit.setToolTipText("Quitter l'application");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("FHome: exit");
                System.exit(0);
            }
        });
        fen.getContentPane().add(quit);
    }

    public void show(){
        System.out.println("FHome: show");
        fen.setVisible(true);
    }

    public void close(){
        System.out.println("FHome: close");
        fen.setVisible(false);
    }

}
