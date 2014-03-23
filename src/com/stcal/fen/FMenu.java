package com.stcal.fen;

import com.stcal.Main;
import com.stcal.control.DBTools;
import com.stcal.control.Datas;
import com.stcal.control.Message;
import com.stcal.don.DCouple;
import com.stcal.don.DEtudiant;
import com.stcal.don.DProf;
import com.stcal.don.Type;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        final JFrame jfics = new JFrame();
        JLabel laboptions = new JLabel("Selectionez une option");
        String[] tab =  {"Salles","Profs","Pdf"} ;
        JComboBox jcboptions = new JComboBox(tab);
        JLabel  laboptions2 = new JLabel();
        JComboBox jcboptions2 = new JComboBox();
        JButton okexp = new JButton("Exporter");
        menuaction.add(lier);

        JMenu menuOther = new JMenu("?");
        JMenuItem help = new JMenuItem("Aide");
        help.setToolTipText("Ouvrir le manuel.");
        JMenuItem raccourcis = new JMenuItem("Raccourcis");
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
        menuOther.add(raccourcis);
        menuOther.addSeparator();
        menuOther.add(aPropos);

        JMenu menufile = new JMenu("Fichier");
        JMenuItem openetu = new JMenuItem("Importer étudiants") ;
        openetu.setToolTipText("Importer un ficher CSV d'étudiants.");
        openetu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.openFile(Type.ETUDIANT);

            }
        });
        menufile.add(openetu);
        JMenuItem openprof = new JMenuItem("Importer enseignants");
        openprof.setToolTipText("Importer un fichier CSV d'enseignants.");
        openprof.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.openFile(Type.TUTEUR);
            }
        });
        menufile.add(openprof);
        menufile.addSeparator();
        JMenuItem exportIcs = new JMenuItem("Exporter au format ICS");
        exportIcs.setToolTipText("Exporter le calendrier au format ICS.");

        jfics.setLayout(new GridLayout(5,0));
        jfics.add(laboptions);
        jfics.add(jcboptions);
        jfics.add(laboptions2);
        jfics.add(jcboptions2);
        jfics.add(okexp);
        jfics.setVisible(false);
        jfics.setSize(new Dimension(500,500));

        exportIcs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              jfics.setVisible(true);
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
                DBTools.askdbsetting(DBTools.dbsettings);
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


        menubar.add(menufile);
        menubar.add(menuaction);
        menubar.add(menupref);
        menubar.add(menuOther);
    }

    public JMenuBar getMenubar(){
        return menubar;
    }


}
