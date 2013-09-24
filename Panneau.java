package com.stcal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Panneau extends JPanel {

    com.stcal.Liste etu;
    com.stcal.Liste prof;

    public Panneau(com.stcal.Liste etu, com.stcal.Liste prof){
        this.etu = etu;
        this.prof = prof;
    }

    public void paintComponent(Graphics g){
        for (int i=0;i<etu.nbPersonne();i++){
            com.stcal.Etudiant inter = (com.stcal.Etudiant)etu.getPersonne(i);
            g.drawString(inter.getPrenom() + " " + inter.getNom().toUpperCase(),30,60+15*i);
            g.drawString(inter.getLieu(),170,60+15*i);
            g.drawString(inter.getSujet(),310,60+15*i);
        }
        g.drawLine(500, 0, 500, this.getHeight());

        for (int i=0;i<prof.nbPersonne();i++){
            com.stcal.Prof inter = (com.stcal.Prof)prof.getPersonne(i);
            g.drawString(inter.getPrenom() + " " + inter.getNom().toUpperCase(),530,60+15*i);
            g.drawString(inter.getUv(),670,60+15*i);
        }
        Font font = new Font("Courier", Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.red);
        g.drawString("ETUDIANT", 200, 30);
        g.drawString("PROF", 625, 30);
    }

}
