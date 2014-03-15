package com.stcal.fen;

import com.stcal.Main;
import com.stcal.control.Datas;
import com.stcal.control.Message;
import com.stcal.control.exceptions.NoSuchSettingException;
import com.stcal.control.exceptions.NothingToSaveException;
import com.stcal.control.parserPeriod;
import com.stcal.don.CustomRenderer;
import com.stcal.don.DCouple;
import com.stcal.don.DCreneau;
import com.stcal.don.Soutenance;
import datechooser.beans.DateChooserPanel;
import datechooser.events.SelectionChangedEvent;
import datechooser.events.SelectionChangedListener;
import datechooser.model.multiple.Period;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class FCal extends FTab{
    protected DefaultListModel etu = new DefaultListModel();
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
    protected JTable jt=null;
    protected JButton supprimers = new JButton("Supprimer ce stage");
    protected JFormattedTextField creneau = null;
    protected JFormattedTextField soutenance = null;
    protected Iterator<Period> datechoisis=null;
    protected ArrayList<Calendar> recupDates = null;
    protected  parserPeriod PP = null;
    protected DCoupleTransferHandler kikoo = new DCoupleTransferHandler();
    protected DefaultTableModel salles = null;
    protected JDialog jdi=null;
    protected JButton okPlageJour = new JButton("Valider votre selection");



    /* Test d'activation du bouton de validation du formulaire
       Il suffit de changer le if pour que les tests sur les composants du formulaire soient mis à jours
     */
    protected boolean condition(){

        if(!(finJour.getSelectedIndex()==-1) && !(debutJour.getSelectedIndex()==-1) && !(soutenance.getText().isEmpty()) && soutenance.getText().matches("\\d{1,10}") && !chooserDebut.getSelectedPeriodSet().isEmpty() && creneau.getText().matches("\\d{1,10}") && !(creneau.getText().isEmpty())) {

            return true ;

        }
        else {
            return false;
        }
    }

    public FCal() {
        super("Calendrier");

            creneau = new JFormattedTextField();
            soutenance = new JFormattedTextField();




        test1.setOpaque(false);
        infoChooser.setOpaque(false);
        okPlageJour.setEnabled(false);



        /* Listener du bouton de validation de formulaire */
        okPlageJour.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {

                //Récupération des des periode sous forme d'iterator
                datechoisis = chooserDebut.getSelection().iterator();
                //Transformation des Period en une arraylist de jours ouvrables
                PP = new parserPeriod(datechoisis);
                recupDates = (ArrayList<Calendar>) PP.getDates();

                //Calcul du nombre de creneau dans une journée
                final int trololo = ((Integer.parseInt(finJour.getSelectedItem().toString()) - Integer.parseInt(debutJour.getSelectedItem().toString())) * 60) / Integer.parseInt(creneau.getText());

                pan().removeAll();
                pan().setLayout(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();

                c.fill = GridBagConstraints.BOTH;

                c.gridx = 0;
                c.gridy = 0;

                c.gridwidth = 1;
                c.gridheight = trololo;
                c.weightx = 0;
                c.weighty = 1;
                c.ipadx = 200;


                DCreneau o[][] = new DCreneau[trololo][recupDates.size()];
                Main.colors=new String[trololo][recupDates.size()];

                int dj = Integer.parseInt(debutJour.getSelectedItem().toString());
                int fj = Integer.parseInt(finJour.getSelectedItem().toString());
                int k;
                int i;
                for (k = 0; k < recupDates.size(); k++) {
                    for (i = 0; i < trololo; i++) {

                        o[i][k] = new DCreneau();
                        o[i][k].setDate_debut(new GregorianCalendar(recupDates.get(k).get(Calendar.YEAR), recupDates.get(k).get(Calendar.MONTH), recupDates.get(k).get(Calendar.DAY_OF_MONTH), dj + i, 0));
                        o[i][k].setDate_fin(new GregorianCalendar(recupDates.get(k).get(Calendar.YEAR), recupDates.get(k).get(Calendar.MONTH), recupDates.get(k).get(Calendar.DAY_OF_MONTH), dj + i + 1, 0));
                        o[i][k].setMax_sout(Integer.parseInt(soutenance.getText()));
                        Main.colors[i][k]="white";

                    }


                }


                pan().addComponentListener(new ComponentListener() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        jt.setRowHeight((pan().getHeight() - 20) / trololo);
                    }

                    @Override
                    public void componentMoved(ComponentEvent e) {
                        //To change body of implemented methods use File | Settings | File Templates.
                    }

                    @Override
                    public void componentShown(ComponentEvent e) {
                        //To change body of implemented methods use File | Settings | File Templates.
                    }

                    @Override
                    public void componentHidden(ComponentEvent e) {
                        //To change body of implemented methods use File | Settings | File Templates.
                    }
                });
                String titre[] = new String[recupDates.size()];
                final DefaultComboBoxModel fet = new DefaultComboBoxModel();
                final JList Fetu = new JList(fet);
                pan().add(new JScrollPane(Fetu), c);
                int j;
                for (j = 0; j < Datas.stages.size(); j++) {

                    fet.addElement(Datas.stages.get(j));
                    Message.out.println(Datas.stages.get(j).getClass());
                }
                Fetu.setDragEnabled(true);
                Fetu.setTransferHandler(kikoo);


                for (i = 0; i < recupDates.size(); i++)
                    titre[i] = "" + recupDates.get(i).get(Calendar.DAY_OF_MONTH) + "/" + (recupDates.get(i).get(Calendar.MONTH) + 1) + "/" + recupDates.get(i).get(Calendar.YEAR) + "";


                jt = new JTable(o, titre) {

                    public boolean isCellEditable(int row, int column) {

                        return false;

                    }

                };
                jt.setCellSelectionEnabled(true);
                jt.setRowSelectionAllowed(false);

                jt.setEnabled(true);
                jt.setRowHeight((pan().getHeight() - 20) / trololo);
                jt.setDropMode(DropMode.ON);
                jt.setDragEnabled(true);
                jt.setTransferHandler(kikoo);
                jt.setColumnSelectionAllowed(false);
                GridBagConstraints cc = new GridBagConstraints();
                cc.fill = GridBagConstraints.BOTH;
                cc.gridx = 1;
                cc.gridy = 0;

                cc.gridwidth = recupDates.size();
                cc.gridheight = trololo + 1;
                cc.weightx = 1;
                cc.weighty = 1;

                pan().add(new JScrollPane(jt), cc);
                jt.setDefaultRenderer(Object.class,new CustomRenderer());
                final DefaultComboBoxModel fs = new DefaultComboBoxModel();
                final JList jls = new JList(fs);
                c = new GridBagConstraints();
                Fetu.addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        int y;
                        int i;
                        DCouple person = (DCouple) Fetu.getSelectedValue() ;
                        if(person!=null){
                            for (y=0;y<jt.getRowCount();y++){
                                for(i=0;i<jt.getColumnCount();i++)  {

                                    DCreneau dte = (DCreneau) jt.getValueAt(y,i);

                                    if(dte.isProfIn(person.getTut()) && dte.toStringtest().size()<dte.getMax_sout() ){
                                        Main.colors[y][i]="green";



                                    }else
                                    {
                                        Main.colors[y][i]="red";
                                    }

                                } }}
                        jt.setDefaultRenderer(Object.class,new CustomRenderer());
                        jt.revalidate();
                        jt.repaint();
                        refresh();
                    }
                });
                c.fill = GridBagConstraints.BOTH;

                c.gridx = recupDates.size() + 1;
                c.gridy = 0;

                c.gridwidth = 1;
                c.gridheight = trololo / 2;
                c.weightx = 0;
                c.weighty = 1;
                c.ipadx = 200;
                pan().add(new JScrollPane(jls), c);

                c.fill = GridBagConstraints.BOTH;

                c.gridx = recupDates.size() + 1;
                c.gridy = trololo/2;

                c.gridwidth = 1;
                c.gridheight = 1;
                c.weightx = 0;
                c.weighty = 1;
                c.ipadx = 200;
                pan().add(supprimers,c);


                refresh();
                supprimers.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(fs.getSelectedItem()!=null){
                            DCouple dfc= (DCouple) jls.getSelectedValue() ;
                            Datas.stages.addElement(dfc);
                            DCreneau dc = (DCreneau) jt.getValueAt(jt.getSelectedRow(), jt.getSelectedColumn());
                            dc.removedcp(dfc);

                            fs.removeAllElements();

                            int m;
                            ArrayList<Soutenance> ars = dc.toStringtest();
                            for (m = 0; m < ars.size(); m++)
                                fs.addElement(ars.get(m).getCpl());
                            refresh();


                        }
                    }
                });

                jt.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //To change body of implemented methods use File | Settings | File Templates.
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        int j;
                        fet.removeAllElements();
                        for(j=0;j<Datas.stages.size();j++){

                            fet.addElement(Datas.stages.get(j));
                            Message.out.println(j);
                        }
                        jt.setDefaultRenderer(Object.class,new CustomRenderer());
                        refresh();                }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        fs.removeAllElements();
                        DCreneau dc = (DCreneau) jt.getValueAt(jt.getSelectedRow(), jt.getSelectedColumn());
                        int m;
                        ArrayList<Soutenance> ars = dc.toStringtest();
                        for (m = 0; m < ars.size(); m++)
                            fs.addElement(ars.get(m).getCpl());

                        int j;
                        fet.removeAllElements();
                        for(j=0;j<Datas.stages.size();j++){

                            fet.addElement(Datas.stages.get(j));



                        }

                        refresh();
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        int j;
                        fet.removeAllElements();
                        for(j=0;j<Datas.stages.size();j++){

                            fet.addElement(Datas.stages.get(j));
                            Message.out.println(j);
                        }
                        refresh();                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        int j;
                        fet.removeAllElements();
                        for(j=0;j<Datas.stages.size();j++){

                            fet.addElement(Datas.stages.get(j));
                            Message.out.println(j);
                        }
                        refresh();                    }
                });
                try {
                    Main.calsettings.set("cal", recupDates.toString());
                    Main.calsettings.set("dursoutenance", creneau.getText());
                    Main.calsettings.set("nbsoutenance", soutenance.getText());
                    Main.calsettings.set("debutj", debutJour.getSelectedItem().toString());
                    Main.calsettings.set("finj", finJour.getSelectedItem().toString());


                } catch (NoSuchSettingException e1) {
                    Message.err.println(e1.getMessage());
                }
                try {
                    Main.calsettings.save();
                } catch (NothingToSaveException e1) {
                    Message.err.println(e1.getMessage());
                }
                refresh();


            }
        }

        );

        chooserDebut.addSelectionChangedListener(new SelectionChangedListener() {

            public void onSelectionChange(SelectionChangedEvent selectionChangedEvent) {

                if(condition())
                    okPlageJour.setEnabled(true);
                else
                    okPlageJour.setEnabled(false);
            }
        });

        pan().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                    if (condition())
                        okPlageJour.setEnabled(true);
                    else
                        okPlageJour.setEnabled(false);



            }

            @Override
            public void mousePressed(MouseEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        debutJour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finJour.removeAllItems();
                int tmp = Integer.parseInt(debutJour.getSelectedItem().toString());

                for (tmp = tmp + 1; tmp < 20; tmp++) {
                    finJour.addItem(tmp);

                }
                finJour.setSelectedIndex(-1);
                okPlageJour.setEnabled(false);
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
        pancreneau.setOpaque(false);
        panSoutenance.setLayout(new GridLayout(4, 0));
        panSoutenance.setOpaque(false);
        test1.setLayout(new GridLayout(4, 0));
        test4.setLayout(new GridLayout(4, 0));
        test4.setOpaque(false);
        test5.setLayout(new GridLayout(3, 0));
        test5.setOpaque(false);
        test1.add(chooserDebut, BorderLayout.NORTH);
        test2.setLayout(new GridLayout(4, 0));
        test2.setOpaque(false);
        panfin.setLayout(new GridLayout(4, 0));
        panfin.setOpaque(false);
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
