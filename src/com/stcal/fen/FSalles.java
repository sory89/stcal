package com.stcal.fen;

import com.stcal.control.Datas;
import com.stcal.control.Message;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: val
 * Date: 23/03/14
 * Time: 01:41
 * To change this template use File | Settings | File Templates.
 */

// TODO finir salles
public class FSalles {

    private JPanel pan;
    private JList<String> listSalles ;
    private JButton delete;
    private JButton add;
    private JTextField salleField;

    public FSalles(){
        pan = new JPanel();
        listSalles = new JList<String>(Datas.salles);
        delete = new JButton("Supprimer");
        add = new JButton("Ajouter");
        salleField = new JTextField();
    }

    public void pop(){

        delete.setEnabled(false);
        delete.setToolTipText("Selectionner une salle dans la liste pour la supprimer");
        add.setEnabled(false);
        add.setToolTipText("Compléter le champs \"nouvelle salle\" pour ajouter une salle dans la liste");

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Datas.salles.contains(salleField.getText())){
                    JOptionPane errPane = new JOptionPane();
                    errPane.showMessageDialog(null, "Impossible d'ajouter cette salle, elle existe déjà !", "Erreur dans l'ajout de la salle", JOptionPane.ERROR_MESSAGE);
                }
                else {
                Datas.salles.addElement(salleField.getText());
                salleField.setText("");
                }
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Datas.salles.removeElement(listSalles.getSelectedValue());
            }
        });

        listSalles.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                delete.setEnabled(!listSalles.isSelectionEmpty());

            }
        });

        salleField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                add.setEnabled(!salleField.getText().equals(""));
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                add.setEnabled(!salleField.getText().equals(""));            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                add.setEnabled(!salleField.getText().equals(""));               }
        });

        JLabel salletxt = new JLabel("Nouvelle salle : ");
        JPanel optionPanel = new JPanel(new GridLayout(2,2,10,5));
        optionPanel.add(salletxt);
        optionPanel.add(salleField);
        optionPanel.add(delete);
        optionPanel.add(add);
        optionPanel.setBorder(BorderFactory.createTitledBorder("Ajout/Suppression"));

        pan.setLayout(new BorderLayout());
        pan.add(new JLabel("Liste des salles", SwingConstants.CENTER), BorderLayout.NORTH);
        pan.add(new JScrollPane(listSalles),BorderLayout.CENTER);
        pan.add(optionPanel, BorderLayout.SOUTH);

        JOptionPane pop = new JOptionPane();
        Message.out.println("Ouverture paramètre salles ");
        pop.showMessageDialog(null, pan, "Paramètrage des salles", JOptionPane.PLAIN_MESSAGE);


    }

}
