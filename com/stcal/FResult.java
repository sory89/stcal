package com.stcal;

import javax.swing.*;
import java.util.ArrayList;

public class FResult {

    protected ArrayList<String> etu = new ArrayList<String>();
    protected ArrayList<String> prof = new ArrayList<String>();
    protected JFrame fen = new JFrame();
    protected JLabel cont = new JLabel("<html></html>");

    public FResult(){
        fen.setTitle("Resultat");
        fen.setContentPane(new JPanel());
        fen.setLocationRelativeTo(null);
        FMenu menubar = new FMenu();
        fen.setJMenuBar(menubar.getMenubar());
        Main.mac(fen);
        fen.setSize(200,400);
        JScrollPane pane = new JScrollPane(cont);
        fen.getContentPane().add(pane);
    }

    public void show(){
        System.out.println("FListe: show");
        fen.setVisible(true);
    }

    public void close(){
        System.out.println("FListe: close");
        fen.setVisible(false);
    }

    public void add(String etu,String prof){
        System.out.println("FResult: add " + etu + " & " + prof);
        String lab = "<html>";
        int a=0;
        for (int i=0;i<this.etu.size();i++){
           if(this.etu.get(i).equals(etu)) {
               a = 1;
           }
        }
        if (a==0){
            this.etu.add(etu);
            this.prof.add(prof);
        }
        for (int i=0;i<this.etu.size();i++){
            lab += this.etu.get(i) + " && " + this.prof.get(i) + "<br />";
        }
        lab += "</html>";
        cont.setText(lab);
        fen.getContentPane().repaint();

    }
}
