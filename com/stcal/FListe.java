package com.stcal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FListe {

    protected ArrayList<String> prenom = new ArrayList<String>();
    protected ArrayList<String> nom = new ArrayList<String>();
    protected ArrayList<String> details = new ArrayList<String>();
    protected JLabel desc = new JLabel("info");
    protected JFrame fen = new JFrame();
    protected DefaultListModel personne;
    protected String selectedPre = "";
    protected String selectedNom = "";

    public FListe(String titre){
        Main.mac(fen);
        FMenu menubar = new FMenu();
        fen.setContentPane(new JPanel());
        fen.setJMenuBar(menubar.getMenubar());
        fen.setSize(500, 400);
        fen.setLocationRelativeTo(null);
        fen.setTitle(titre);
        fen.getContentPane().setLayout(new GridLayout(0, 2));
        personne = new DefaultListModel();
        final JList liste = new JList(personne);
        liste.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedPre = prenom.get(liste.getLeadSelectionIndex());
                selectedNom = nom.get(liste.getLeadSelectionIndex());
                Main.personneInfo(selectedPre,selectedNom);
            }
        });
        JScrollPane pane = new JScrollPane(liste);
        fen.getContentPane().add(pane);
        fen.getContentPane().add(desc);
    }

    public String getSelectedPre(){
        return selectedPre;
    }

    public  String getSelectedNom(){
        return selectedNom;
    }

    public void add(String pre,String nom){
        this.prenom.add(pre);
        this.nom.add(nom);
        personne.addElement(pre + " " + nom.toUpperCase());
        fen.getContentPane().repaint();
    }

    public void addInfo(String info){
        details.add(info);
    }

    public void resetInfo(){
        details = new ArrayList<String>();
    }

    public void showInfo(){
        String info = "<html>";
        desc.setText("");
        for (int i=0;i<details.size();i++){
           info += details.get(i) + "<br />";
        }
        info += "</html>";
        desc.setText(info);
        fen.getContentPane().repaint();
    }

    public void show(){
        System.out.println("FListe: show");
        fen.setVisible(true);
    }

    public void close(){
        System.out.println("FListe: close");
        fen.setVisible(false);
    }
}
