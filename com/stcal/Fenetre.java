package com.stcal;

import javax.swing.JFrame;

public class Fenetre extends JFrame {

    /** Construit une fenetre vide */
    public Fenetre (com.stcal.Liste etu, com.stcal.Liste prof){
        this.setTitle("StCal");
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new com.stcal.Panneau(prof,etu));
    }

    /** rend la fenetre visible */
    public void visible() {
        this.setVisible(true);
    }



}
