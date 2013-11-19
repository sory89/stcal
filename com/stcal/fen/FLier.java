package com.stcal.fen;

import com.stcal.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FLier extends FTab {

    protected DefaultListModel profList = new DefaultListModel();
    protected DefaultListModel etuList = new DefaultListModel();
    protected JLabel info = new JLabel("<html>Selectionner un étudiant ou un prof pour afficher ses infos.</html>");
    protected JLabel courant = new JLabel("courant");
    protected JPanel option= new JPanel();
    protected ArrayList<String> prenomEtu = new ArrayList<String>();
    protected ArrayList<String> nomEtu = new ArrayList<String>();
    protected ArrayList<String> prenomProf = new ArrayList<String>();
    protected ArrayList<String> nomProf = new ArrayList<String>();
    protected String selectedEtuPre = "";
    protected String selectedEtuNom = "";
    protected String selectedProfPre = "";
    protected String selectedProfNom = "";
    protected String selectedType = Main.NONE;
    protected String courantEtuPre = "";
    protected String courantEtuNom = "";
    protected String courantTutPre = "";
    protected String courantTutNom = "";
    protected String courantCandPre = "";
    protected String courantCandNom = "";

    public FLier(){
        super("Lier");
        pan().setLayout(new GridLayout(0, 3));
        final JList Fprof = new JList(profList);
        Fprof.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                askInfo(Fprof,Main.PROF);
            }
        });
        Fprof.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}


            @Override
            public void keyPressed(KeyEvent e) {
                askInfo(Fprof,Main.PROF);
            }

            @Override
            public void keyReleased(KeyEvent e) {}

        });
        pan().add(new JScrollPane(Fprof));
        JPanel centre = new JPanel();
        centre.setOpaque(false);
        centre.setLayout(new GridLayout(3, 0));
        courant.setBorder(BorderFactory.createTitledBorder("Courant"));
        centre.add(courant);
        option.setOpaque(false);
        option.setLayout(new GridLayout(8, 0));
        JButton opt1 = new JButton("Importer étudiants");
        opt1.setToolTipText("Ouvrire un fichier d'étudiant CSV");
        opt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.openFile(Main.ETU);
            }
        });
        option.add(opt1);
        JButton opt2 = new JButton("Importer enseignants");
        opt2.setToolTipText("Ouvrire un fichier d'enseignant CSV");
        opt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.openFile(Main.PROF);
            }
        });
        option.add(opt2);
        centre.add(option);
        info.setBorder(BorderFactory.createTitledBorder("Info"));
        centre.add(info);
        pan().add(centre);
        final JList Fetu = new JList(etuList);
        Fetu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                askInfo(Fetu, Main.ETU);
            }
        });
        Fetu.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}


            @Override
            public void keyPressed(KeyEvent e) {
                askInfo(Fetu, Main.ETU);
            }

            @Override
            public void keyReleased(KeyEvent e) {}

        });
        pan().add(new JScrollPane(Fetu));
    }

    protected void askInfo(JList pan,String type){
        try {
            if (type.equals(Main.ETU)){
                selectedEtuPre = prenomEtu.get(pan.getLeadSelectionIndex());
                selectedEtuNom = nomEtu.get(pan.getLeadSelectionIndex());
                Main.personneInfo(selectedType,selectedEtuPre, selectedEtuNom);
            }
            else if (type.equals(Main.PROF)){
                selectedProfPre = prenomProf.get(pan.getLeadSelectionIndex());
                selectedProfNom = nomProf.get(pan.getLeadSelectionIndex());
                Main.personneInfo(selectedType,selectedProfPre, selectedProfNom);
            }
            selectedType = type;
        }
        catch (Exception ex){
            System.err.println("err: FInterface: event JList: " + ex.getMessage());
        }
    }

    protected void askStage(){
        if (Main.lier(courantEtuPre,courantEtuNom,courantTutPre,courantTutNom,courantCandPre,courantCandNom)){
            courantEtuPre = "";
            courantEtuPre = "";
            courantTutPre = "";
            courantTutNom = "";
            courantCandPre = "";
            courantCandNom = "";
            etuList.removeElement(courantEtuPre + " " + courantEtuNom.toUpperCase());
            prenomEtu.remove(courantEtuPre);
            nomEtu.remove(courantEtuNom);
            courant.setText("<html>Stage crée.</html>");
            refresh();
        }
    }

    public void resetOption(){
        option.removeAll();
        if (etuList.isEmpty()){
            JButton opt1 = new JButton("Importer étudiants");
            opt1.setToolTipText("Ouvrire un fichier d'étudiant CSV");
            opt1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Main.openFile(Main.ETU);
                }
            });
            option.add(opt1);
        }
        if (profList.isEmpty()){
            JButton opt2 = new JButton("Importer enseignants");
            opt2.setToolTipText("Ouvrire un fichier d'enseignant CSV");
            opt2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Main.openFile(Main.PROF);
                }
            });
            option.add(opt2);
        }
        if (!etuList.isEmpty()){
            JButton opt3 = new JButton("Definir etudiants");
            opt3.setToolTipText("Ajoute l'étudiant selectionné comme l'étudiant courant");
            opt3.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    courantEtuPre = selectedEtuPre;
                    courantEtuNom = selectedEtuNom;
                    resetCourant();
                    resetOption();
                    Main.fenStatut("Etudiant defini.");
                }
            });
            option.add(opt3);
        }
        if(!courantEtuPre.equals("") || !courantEtuNom.equals("")){
            JButton opt7 = new JButton("Supprimer etudiant courant");
            opt7.setToolTipText("Supprime l'étudiant l'étudiant courant");
            opt7.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    courantEtuPre = "";
                    courantEtuNom = "";
                    resetCourant();
                    resetOption();
                    Main.fenStatut("Etudiant courant non défini.");
                }
            });
            option.add(opt7);
        }
        if (!profList.isEmpty()){
            JButton opt4 = new JButton("Enseignants tuteur");
            opt4.setToolTipText("Ajoute l'enseignant selectionné comme le tuteur courant");
            opt4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!selectedProfPre.equals(courantCandPre) && !selectedProfNom.equals(courantCandNom)){
                        courantTutPre = selectedProfPre;
                        courantTutNom = selectedProfNom;
                        resetCourant();
                        resetOption();
                        Main.fenStatut("Tuteur defini.");
                    }
                    else {
                        Main.fenStatut("Cette enseignant est deja utilisé.");
                        refresh();
                    }
                }
            });
            option.add(opt4);
            JButton opt5 = new JButton("Enseignants candide");
            opt5.setToolTipText("Ajoute l'enseignant selectionné comme l'enseignant candide courant");
            opt5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!selectedProfPre.equals(courantTutPre) && !selectedProfNom.equals(courantTutNom)){
                        courantCandPre = selectedProfPre;
                        courantCandNom = selectedProfNom;
                        resetCourant();
                        resetOption();
                        Main.fenStatut("Enseignant candide defini.");
                    }
                    else {
                        Main.fenStatut("Cette enseignant est deja utilisé.");
                        refresh();
                    }
                }
            });
            option.add(opt5);
        }
        if(!courantTutPre.equals("") || !courantTutNom.equals("")){
            JButton opt8 = new JButton("Supprimer tuteure courant");
            opt8.setToolTipText("Supprime l'enseignant tuteur courant");
            opt8.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    courantTutPre = "";
                    courantTutNom = "";
                    Main.fenStatut("Tuteure courant non défini.");
                    resetCourant();
                    resetOption();
                }
            });
            option.add(opt8);
        }
        if(!courantCandPre.equals("") || !courantCandNom.equals("")){
            JButton opt9 = new JButton("Supprimer candide courant");
            opt9.setToolTipText("Supprime l'enseignant candide courant");
            opt9.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    courantCandPre = "";
                    courantCandNom = "";
                    Main.fenStatut("Candide non défini.");
                    resetCourant();
                    resetOption();
                }
            });
            option.add(opt9);
        }
        if (!etuList.isEmpty() && !profList.isEmpty()){
            JButton opt6 = new JButton("Valider");
            opt6.setToolTipText("Confirme le stage courant");
            opt6.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    askStage();
                }
            });
            option.add(opt6);
        }
        refresh();
    }

    public void resetCourant(){
        String html = "<html>";
        if (!courantTutNom.equals("") && !courantTutPre.equals("")){
            html += "Tuteur: " + courantTutPre + " " + courantTutNom.toUpperCase() + "<br />";
        }
        if (!courantCandNom.equals("") && !courantCandPre.equals("")){
            html += "Candide: " + courantCandPre + " " + courantCandNom.toUpperCase() + "<br />";
        }
        if (!courantEtuPre.equals("") && !courantEtuNom.equals("")){
            html += "Etudiant: " + courantEtuPre + " " + courantEtuNom.toUpperCase() + "<br />";
        }
        html += "</html>";
        courant.setText(html);
        refresh();
    }

    public void setInfo(ArrayList<String> details){
        String newInfo = "<html>";
        info.setText("");
        for (int i=0;i<details.size();i++){
            newInfo += details.get(i) + "<br />";
        }
        newInfo += "</html>";
        info.setText(newInfo);
        refresh();
    }

    public void add(String type,String pre,String nom){
        if (type.equals(Main.ETU)){
            addEtu(pre,nom);
        }
        else if (type.equals(Main.PROF)){
            addProf(pre,nom);
        }
        else {
            Main.fenStatut("Err: type de personne non reconnu.");
            refresh();
        }
    }

    public void addEtu(String pre,String nom){
        prenomEtu.add(pre);
        nomEtu.add(nom);
        etuList.addElement(pre + " " + nom.toUpperCase());
        refresh();
    }

    public void addProf(String pre,String nom){
        prenomProf.add(pre);
        nomProf.add(nom);
        profList.addElement(pre + " " + nom.toUpperCase());
        refresh();
    }


}
