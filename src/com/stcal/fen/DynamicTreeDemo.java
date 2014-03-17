package com.stcal.fen;

import com.stcal.control.Datas;
import com.stcal.don.DCouple;
import com.stcal.don.DEtudiant;
import com.stcal.don.DTuteur;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DynamicTreeDemo extends JPanel implements ActionListener {
    private static String REMOVE_COMMAND = "remove";


    public DynamicTree treePanel;
    public DynamicTree getTreePanel() {
        return treePanel;
    }
    public JTree getTree(){
        return getTreePanel().getTree();
    }

    public JButton removeButton;
    public DynamicTreeDemo() {
        super(new BorderLayout());

        treePanel = new DynamicTree();
        populateTree(treePanel);


        removeButton = new JButton("Supprimer stage");
        removeButton.setEnabled(false);
        removeButton.setActionCommand(REMOVE_COMMAND);
        removeButton.addActionListener(this);

        treePanel.getTree().addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                removeButton.setEnabled(!isSelectionEmpty() && isDCoupleSelected());
            }
        });

        // Lay everything out.
        treePanel.setPreferredSize(new Dimension(300, 150));
        add(treePanel, BorderLayout.CENTER);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(removeButton);
        add(panel, BorderLayout.NORTH);

    }

    private ArrayList<DTuteur> getEachTut(DefaultListModel<DCouple> listCouple){
        ArrayList<DTuteur> eachTut = new ArrayList<DTuteur>();
        if(eachTut.isEmpty()){
            eachTut.add(listCouple.get(0).getTut());
        }
        int nb=-1;
        for(int i=0; i<listCouple.size() ; i++){
            nb=0;
            for(int j=0; j<eachTut.size(); j++){
                if(listCouple.get(i).getTut().getNom()==eachTut.get(j).getNom() && listCouple.get(i).getTut().getPrenom()==eachTut.get(j).getPrenom()){
                    nb++;
                }
            }
            if(nb==0){
                eachTut.add(listCouple.get(i).getTut());
            }
        }
        return eachTut;
    }

    public boolean isSelectionEmpty(){
        if(treePanel.tree.isSelectionEmpty())
            return true;
        return false;
    }

    public void populateTree(DynamicTree treePanel) {
        ArrayList<DTuteur> ltut=new ArrayList<DTuteur>();
        treePanel.clear();
        if(!Datas.stages.isEmpty())
            ltut= getEachTut(Datas.stages);

        System.out.println("Nombre de profs : "+ltut.size());
        for(int i = 0; i < ltut.size(); i++){
            DefaultMutableTreeNode tuteur = new DefaultMutableTreeNode(ltut.get(i));
            //Et une branche en plus ! Une !
            for(int j=0; j<Datas.stages.size(); j++){
                if(Datas.stages.getElementAt(j).getTut().getNom()==ltut.get(i).getNom() && Datas.stages.getElementAt(j).getTut().getPrenom()==ltut.get(i).getPrenom()){
                    tuteur.add(new DefaultMutableTreeNode(Datas.stages.getElementAt(j)));
                }
            }
            treePanel.getRootNode().add(tuteur);
        }
        treePanel.expandAll();
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (REMOVE_COMMAND.equals(command)) {
            treePanel.removeDCoupleFromJtree(Datas.stages);
            treePanel.expandAll();
        }
    }

    public Boolean isDCoupleSelected(){
        if(((DefaultMutableTreeNode)this.getTree().getLastSelectedPathComponent()).isLeaf() && !((DefaultMutableTreeNode)this.getTree().getLastSelectedPathComponent()).isRoot())
            return true;
        return false;
    }
}