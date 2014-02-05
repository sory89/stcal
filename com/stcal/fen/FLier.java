package com.stcal.fen;

import com.stcal.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FLier extends FTab {

    protected DefaultListModel profList = new DefaultListModel();
    protected static DefaultListModel etuList = new DefaultListModel();
    protected JLabel info = new JLabel("<html>Sélectionner un étudiant ou un prof pour afficher ses infos.</html>");
    protected JLabel courant = new JLabel("courant");
    protected JPanel option= new JPanel();
    protected static ArrayList<String> prenomEtu = new ArrayList<String>();
    protected static ArrayList<String> nomEtu = new ArrayList<String>();
    protected ArrayList<String> prenomProf = new ArrayList<String>();
    protected ArrayList<String> nomProf = new ArrayList<String>();
    protected String selectedEtuPre = "";
    protected String selectedEtuNom = "";
    protected int selectedEtuIndex = 0;
    protected String selectedProfPre = "";
    protected String selectedProfNom = "";
    protected String selectedType = Main.NONE;
    protected JList Fetu;
    protected JScrollPane jScrollPane;


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
            public void keyTyped(KeyEvent e) {
            }


            @Override
            public void keyPressed(KeyEvent e) {
                askInfo(Fprof, Main.PROF);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

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
        opt1.setToolTipText("Ouvrir un fichier d'étudiants CSV");
        opt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.openFile(Main.ETU);
            }
        });
        option.add(opt1);
        JButton opt2 = new JButton("Importer enseignants");
        opt2.setToolTipText("Ouvrir un fichier d'enseignants CSV");
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
        Fetu = new JList(etuList);
        Fetu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                askInfo(Fetu, Main.ETU);
            }
        });
        Fetu.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }


            @Override
            public void keyPressed(KeyEvent e) {
                askInfo(Fetu, Main.ETU);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });
        jScrollPane=new JScrollPane(Fetu);
        pan().add(jScrollPane);
        refresh();
    }

    protected void askInfo(JList pan,String type){
        try {
            if (type.equals(Main.ETU)){
                selectedEtuIndex = pan.getLeadSelectionIndex();
                selectedEtuPre = prenomEtu.get(selectedEtuIndex);
                selectedEtuNom = nomEtu.get(selectedEtuIndex);
                setInfo(Main.personneInfo(Main.ETU,selectedEtuPre, selectedEtuNom));

            }
            else if (type.equals(Main.PROF)){
                selectedProfPre = prenomProf.get(pan.getLeadSelectionIndex());
                selectedProfNom = nomProf.get(pan.getLeadSelectionIndex());
                setInfo(Main.personneInfo(Main.PROF,selectedProfPre, selectedProfNom));
            }
            selectedType = type;
            refresh();
            resetCourant();
        }
        catch (Exception ex){
            System.err.println("err: FLier: event JList: " + ex.getMessage());
        }
    }

    protected void askStage(){
            delEtu(selectedEtuPre,selectedEtuNom);
            // Fetu.remove(selectedEtuIndex);
            courant.setText("<html>Stage créé.</html>");
            refresh();
    }

    public void resetOption(){
        option.removeAll();
        if (etuList.isEmpty()){
            JButton opt1 = new JButton("Importer étudiants");
            opt1.setToolTipText("Ouvrir un fichier d'étudiants CSV");
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
            opt2.setToolTipText("Ouvrir un fichier d'enseignants CSV");
            opt2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Main.openFile(Main.PROF);
                }
            });
            option.add(opt2);
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
        if (!selectedProfPre.equals("") && !selectedProfNom.equals("")){
            html += "Tuteur: " + selectedProfPre + " " + selectedProfNom.toUpperCase() + "<br />";
        }
        if (!selectedEtuPre.equals("") && !selectedEtuNom.equals("")){
            html += "DEtudiant: " + selectedEtuPre + " " + selectedEtuNom.toUpperCase() + "<br />";
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

    public static void addEtu(String pre, String nom){
        prenomEtu.add(pre);
        nomEtu.add(nom);
        etuList.addElement(pre + " " + nom.toUpperCase());

    }

    public void delEtu(String nom,String prenom){
        if(Main.lier(selectedEtuPre,selectedEtuNom,selectedProfPre,selectedProfNom))
        {
            etuList.removeElementAt(selectedEtuIndex);
            System.out.println(selectedEtuIndex);
            prenomEtu.remove(selectedEtuPre);
            nomEtu.remove(selectedEtuNom);
            selectedEtuIndex=0;
            selectedEtuNom="";
            selectedEtuPre="";
            selectedProfNom="";
            selectedProfPre="";
            refresh();
        }
    }
    public void addProf(String pre,String nom){
        prenomProf.add(pre);
        nomProf.add(nom);
        profList.addElement(pre + " " + nom);
        refresh();
    }


}
