package com.stcal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FMenu {

    protected JMenuBar menubar = new JMenuBar();

    public FMenu(){
        JMenu menuaction = new JMenu("Actions");
        JMenuItem lier = new JMenuItem("Lier");
        lier.setToolTipText("Lier l'étudiant et l'enseignants.");
        /*lier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.lier(, , , , , );
            }
        });*/
        menuaction.add(lier);


        JMenu menufile = new JMenu("File");
        JMenuItem openetu = new JMenuItem("Importer étudiants...");
        openetu.setToolTipText("Importer un ficher CSV d'étudiants.");
        openetu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.openFile(Main.ETU);
            }
        });
        menufile.add(openetu);
        JMenuItem openprof = new JMenuItem("Importer enseignants...");
        openprof.setToolTipText("Importer un ficher CSV d'enseignants.");
        openprof.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.openFile(Main.PROF);
            }
        });
        menufile.add(openprof);
        menufile.addSeparator();
        JMenuItem expot = new JMenuItem("Exporter...");
        expot.setToolTipText("Exporter les liaison déja faite dans un fichier CSV.");
        expot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.exporter();
            }
        });
        menufile.add(expot);
        menufile.addSeparator();
        JMenuItem quit = new JMenuItem("Quit StCal");
        quit.setToolTipText("Fermer l'application");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("FMenu: JMenu exit");
                System.exit(0);
            }
        });
        menufile.add(quit);

        menubar.add(menufile);
        menubar.add(menuaction);
    }

    public JMenuBar getMenubar(){
        return menubar;
    }


}
