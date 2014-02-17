package com.stcal.fen;

import com.stcal.Main;
import com.stcal.control.Datas;
import com.stcal.don.DCouple;
import com.stcal.don.DEtudiant;
import com.stcal.don.Type;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FStage extends FTab {


    protected DefaultListModel etu = new DefaultListModel();
    protected DefaultListModel stag = new DefaultListModel();
    protected JLabel info = new JLabel("<html>Sélectionner un stage pour afficher ses informations.</html>");
    protected JPanel option = new JPanel();
    protected ArrayList<String> liste =new ArrayList<String>();
    protected ArrayList<String> tutPre = new ArrayList<String>();
    protected ArrayList<String> tutNom = new ArrayList<String>();
    protected String selectedEtuPre = "";
    protected String selectedEtuNom = "";
    protected Type selectedType = Type.NONE;
    int i;

    protected JList Fstage;

    /**
     * Initialisation de l'onglet
     */
    public FStage(){
        super("Stage");
        pan().setLayout(new GridLayout(0, 2));
        Fstage = new JList<DCouple>(Datas.stages);
        Fstage.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Fstage.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                askInfo(Fstage, Type.ETUDIANT);
            }
        });


        pan().add(new JScrollPane(Fstage));
        JPanel right = new JPanel();
        right.setOpaque(false);
        right.setLayout(new GridLayout(3,0));
        final  JList Fstag = new JList(stag);
        // event
        right.add(new JScrollPane(Fstag));
        info.setBorder(BorderFactory.createTitledBorder("Infos"));
        right.add(info);
        option.setOpaque(false);
        option.setLayout(new GridLayout(3,1));
        right.add(option);
        final JButton supprimer=new JButton("Supprimer stage");
        supprimer.setEnabled(false);
        supprimer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                supprimer_stage(Fstage);
                Datas.stages.remove(Fstage.getSelectedIndex());
                Fstage.clearSelection();
            }
        });
        option.add(supprimer);
        Fstage.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                supprimer.setEnabled(!Fstage.isSelectionEmpty());
            }
        });

        pan().add(right);
    }

    /**
     *
     * @param pan
     * @param type Constante de Main.ETUDIANT/Main.TUTEUR/Main.NONE
     */
    protected void askInfo(JList pan,Type type){
        try {
            if (type.equals(Type.ETUDIANT)){
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






   public void supprimer_stage(JList<DCouple> pan){
       Datas.etu.addElement(pan.getSelectedValue().getEtu());
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
        for(i=0;i< Datas.stages.size();i++){
            etu.addElement(Datas.stages.get(i).getEtu());


        }
        refresh();

    }
}
