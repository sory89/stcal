package com.stcal.fen;

import com.stcal.control.Datas;
import com.stcal.don.DCouple;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.util.ArrayList;

public class FStage extends FTab {

    protected JList Fstage;
    protected JLabel info = new JLabel("<html>Sélectionnez un stage pour afficher ses informations.</html>");
    protected JPanel option = new JPanel();
    protected JLabel infosEtu;
    protected JLabel infosTut;
    public static DynamicTreeDemo newContentPane;
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
                askInfo();
            }
        });


        final JPanel PanelCpl = new JPanel();
        PanelCpl.setLayout(new BorderLayout());
        PanelCpl.setOpaque(false);
        PanelCpl.add(new JLabel("Liste des stages", SwingConstants.CENTER), BorderLayout.NORTH);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create and set up the content pane.
                newContentPane = new DynamicTreeDemo();
                PanelCpl.add(newContentPane, BorderLayout.CENTER);
                newContentPane.getTree().addTreeSelectionListener(new TreeSelectionListener() {
                    @Override
                    public void valueChanged(TreeSelectionEvent e) {
                        askInfo();            }
                });
            }
        });
        PanelCpl.add(new JScrollPane(Fstage), BorderLayout.CENTER);
        pan().add(PanelCpl);
        JPanel right = new JPanel();
        right.setOpaque(false);
        right.setLayout(new GridLayout(2,0));

        infosEtu = new JLabel();
        infosTut = new JLabel();
        JTabbedPane ongletInfos = new JTabbedPane();
        ongletInfos.add("Étudiant",infosEtu);
        ongletInfos.add("Tuteur", infosTut);
        ongletInfos.setBorder(BorderFactory.createTitledBorder("Informations"));

        // event
        right.add(new JScrollPane(ongletInfos));
        right.add(info);
        pan().add(right);
    }

    /**
     *
     * @param
     */
    protected void askInfo(){
        try {
            if(newContentPane.isDCoupleSelected()){
                DCouple cpl = (DCouple)((DefaultMutableTreeNode)(newContentPane.getTreePanel().tree).getLastSelectedPathComponent()).getUserObject();
                infosEtu.setText(setInfo(cpl.getEtu().getInfos()));
                infosTut.setText(setInfo(cpl.getTut().getInfos()));
                System.out.println("infos focus du jtree : "+cpl.getEtu().getNom());
            }
            else{
                infosTut.setText("");
                infosEtu.setText("");
            }
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

    public void refreshTree(){
        newContentPane.populateTree(newContentPane.getTreePanel());
    }
}
