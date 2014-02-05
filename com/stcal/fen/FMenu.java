package com.stcal.fen;

import com.stcal.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FMenu {

    protected JMenuBar menubar = new JMenuBar();

    public FMenu(){
        JMenu menuaction = new JMenu("Actions");
        JMenuItem lier = new JMenuItem("Lier");
        lier.setToolTipText("Lier l'étudiant et l'enseignant.");
        /*lier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.lier(, , , , , );
            }
        });*/
        menuaction.add(lier);


        JMenu menufile = new JMenu("File");
        JMenuItem openetu = new JMenuItem("Importer étudiants...") ;
        openetu.setToolTipText("Importer un ficher CSV d'étudiants.");
        openetu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.openFile(Main.ETU);
            }
        });
        menufile.add(openetu);
        JMenuItem openprof = new JMenuItem("Importer enseignants...");
        openprof.setToolTipText("Importer un fichier CSV d'enseignants.");
        openprof.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.openFile(Main.PROF);
            }
        });
        menufile.add(openprof);
        menufile.addSeparator();
        JMenuItem export = new JMenuItem("Exporter...");
        export.setToolTipText("Exporter les liaisons déjà faites dans un fichier CSV.");
        export.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.exporter();
            }
        });
        menufile.add(export);
        menufile.addSeparator();
        JMenuItem quit = new JMenuItem("Quitter");
        quit.setToolTipText("Fermer l'application");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("FMenu: JMenu exit");
                System.exit(0);
            }
        });
        menufile.add(quit);

        JMenu menupref = new JMenu("Préférences");
        JMenuItem db = new JMenuItem("Base de données...");
        db.setToolTipText("Modifier le serveur et les identifiants de connexion à la base de données.");
        db.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.askdbsetting();
            }
        });
        menupref.add(db);


        menubar.add(menufile);
        menubar.add(menuaction);
        menubar.add(menupref);
    }

    public JMenuBar getMenubar(){
        return menubar;
    }


}
