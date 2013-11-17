package com.stcal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FStage extends FTab {

    protected DefaultListModel prof = new DefaultListModel();
    protected DefaultListModel stag = new DefaultListModel();
    protected JLabel info = new JLabel("<html>Selectionner un enseignant ou un stagiere pour afficher ses infos.</html>");
    protected JPanel option = new JPanel();

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


}
