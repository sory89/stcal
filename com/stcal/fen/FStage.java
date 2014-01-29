package com.stcal.fen;

import com.stcal.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FStage extends FTab {

    protected DefaultListModel prof = new DefaultListModel();
    protected DefaultListModel stag = new DefaultListModel();
    protected JLabel info = new JLabel("<html>Sélectionner un enseignant ou un stagiaire pour afficher ses infos.</html>");
    protected JPanel option = new JPanel();
    protected ArrayList<String> liste =new ArrayList<String>();
    protected ArrayList<String> tutPre = new ArrayList<String>();
    protected ArrayList<String> tutNom = new ArrayList<String>();
    protected String selectedTutPre = "";
    protected String selectedTutNom = "";
    protected String selectedType = Main.NONE;


    /**
     * Initialisation de l'ongelet
     */
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
        option.setLayout(new GridLayout(3,1));
        right.add(option);
        JButton supprimer=new JButton("supprimer stage");
        supprimer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                supprimer_stage();
            }
        });
        JButton supprimer1=new JButton("supprimer etudiant");
        option.add(supprimer);
        option.add(supprimer1);
        pan().add(right);
    }

    /**
     *
     * @param pan
     * @param type Constante de Main.ETU/Main.PROF/Main.NONE
     */
    protected void askInfo(JList pan,String type){
        try {
            if (type.equals(Main.PROF)){
                selectedTutPre = tutPre.get(pan.getLeadSelectionIndex());
                selectedTutNom = tutNom.get(pan.getLeadSelectionIndex());
                setInfo(Main.personneInfo(type,selectedTutPre, selectedTutNom));
                stag.removeAllElements();
                ArrayList<String> inter = Main.etuStage(selectedTutPre,selectedTutNom);
            }
            selectedType = type;
        }
        catch (Exception ex){
            System.err.println("err: FTab: event JList: " + ex.getMessage());
        }
        refresh();
    }

    public void addetustage(String pren,String nom1){
      //  if (tutPre.contains(pre) && tutNom.contains(nom)){
        liste.add(pren+" "+nom1);
        stag.addElement(pren+" "+nom1);
        refresh();
    }
    public void addProf(String pre,String nom){
        if (tutPre.contains(pre) && tutNom.contains(nom)){
            stag.removeAllElements();
            return;
        }
        tutPre.add(pre);
        tutNom.add(nom);
        prof.addElement(pre + " " + nom);
        refresh();
    }

    public void delProf(String pre,String nom){
        if(!tutPre.contains(pre)|| !tutNom.contains(nom)){
            return;
        }
        tutPre.remove(pre);
        tutNom.remove(nom);
        prof.removeElement(pre+" "+nom);
    }

    void supprimer_stage(){
        if(Main.delier(selectedTutPre,selectedTutNom)){
            delProf(selectedTutPre, selectedTutNom);
            for(int i=0;i<liste.size();i++){
                liste.remove(i);
            }
            stag.removeAllElements();
        }
        refresh();
    }

    @Override
    public void setInfo(ArrayList<String> details){
        String newInfo = "<html>";
        info.setText("");
        for (int i=0;i<details.size();i++){
            newInfo += details.get(i) + "<br />";
        }
        newInfo += "</html>";
        refresh();
    }

    public boolean existe(String prenom,String nom){
        if(prof.contains(prenom+" "+nom)){
            return true;
        }
        return false;
    }

}
