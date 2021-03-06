package com.stcal.fen;

import com.stcal.Stcal;
import com.stcal.control.DBTools;
import com.stcal.control.Message;
import com.stcal.don.Type;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class FMenu {

    protected JMenuBar menubar = new JMenuBar();

    public FMenu(){
        JMenu menuaction = new JMenu("Actions");
        JMenuItem lier = new JMenuItem("Lier");
        lier.setToolTipText("Lier un étudiant à un enseignant.");
        lier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                FLier.opt6.doClick();
            }
        });

        menuaction.add(lier);

        JMenu menuOther = new JMenu("?");
        JMenuItem help = new JMenuItem("Aide");
        help.setToolTipText("Ouvrir le manuel.");
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message.popnotice("Le manuel d'utilisation de STCal est disponible à l'adresse suivante : https://github.com/Ricain/stcal/blob/Main/rapports/manuel/Manuel.pdf");
            }
        });
        JMenuItem aPropos = new JMenuItem("À propos de STCal");
        aPropos.setToolTipText("Découvrir le projet STCal.");
        aPropos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message.popnotice("Le logiciel STCal a été créé par des étudiants dans le cadre du projet tuteuré de l'IUT de Belfort. \n" +
                        "Sa conception et sa réalisation ont été entièrement réalisées par Florian BARROIS, Nicolas DEVILLERS, \nValentin JEANROY, Mehdi LOISEL Jean MERCADIER, Ismail TALEB et Willeme VERDEAUX.\n\n " +
                        "Il est destiné à faciliter la gestion des stages d'étudiants en proposant des fonctionnalités pour les stages propres à l'application \n ainsi qu'un outil de création de calendrier permettant la mise en place des soutenances orales des étudiants." +
                        "\n\n Les sources du projet sont disponibles à l'adresse URL suivante : https://github.com/Ricain/stcal" ,"A propos");
            }
        });

        menuOther.add(help);
        menuOther.addSeparator();
        menuOther.add(aPropos);

        JMenu menufile = new JMenu("Fichier");
        JMenuItem openetu = new JMenuItem("Importer étudiants") ;
        openetu.setToolTipText("Importer un ficher CSV d'étudiants.");
        openetu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Stcal.openFile(Type.ETUDIANT);

            }
        });
        menufile.add(openetu);
        JMenuItem openprof = new JMenuItem("Importer enseignants");
        openprof.setToolTipText("Importer un fichier CSV d'enseignants.");
        openprof.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Stcal.openFile(Type.TUTEUR);
            }
        });
        menufile.add(openprof);
        menufile.addSeparator();
        JMenuItem exportIcs = new JMenuItem("Exporter au format ICS ou PDF");
        exportIcs.setToolTipText("Exporter le calendrier au format ICS ou PDF.");


        exportIcs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              FExportIcs ics = new FExportIcs();
                ics.pop();
            }
        });


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
        JMenuItem db = new JMenuItem("Base de données");
        db.setToolTipText("Modifier le serveur et les identifiants de connexion à la base de données.");
        db.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBTools.dbsettings.popup().pop();
                DBTools.askdbsetting();
            }
        });
        menupref.add(db);
        menupref.addSeparator();
        JMenuItem dbr = new JMenuItem("Réinitialiser la connexion");
        dbr.setToolTipText("Fermer la connexion actuelle à la base de données et en ouvrir une nouvelle.");
        dbr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DBTools.resetConnection();
                    Message.popnotice("Connexion à la base de données fermée et rouverte avec succès.");
                } catch (SQLException e1) {
                    Message.poperror(e1);
                }
            }
        });
        menupref.add(dbr);
        JMenuItem dbe = new JMenuItem("Réinitialiser la base de données");
        dbe.setToolTipText("Vide et recrée la base de données");
        dbe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (Message.popquestion("Etes-vous sur de vouloir réinitialiser la base de données ? Toutes les données seront perdues.")) DBTools.resetDatabase();
                } catch (SQLException e1) {
                    Message.poperror(e1);
                } catch (IOException e1) {
                    Message.poperror(e1);
                }
            }
        });
        menupref.add(dbe);
        menupref.addSeparator();
        JMenuItem itemSalle = new JMenuItem("Salles");
        itemSalle.setToolTipText("Éditer la liste des salles");
        itemSalle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FSalles fSalles = new FSalles();
                fSalles.pop();
            }
        });
        menupref.add(itemSalle);



        JMenuItem sepCsv = new JMenuItem("Séparateur CSV");
        sepCsv.setToolTipText("Changer de séparateur pour les fichiers CSV. ( Par défaut ';' )");
        sepCsv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FCsv fCsv = new FCsv();
                fCsv.pop();
            }
        });
        menupref.add(sepCsv);



        menubar.add(menufile);
        menubar.add(menuaction);
        menubar.add(menupref);
        menubar.add(menuOther);
    }

    public JMenuBar getMenubar(){
        return menubar;
    }


}
