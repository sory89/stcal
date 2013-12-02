package com.stcal.fen;

import com.stcal.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class FStage extends FTab {

    protected DefaultListModel prof = new DefaultListModel();
    protected DefaultListModel stag = new DefaultListModel();
    protected JLabel info = new JLabel("<html>Selectionner un enseignant ou un stagiere pour afficher ses infos.</html>");
    protected JPanel option = new JPanel();

    protected ArrayList<String> tutPre = new ArrayList<String>();
    protected ArrayList<String> tutNom = new ArrayList<String>();
    protected String selectedTutPre = "";
    protected String selectedTutNom = "";
    protected String selectedType = Main.NONE;

    public FStage(){
        super("Stage");
        pan().setLayout(new GridLayout(0, 2));
        final JList Fprof = new JList(prof);
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
        JPanel right = new JPanel();
        right.setOpaque(false);
        right.setLayout(new GridLayout(3,0));
        final  JList Fstag = new JList(stag);
        // event
        right.add(new JScrollPane(Fstag));
        info.setBorder(BorderFactory.createTitledBorder("Info"));
        right.add(info);
        option.setOpaque(false);
        option.setLayout(new GridLayout(8,0));
        right.add(option);
        pan().add(right);
    }

    protected void askInfo(JList pan,String type){
        try {
            if (type.equals(Main.PROF)){
                selectedTutPre = tutPre.get(pan.getLeadSelectionIndex());
                selectedTutNom = tutNom.get(pan.getLeadSelectionIndex());
                setInfo(Main.personneInfo(type,selectedTutPre, selectedTutNom));
                ArrayList<String> inter = Main.etuStage(selectedTutPre,selectedTutNom);
                stag.removeAllElements();
                for (int i=0;i<inter.size();i++){
                    stag.addElement(inter.get(i));
                }
            }
            //else if (type.equals(Main.ETU)){
            //    selectedProfPre = prenomProf.get(pan.getLeadSelectionIndex());
            //    selectedProfNom = nomProf.get(pan.getLeadSelectionIndex());
            //    Main.personneInfo(Main.PROF,selectedProfPre, selectedProfNom);
            //}
            selectedType = type;
        }
        catch (Exception ex){
            System.err.println("err: FTab: event JList: " + ex.getMessage());
        }
    }

    public void addProf(String pre,String nom){
        if (tutPre.contains(pre) && tutNom.contains(nom)){
            return;
        }
        tutPre.add(pre);
        tutNom.add(nom);
        prof.addElement(pre + " " + nom.toUpperCase());
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
