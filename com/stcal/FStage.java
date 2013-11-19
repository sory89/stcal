package com.stcal;

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

    public FStage(){
        super("Stage");
        pan().setLayout(new GridLayout(0, 2));
        final JList Fprof = new JList(prof);
        // event
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

    public void addProf(String pre,String nom){
        tutPre.add(pre);
        tutNom.add(nom);
        prof.addElement(pre + " " + nom.toUpperCase());
        refresh();
    }

}
