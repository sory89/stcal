package com.stcal.fen;

import com.stcal.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FStage extends FTab {

    protected DefaultListModel etu = new DefaultListModel();
    protected DefaultListModel stag = new DefaultListModel();
    protected JLabel info = new JLabel("<html>Sélectionner un enseignant ou un stagiaire pour afficher ses infos.</html>");
    protected JPanel option = new JPanel();
    protected ArrayList<String> liste =new ArrayList<String>();
    protected ArrayList<String> tutPre = new ArrayList<String>();
    protected ArrayList<String> tutNom = new ArrayList<String>();
    protected String selectedEtuPre = "";
    protected String selectedEtuNom = "";
    protected String selectedType = Main.NONE;
    int i;

    /**
     * Initialisation de l'ongelet
     */
    public FStage(){
        super("Stage");
        pan().setLayout(new GridLayout(0, 2));
        final JList Fetu = new JList(etu);

        for(i=0;i<Main.stages.size();i++){
           etu.addElement(Main.stages.get(i).getEtu());


        }
        Fetu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                askInfo(Fetu,Main.ETU);
            }
        });
        Fetu.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                askInfo(Fetu, Main.ETU);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });


        pan().add(new JScrollPane(Fetu));
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
                supprimer_stage(Fetu);
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
            if (type.equals(Main.ETU)){
                selectedEtuPre = tutPre.get(pan.getLeadSelectionIndex());
                selectedEtuNom = tutNom.get(pan.getLeadSelectionIndex());
                setInfo(Main.personneInfo(type,selectedEtuPre, selectedEtuNom));
                stag.removeAllElements();
                ArrayList<String> inter = Main.etuStage(selectedEtuPre,selectedEtuNom);
            }
            selectedType = type;
        }
        catch (Exception ex){
            System.err.println("err: FTab: event JList: " + ex.getMessage());
        }
        refresh();
    }






   public void supprimer_stage(JList pan){
        Main.delier(Main.stages.get(pan.getSelectedIndex()).getEtu());
        FLier.addEtu(Main.stages.get(pan.getSelectedIndex()).getEtu().getPrenom(),Main.stages.get(pan.getSelectedIndex()).getEtu().getNom());
        Main.stages.remove(pan.getSelectedIndex());
        change();
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
        if(etu.contains(prenom+" "+nom)){
            return true;
        }
        return false;
    }

    public void change() {
        etu.removeAllElements();
        for(i=0;i<Main.stages.size();i++){
            etu.addElement(Main.stages.get(i).getEtu());


        }
        refresh();

    }
}
