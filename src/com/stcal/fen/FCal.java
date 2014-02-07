package com.stcal.fen;

import com.stcal.Main;
import com.stcal.control.CALsettings;
import com.stcal.control.exceptions.NoSuchSettingException;
import com.stcal.control.exceptions.NothingToSaveException;
import com.stcal.control.exceptions.UncreatableSettingException;
import datechooser.beans.DateChooserPanel;
import datechooser.events.SelectionChangedEvent;
import datechooser.events.SelectionChangedListener;
import datechooser.model.multiple.Period;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

public class FCal extends FTab{

    protected JPanel test1 = new JPanel();
    protected JPanel test2 = new JPanel();
    protected JPanel panfin = new JPanel();
    protected JPanel test4 = new JPanel();
    protected JPanel pancreneau = new JPanel();
    protected JPanel test5 = new JPanel();
    protected JPanel panSoutenance = new JPanel();
    protected JLabel infoChooser = new JLabel("Choisissez les jours de soutenance");
    protected JLabel creneauLabel = new JLabel("Durée en minutes d'une soutenance");
    protected JLabel debutLabel = new JLabel("Début d'une journée");
    protected JLabel finLabel = new JLabel("Fin d'une journée");
    protected JLabel nbSoutenances = new JLabel("Nombre de Soutenance par créneau");
    String[] tab =  {"7","8","9","10","11","12","13","14","15","16","17","18","19","20"} ;
    protected JComboBox debutJour = new JComboBox(tab);
    protected JComboBox finJour = new JComboBox();
    public DateChooserPanel chooserDebut= new DateChooserPanel();


    protected JFormattedTextField creneau = null;
    protected JFormattedTextField soutenance = null;
    protected Iterator<Period> datechoisis=null;
    protected List<Calendar> recupDates = null;
    protected  parserPeriod PP = null;


    protected JButton okPlageJour = new JButton("Valider votre selection");
    protected boolean condition(){

        if(!(soutenance.getText().isEmpty()) && !chooserDebut.getSelectedPeriodSet().isEmpty() && !creneau.getText().isEmpty()) {

            return true ;

        }
              else {
            return false;
        }

    }
    public FCal() {
        super("cal");

        creneau = new JFormattedTextField();
        soutenance = new JFormattedTextField();



        test1.setOpaque(false);
        test1.setOpaque(false);
        infoChooser.setOpaque(false);
        okPlageJour.setEnabled(false);
          chooserDebut.addSelectionChangedListener(new SelectionChangedListener() {

              public void onSelectionChange(SelectionChangedEvent selectionChangedEvent) {

                  if(condition())
                      okPlageJour.setEnabled(true);
                  else
                      okPlageJour.setEnabled(false);
              }
          });
        okPlageJour.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                datechoisis = chooserDebut.getSelection().iterator();
                PP = new parserPeriod(datechoisis);
                recupDates = PP.getDates();
                pan().removeAll();
                try {
                    Main.calsettings.set("cal", recupDates.toString());
                    Main.calsettings.set("dursoutenance", creneau.getText());
                    Main.calsettings.set("nbsoutenance", soutenance.getText());
                    Main.calsettings.set("debutj", debutJour.getSelectedItem().toString());
                    Main.calsettings.set("finj", finJour.getSelectedItem().toString());


                } catch (NoSuchSettingException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                try {
                    Main.calsettings.save();
                } catch (UncreatableSettingException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (NothingToSaveException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                refresh();


            }
        }

        );
        pan().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (condition())
                    okPlageJour.setEnabled(true);
                else
                    okPlageJour.setEnabled(false);
            }
        });
        debutJour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finJour.removeAllItems();
                int tmp =Integer.parseInt(debutJour.getSelectedItem().toString());

                for(tmp=tmp+1;tmp<20;tmp++){
                    finJour.addItem(tmp);

                }
                refresh();
            }
        });

        pan().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(condition())
                    okPlageJour.setEnabled(true);
                else
                    okPlageJour.setEnabled(false);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(condition())
                    okPlageJour.setEnabled(true);
                else
                    okPlageJour.setEnabled(false);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(condition())
                    okPlageJour.setEnabled(true);
                else
                    okPlageJour.setEnabled(false);
            }
        });
        creneau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(condition())
                    okPlageJour.setEnabled(true);
                else
                    okPlageJour.setEnabled(false);

            }

        });
        soutenance.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(condition())
                    okPlageJour.setEnabled(true);
                else
                    okPlageJour.setEnabled(false);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(condition())
                    okPlageJour.setEnabled(true);
                else
                    okPlageJour.setEnabled(false);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(condition())
                    okPlageJour.setEnabled(true);
                else
                    okPlageJour.setEnabled(false);
            }
        });
        creneau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(condition())
                    okPlageJour.setEnabled(true);
                else
                    okPlageJour.setEnabled(false);

            }

        });
        creneau.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(condition())
                    okPlageJour.setEnabled(true);
                else
                    okPlageJour.setEnabled(false);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(condition())
                    okPlageJour.setEnabled(true);
                else
                    okPlageJour.setEnabled(false);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(condition())
                    okPlageJour.setEnabled(true);
                else
                    okPlageJour.setEnabled(false);
            }
        });
        creneau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(condition())
                    okPlageJour.setEnabled(true);
                else
                    okPlageJour.setEnabled(false);

            }

        });
        soutenance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (condition())
                    okPlageJour.setEnabled(true);
                else
                    okPlageJour.setEnabled(false);


            }
        });

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
