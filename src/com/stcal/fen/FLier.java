package com.stcal.fen;

import com.stcal.Main;
import com.stcal.control.Datas;
import com.stcal.don.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FLier extends FTab {
    protected JLabel info = new JLabel("<html>Sélectionner un étudiant ou un enseignant pour afficher ses informations.</html>");
    protected JLabel courant = new JLabel("courant");
    protected JPanel option= new JPanel();
    protected JList Fetu;
    protected JList Fprof;


    public FLier(){
        super("Lier");
        pan().setLayout(new GridLayout(0, 3));

        Fprof = new JList<DPersonne>(Datas.prof);
        Fprof.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Fprof.setBorder(BorderFactory.createTitledBorder("Liste des enseignants"));
        Fprof.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                askInfo(Fprof, Type.TUTEUR);
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
        }});
        option.add(opt2);
        centre.add(option);
        info.setBorder(BorderFactory.createTitledBorder("Informations"));

        // TODO scroll vertical non autorisé mais trouver solution pour afficher le texte correctement
        JScrollPane scrollPane = new JScrollPane(info);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        centre.add(scrollPane);
        pan().add(centre);



        Fetu = new JList(Datas.etu);
        Fetu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Fetu.setBorder(BorderFactory.createTitledBorder("Liste des étudiants"));
        Fetu.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                askInfo(Fetu, Type.ETUDIANT);
            }
        });

        pan().add(new JScrollPane(Fetu));
        refresh();
    }





    protected void askInfo(JList<DPersonne> pan,Type type){
        try {
            if (type.equals(Type.ETUDIANT)){
                setInfo(pan.getSelectedValue().getInfos());
            }
            else if (type.equals(Type.TUTEUR)){
                setInfo(pan.getSelectedValue().getInfos());
            }
            refresh();
            resetCourant(pan);
        }
        catch (Exception ex){
            System.err.println("err: FLier: event JList: " + ex.getMessage());
        }
    }

    protected void askStage(){
            Datas.stages.addElement(new DCouple((DEtudiant)Fetu.getSelectedValue(), (DTuteur) Fprof.getSelectedValue()));
            Datas.etu.remove(Fetu.getSelectedIndex());
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
            final JButton opt6 = new JButton("Valider");
            opt6.setEnabled(false);
            opt6.setToolTipText("Confirmer le stage courant");
            opt6.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    askStage();
                    Fetu.clearSelection();
                    Fprof.clearSelection();
                }
            });
            option.add(opt6);
            Fetu.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    opt6.setEnabled(!Fprof.isSelectionEmpty());
                }
            });

            Fprof.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    opt6.setEnabled(!Fetu.isSelectionEmpty());
                }
            });
        }
        refresh();
    }

    public void resetCourant(JList<DPersonne> list){
        String html = "<html>";
        if (list.getSelectedValue() instanceof DTuteur){
            html += "Tuteur: " + list.getSelectedValue().getPrenom() + " " + list.getSelectedValue().getNom().toUpperCase() + "<br />";
        }
        if (list.getSelectedValue() instanceof DEtudiant){
            html += "Étudiant: " + list.getSelectedValue().getPrenom() + " " + list.getSelectedValue().getNom().toUpperCase() + "<br />";
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
}