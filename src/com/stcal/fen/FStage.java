package com.stcal.fen;

import com.stcal.control.Datas;
import com.stcal.don.DCouple;
import com.stcal.don.Type;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FStage extends FTab {

    protected JList Fstage;
    protected JLabel info = new JLabel("<html>Sélectionner un stage pour afficher ses informations.</html>");
    protected JPanel option = new JPanel();
    protected JLabel infosEtu;
    protected JLabel infosTut;
    /**
     * Initialisation de l'onglet
     */
    public FStage(){
        super("Stage");
        pan().setLayout(new GridLayout(0, 2,10,10));
        Fstage = new JList<DCouple>(Datas.stages);
        Fstage.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Fstage.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                askInfo(Fstage);
            }
        });

        JPanel PanelCpl = new JPanel();
        PanelCpl.setLayout(new BorderLayout());
        PanelCpl.setOpaque(false);
        PanelCpl.add(new JLabel("Liste des stages", SwingConstants.CENTER),BorderLayout.NORTH);
        PanelCpl.add(new JScrollPane(Fstage), BorderLayout.CENTER);
        pan().add(PanelCpl);
        JPanel right = new JPanel();
        right.setOpaque(false);
        right.setLayout(new GridLayout(3,0));

        infosEtu = new JLabel();
        infosTut = new JLabel();
        JTabbedPane ongletInfos = new JTabbedPane();
        ongletInfos.add("Étudiant",infosEtu);
        ongletInfos.add("Tuteurs", infosTut);
        ongletInfos.setBorder(BorderFactory.createTitledBorder("Informations"));

        // event
        right.add(new JScrollPane(ongletInfos));
        info.setBorder(BorderFactory.createTitledBorder("Courant"));
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
                infosTut.setText("");
                infosEtu.setText("");
                info.setText("Stage supprimé.");
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
     */
    protected void askInfo(JList<DCouple> pan){
        try {
            infosEtu.setText(setInfo(pan.getSelectedValue().getEtu().getInfos()));
            infosTut.setText(setInfo(pan.getSelectedValue().getTut().getInfos()));
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
    public String setInfo(ArrayList<String> details){
        String newInfo = "<html>";
        info.setText("");
        for (int i=0;i<details.size();i++){
            newInfo += details.get(i) + "<br />";
        }
        newInfo += "</html>";
        refresh();
        return newInfo;
    }
}
