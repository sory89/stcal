package com.stcal.fen;

import com.stcal.don.Jour;
import datechooser.beans.DateChooserPanel;
import datechooser.events.SelectionChangedEvent;
import datechooser.events.SelectionChangedListener;
import datechooser.model.multiple.Period;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class FCal extends FTab{

    protected JPanel test1 = new JPanel();
    protected JPanel test2 = new JPanel();
    protected JPanel panfin = new JPanel();
    protected JPanel test4 = new JPanel();
    protected JPanel pancreneau = new JPanel();
    protected JPanel test5 = new JPanel();
    protected JPanel panSoutenance = new JPanel();
    protected JLabel infoChooser = new JLabel("Choisissez les jours de soutenance");
    protected JLabel creneauLabel = new JLabel("Durée en minutes d'un créneau");
    protected JLabel debutLabel = new JLabel("Début d'une journée");
    protected JLabel finLabel = new JLabel("Fin d'une journée");
    protected JLabel nbSoutenances = new JLabel("Nombre de Soutenance par créneau");
    String[] tab =  {"7","8","9","10","11","12","13","14","15","16","17","18","19","20"} ;
    protected JComboBox debutJour = new JComboBox(tab);
    protected JComboBox finJour = new JComboBox(tab);
    public DateChooserPanel chooserDebut= new DateChooserPanel();
    protected JTextField creneau = new JTextField();
    protected JTextField soutenance = new JTextField();
    protected Iterator<Period> datechoisis=null;
    protected ArrayList<Calendar> recupDates = null;
    protected  parserPeriod PP = null;
    protected JButton okPlageJour = new JButton("Valider votre selection");
    public FCal() {
        super("cal");
        test1.setOpaque(false);
        test1.setOpaque(false);
        infoChooser.setOpaque(false);
        okPlageJour.setEnabled(false);
          chooserDebut.addSelectionChangedListener(new SelectionChangedListener() {
              @Override
              public void onSelectionChange(SelectionChangedEvent selectionChangedEvent) {
                  if(chooserDebut.getSelectedPeriodSet().isEmpty()==false )
                  okPlageJour.setEnabled(true);
                  else
                  okPlageJour.setEnabled(false);
              }
          });
        okPlageJour.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                datechoisis=chooserDebut.getSelection().iterator();
                PP= new parserPeriod(datechoisis);
                recupDates = PP.getDates();
                System.out.println(recupDates);


            }
        }

        );
        okPlageJour.validate();
        pan().setLayout(new GridLayout(0,3));
        Dimension dim = new Dimension(1,1);
        test1.add(infoChooser);
        pancreneau.setLayout(new GridLayout(4, 0));
        panSoutenance.setLayout(new GridLayout(4, 0));
        test1.setLayout(new GridLayout(4, 0));
        test4.setLayout(new GridLayout(4, 0));
        test5.setLayout(new GridLayout(3, 0));
        test1.add(chooserDebut, BorderLayout.NORTH);
        test2.setLayout(new GridLayout(4, 0));
        panfin.setLayout(new GridLayout(4, 0));
        pancreneau.add(creneauLabel);
        pancreneau.add(creneau);
        panSoutenance.add(nbSoutenances);
        panSoutenance.add(soutenance);
        test5.add(okPlageJour,BorderLayout.CENTER);
         test1.add(test2);
        panfin.add(debutLabel);
        panfin.add(debutJour);
        panfin.add(finLabel);
        panfin.add(finJour);
        test4.add(pancreneau);
        test4.add(panfin);
        test4.add(panSoutenance);
        test1.setSize(dim);
        pan().add(test1,BorderLayout.WEST);
        pan().add(test4,BorderLayout.CENTER);
        pan().add(test5,BorderLayout.EAST);




    }



}
