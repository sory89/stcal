package com.stcal.fen;

import javax.swing.*;

public class FTab {

    private JPanel tabpan = new JPanel();
    private String nom = "";

    public FTab(String titre){
        nom = titre;
        tabpan.setOpaque(false);
    }

    public void refresh(){
        tabpan.revalidate();
        tabpan.repaint();
    }

    public JPanel pan(){
        return tabpan;
    }

    public String getNom(){
        return nom;
    }

}
