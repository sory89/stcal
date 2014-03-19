package com.stcal.fen;

import com.stcal.Main;
import com.stcal.control.DBTools;
import com.stcal.control.Message;
import com.stcal.don.Type;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

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

        JMenu menuOther = new JMenu("?");
        JMenuItem help = new JMenuItem("Aide");
        JMenuItem raccourcis = new JMenuItem("Raccourcis");
        JMenuItem aPropos = new JMenuItem("À propos de STCal");
        //final JOptionPane jop1 = new JOptionPane();
        aPropos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message.popnotice("Le logiciel STCal a été créé par des étudiants dans le cadre du projet tutoré de l'IUT de Belfort. \n" +
                        "Sa conception et sa réalisation ont été entièrement réalisées par Florian BARROIS, Nicolas DEVILLERS, \nValentin JEANROY, Mehdi LOISEL Jean MERCADIER, Ismail TALEB et Willeme VERDEAUX.\n\n " +
                        "Il est destiné à faciliter la gestion des stages d'étudiants en proposant des fonctionnalités pour les stages propres à l'application \n ainsi qu'un outil de création de calendrier permettant la mise en place des soutenances orales des étudiants." +
                        "\n\n Les sources du projet sont disponibles à l'adresse URL suivante : https://github.com/Ricain/stcal" ,"A propos");
            }
        });

        menuOther.add(help);
        menuOther.add(raccourcis);
        menuOther.addSeparator();
        menuOther.add(aPropos);

        JMenu menufile = new JMenu("File");
        JMenuItem openetu = new JMenuItem("Importer étudiants...") ;
        openetu.setToolTipText("Importer un ficher CSV d'étudiants.");
        openetu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.openFile(Type.ETUDIANT);

            }
        });
        menufile.add(openetu);
        JMenuItem openprof = new JMenuItem("Importer enseignants...");
        openprof.setToolTipText("Importer un fichier CSV d'enseignants.");
        openprof.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.openFile(Type.TUTEUR);
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
        JMenuItem exportIcs = new JMenuItem("Exporter au format ICS");
        exportIcs.setToolTipText("Exporter le calendrier au format ICS.");
        menufile.add(exportIcs);
        menufile.addSeparator();
        JMenuItem quit = new JMenuItem("Quitter");
        quit.setToolTipText("Fermer l'application");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message.out.println("FMenu: JMenu exit");
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
                DBTools.dbsettings.popup().pop();
                DBTools.askdbsetting(DBTools.dbsettings);
            }
        });
        menupref.add(db);
        menupref.addSeparator();
        JMenuItem dbr = new JMenuItem("Reset DB connection");
        dbr.setToolTipText("Ferme la connection à la base de données et en ouvre une nouvelle.");
        dbr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DBTools.resetConnection();
                    Message.popnotice("Connection à la base de données fermée et réouverte avec succès.");
                } catch (SQLException e1) {
                    Message.poperror(e1);
                }
            }
        });
        menupref.add(dbr);



        menubar.add(menufile);
        menubar.add(menuaction);
        menubar.add(menupref);
        menubar.add(menuOther);
    }

    public JMenuBar getMenubar(){
        return menubar;
    }


}