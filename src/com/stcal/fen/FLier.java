package com.stcal.fen;

import com.stcal.Main;
import com.stcal.control.Datas;
import com.stcal.don.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FLier extends FTab {
    protected JLabel info = new JLabel("<html>Sélectionner un étudiant ou un enseignant pour afficher ses informations.</html>");
    protected JLabel courant = new JLabel("courant");
    protected JPanel option= new JPanel();
    protected JList Fetu;
    protected JList Fprof;


    public FLier(){
        super("Lier");
        pan().setLayout(new GridLayout(0, 3,10,10));

        DefaultListModel<DPersonne> model = new DefaultListModel<DPersonne>();
        for(DPersonne val : Datas.prof) model.addElement(val);
        Fprof = new JList<DPersonne>(model);
        Fprof.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Fprof.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                askInfo(Fprof, Type.TUTEUR);
            }
        });
        JPanel PanelProf = new JPanel();
        PanelProf.setLayout(new BorderLayout());
        PanelProf.setOpaque(false);
        PanelProf.add(new JLabel("Liste des enseignants", SwingConstants.CENTER),BorderLayout.NORTH);
        PanelProf.add(new JScrollPane(Fprof), BorderLayout.CENTER);
        pan().add(PanelProf);



        JPanel centre = new JPanel();
        centre.setOpaque(false);
        centre.setLayout(new GridLayout(3, 0));
        courant.setBorder(BorderFactory.createTitledBorder("Courant"));
        centre.add(courant);
        option.setOpaque(false);
        option.setLayout(new GridLayout(2, 0, 20, 20));
        option.setBorder(new EmptyBorder(20,0,20,0));
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



        DefaultListModel<DPersonne> model1 = new DefaultListModel<DPersonne>();
        for(DPersonne val : Datas.etu) model1.addElement(val);
        Fetu = new JList(model1);
        Fetu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Fetu.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                askInfo(Fetu, Type.ETUDIANT);
            }
        });
        JPanel PanelEtu = new JPanel();
        PanelEtu.setLayout(new BorderLayout());
        PanelEtu.setOpaque(false);
        PanelEtu.add(new JLabel("Liste des étudiants", SwingConstants.CENTER),BorderLayout.NORTH);
        PanelEtu.add(new JScrollPane(Fetu), BorderLayout.CENTER);
        pan().add(PanelEtu);
        refresh();
    }





    protected void askInfo(JList<DPersonne> pan,Type type){
        try {
            if (type.equals(Type.ETUDIANT)){
                info.setText(setInfo(pan.getSelectedValue().getInfos()));
            }
            else if (type.equals(Type.TUTEUR)){
                info.setText(setInfo(pan.getSelectedValue().getInfos()));
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
        FStage.newContentPane.populateTree(FStage.newContentPane.getTreePanel());
        System.out.println("Création du stage "+Datas.stages.getSize());
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
                    info.setText("");
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


    public String setInfo(ArrayList<String> details){
        String newInfo = "<html>";
        info.setText("");
        for (int i=0;i<details.size();i++){
            newInfo += details.get(i) + "<br />";
        }
        newInfo += "</html>";
        refresh();
        return newInfo;
    }
}