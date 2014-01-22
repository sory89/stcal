package com.stcal.fen;

import datechooser.beans.DateChooserPanel;
import datechooser.events.SelectionChangedEvent;
import datechooser.events.SelectionChangedListener;

import javax.swing.*;
import java.awt.*;


public class FCal extends FTab{
    String test=null;
    protected JPanel test1 = new JPanel();
    protected JPanel test2 = new JPanel();
    protected JLabel infoChooser = new JLabel("Choisissez les jours de soutenance");
    public DateChooserPanel chooserDebut= new DateChooserPanel();
    public DateChooserPanel chooserFin= new DateChooserPanel();
    protected JButton okPlageJour = new JButton("Valider votre selection");
    public FCal() {
        super("cal");
        test1.setOpaque(false);
        test1.setOpaque(false);
        infoChooser.setOpaque(false);
        chooserDebut.setOpaque(false);
        chooserFin.setOpaque(false);
        okPlageJour.setEnabled(false);
        chooserDebut.addSelectionChangedListener(new SelectionChangedListener() {
            @Override
            public void onSelectionChange(SelectionChangedEvent selectionChangedEvent) {
                if(chooserDebut.getSelectedPeriodSet().isEmpty()==false)
                    okPlageJour.setEnabled(true);
                else
                    okPlageJour.setEnabled(false);
            }
        });
        /*okPlageJour.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });*/
        okPlageJour.validate();
        pan().setLayout(new GridLayout(0,3));
        Dimension dim = new Dimension(1,1);
        test1.add(infoChooser);
        test1.setLayout(new GridLayout(4,0));
        test1.add(chooserDebut,BorderLayout.NORTH);
        test2.setLayout(new GridLayout(4,0));
        test2.add(okPlageJour);
        test1.add(test2);
        test1.setSize(dim);
        pan().add(test1,BorderLayout.WEST);





    }



}
