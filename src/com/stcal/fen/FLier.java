package com.stcal.fen;

import com.stcal.Main;
import com.stcal.control.Datas;
import com.stcal.don.DEtudiant;
import com.stcal.don.DListe;
import com.stcal.don.DPersonne;
import com.stcal.don.Type;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FLier extends FTab {

    protected DefaultListModel profList = new DefaultListModel();
    protected static DefaultListModel etuList = new DefaultListModel();
    protected JLabel info = new JLabel("<html>Sélectionner un étudiant ou un prof pour afficher ses infos.</html>");

    protected static ArrayList<String> prenomEtu = new ArrayList<String>();
    protected static ArrayList<String> nomEtu = new ArrayList<String>();
    protected ArrayList<String> prenomProf = new ArrayList<String>();
    protected ArrayList<String> nomProf = new ArrayList<String>();
    protected String selectedEtuPre = "";
    protected String selectedEtuNom = "";
    protected int selectedEtuIndex = 0;
    protected String selectedProfPre = "";
    protected String selectedProfNom = "";
    protected Type selectedType = Type.NONE;


    protected JLabel courant = new JLabel("courant");
    protected JPanel option= new JPanel();
    protected JList Fetu;
    protected JList Fprof;
    protected JScrollPane jScrollPane;


    public FLier(){
        super("Lier");
        pan().setLayout(new GridLayout(0, 3));

        Fprof = new JList(Datas.prof);
        Fprof.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Fprof.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                askInfo(Fprof, Type.TUTEUR);
            }
        });
        Fprof.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }


            @Override
            public void keyPressed(KeyEvent e) {
                askInfo(Fprof, Type.TUTEUR);
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
                Main.openFile(Type.ETUDIANT);
            }
        });
        option.add(opt1);
        JButton opt2 = new JButton("Importer enseignants");
        opt2.setToolTipText("Ouvrir un fichier d'enseignants CSV");
        opt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.openFile(Type.TUTEUR);
                /*
                System.out.println("taille : " + Datas.prof.getSize());
                for (int i=0;i<Datas.prof.getSize(); i++){
                    System.out.println(Datas.prof.getElementAt(i).getPrenom());
                }
                */
        }});
        option.add(opt2);
        centre.add(option);
        info.setBorder(BorderFactory.createTitledBorder("Info"));
        centre.add(info);
        pan().add(centre);



        Fetu = new JList(Datas.etu);
        Fetu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        Fetu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                askInfo(Fetu, Type.ETUDIANT);
            }
        });
        Fetu.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }


            @Override
            public void keyPressed(KeyEvent e) {
                askInfo(Fetu, Type.ETUDIANT);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });
        jScrollPane=new JScrollPane(Fetu);
        pan().add(jScrollPane);
        refresh();
    }

    protected void askInfo(JList pan,Type type){
        try {
            if (type.equals(Type.ETUDIANT)){
                selectedEtuIndex = pan.getLeadSelectionIndex();
                selectedEtuPre = prenomEtu.get(selectedEtuIndex);
                selectedEtuNom = nomEtu.get(selectedEtuIndex);
                setInfo(Main.personneInfo(Type.ETUDIANT,selectedEtuPre, selectedEtuNom));

            }
            else if (type.equals(Type.TUTEUR)){
                selectedProfPre = prenomProf.get(pan.getLeadSelectionIndex());
                selectedProfNom = nomProf.get(pan.getLeadSelectionIndex());
                setInfo(Main.personneInfo(Type.TUTEUR,selectedProfPre, selectedProfNom));
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
            //delEtu(selectedEtuPre,selectedEtuNom);
            // Fetu.remove(selectedEtuIndex);
            courant.setText("<html>Stage créé.</html>");
            refresh();
    }

    public void resetOption(){
        option.removeAll();
        if (Datas.etu.isEmpty()){
            JButton opt1 = new JButton("Importer étudiants");
            opt1.setToolTipText("Ouvrir un fichier d'étudiants CSV");
            opt1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Main.openFile(Type.ETUDIANT);
                }
            });
            option.add(opt1);
        }

        if (Datas.prof.isEmpty()){
            JButton opt2 = new JButton("Importer enseignants");
            opt2.setToolTipText("Ouvrir un fichier d'enseignants CSV");
            opt2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Main.openFile(Type.TUTEUR);
                    refresh();
                }
            });
            option.add(opt2);
        }
        if (!Datas.etu.isEmpty() && !Datas.prof.isEmpty()){
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
            html += "DTuteur: " + selectedProfPre + " " + selectedProfNom.toUpperCase() + "<br />";
        }
        if (!selectedEtuPre.equals("") && !selectedEtuNom.equals("")){
            html += "Étudiant: " + selectedEtuPre + " " + selectedEtuNom.toUpperCase() + "<br />";
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

    public void add(Type type,String pre,String nom){
        if (type.equals(Type.ETUDIANT)){
            addEtu(pre,nom);
        }
        else if (type.equals(Type.TUTEUR)){
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

    /*
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
    */
    public void addProf(String pre,String nom){
        prenomProf.add(pre);
        nomProf.add(nom);
        profList.addElement(pre + " " + nom);
        refresh();
    }


}
