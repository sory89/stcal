package com.stcal.fen;

import com.stcal.Main;
import com.stcal.control.CustomRenderer;
import com.stcal.control.Datas;
import com.stcal.control.Message;
import com.stcal.control.exceptions.NoSuchSettingException;
import com.stcal.control.exceptions.NothingToSaveException;
import com.stcal.control.parserPeriod;
import com.stcal.don.*;
import datechooser.beans.DateChooserPanel;
import datechooser.events.SelectionChangedEvent;
import datechooser.events.SelectionChangedListener;
import datechooser.model.multiple.Period;
import net.fortuna.ical4j.model.DateTime;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class FCal extends FTab{
    protected DefaultListModel etu = new DefaultListModel();
    protected JPanel dateChooserPanel = new JPanel();
    protected JPanel dateSettingsPanel = new JPanel();
    protected JPanel panfin = new JPanel();
    protected JPanel settingsPanel = new JPanel();
    protected JPanel pancreneau = new JPanel();
    protected JPanel validPanel = new JPanel();
    protected JPanel panSoutenance = new JPanel();
    protected JPanel formsalles = new JPanel();
    protected JLabel info = new JLabel("<html>Sélectionnez un stage pour afficher ses informations.</html>");
    protected JLabel infoChooser = new JLabel("Choisissez les jours de soutenance");
    protected JLabel creneauLabel = new JLabel("(4) Durée soutenance (en min)");
    protected JLabel debutLabel = new JLabel("(2) Début journée");
    protected JLabel finLabel = new JLabel("(3) Fin journée");
    protected JLabel nbSoutenances = new JLabel("(5) Nombre soutenances par créneau");
    protected JLabel labcandide = new JLabel("Selectionnez un candide");
    protected JLabel labsalle = new JLabel("Selectionnez la salle");
    String[] tab =  {"7","8","9","10","11","12","13","14","15","16","17","18","19","20"} ;
    protected JComboBox debutJour = new JComboBox(tab);
    protected JComboBox finJour = new JComboBox();
    protected JComboBox jcbsalles = new JComboBox();
    protected JComboBox<DPersonne> jcbcandide = new JComboBox();
    public DateChooserPanel chooserDebut= new DateChooserPanel();
    protected JTable jt=null;
    protected JButton supprimers = new JButton("Supprimer cette soutenance");
    protected JButton okcand = new JButton("Valider");
    public static DynamicTreeDemo newContentPane;
    protected JFormattedTextField creneau = null;
    protected JFormattedTextField soutenance = null;
    protected Iterator<Period> datechoisis=null;
    protected ArrayList<Calendar> recupDates = null;
    protected  parserPeriod PP = null;
    protected DCoupleTransferHandler kikoo = new DCoupleTransferHandler();
    protected DefaultTableModel salles = null;
    protected JButton okPlageJour = new JButton("Générer le planning");

    public void refreshTree(){
        newContentPane.populateTree(newContentPane.getTreePanel());


    }

    /* Test d'activation du bouton de validation du formulaire
       Il suffit de changer le if pour que les tests sur les composants du formulaire soient mis à jours
    */

    protected boolean condition(){
        if(!(finJour.getSelectedIndex()==-1) && !(debutJour.getSelectedIndex()==-1) && !(soutenance.getText().isEmpty()) && soutenance.getText().matches("\\d{1,10}")
                && !chooserDebut.getSelectedPeriodSet().isEmpty() && creneau.getText().matches("\\d{1,10}") && !(creneau.getText().isEmpty()))
            return true ;
        return false;
    }
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

    public FCal() {
        super("Calendrier");
        creneau = new JFormattedTextField();
        soutenance = new JFormattedTextField();
        dateChooserPanel.setOpaque(false);
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
                final int totalCreneaux = ((Integer.parseInt(finJour.getSelectedItem().toString()) - Integer.parseInt(debutJour.getSelectedItem().toString())) * 60) / Integer.parseInt(creneau.getText());

                pan().removeAll();
                pan().setLayout(new GridBagLayout());

                GridBagConstraints c = new GridBagConstraints();

                Datas.o = new DCreneau[totalCreneaux][recupDates.size()];
                Main.colors=new String[totalCreneaux][recupDates.size()];

                int dj = Integer.parseInt(debutJour.getSelectedItem().toString());
                int fj = Integer.parseInt(finJour.getSelectedItem().toString());
                int dureeCreneau = Integer.parseInt(creneau.getText());
                int k;
                int i;
                if(Datas.o[0][0]==null){
                for (k = 0; k < recupDates.size(); k++) {
                    for (i = 0; i < totalCreneaux; i++) {
                        Datas.o[i][k] = new DCreneau();
                        Datas.o[i][k].setDate_debut(new GregorianCalendar(recupDates.get(k).get(Calendar.YEAR), recupDates.get(k).get(Calendar.MONTH), recupDates.get(k).get(Calendar.DAY_OF_MONTH), dj + (int)Math.round(((i*dureeCreneau)/60)-0.5), i*dureeCreneau%60));
                        Datas.o[i][k].setDate_fin(new GregorianCalendar(recupDates.get(k).get(Calendar.YEAR), recupDates.get(k).get(Calendar.MONTH), recupDates.get(k).get(Calendar.DAY_OF_MONTH), dj + (int)Math.round((((i+1)*dureeCreneau)/60)-0.5), (1+i)*dureeCreneau%60));
                        Datas.o[i][k].setMax_sout(Integer.parseInt(soutenance.getText()));
                        Main.colors[i][k]="white";
                    }
                }
                }

                pan().addComponentListener(new ComponentListener() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        jt.setRowHeight((pan().getHeight() - 20) / totalCreneaux);
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


            /*    final JList Fetu = new JList(fet);
                pan().add(new JScrollPane(Fetu), c);
                int j;
                for (j = 0; j < Datas.stages.size(); j++) {
                    fet.addElement(Datas.stages.get(j));
                    Message.out.println(Datas.stages.get(j).getClass());
                }
                Fetu.setDragEnabled(true);
                Fetu.setTransferHandler(kikoo);

*/             javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        GridBagConstraints ca = new GridBagConstraints();

                        ca.fill = GridBagConstraints.BOTH;
                        ca.gridx = 0;
                        ca.gridy = 0;
                        ca.gridwidth = 1;
                        ca.gridheight =totalCreneaux-2;
                        ca.weightx = 0;
                        ca.weighty = 1;
                        ca.ipadx = 100;
                newContentPane = new DynamicTreeDemo();
                pan().add(newContentPane, ca);
                newContentPane.getTree().setDragEnabled(true);
                info.setBorder(BorderFactory.createTitledBorder("Informations"));
                        ca.fill = GridBagConstraints.BOTH;
                        ca.gridx = 0;
                        ca.gridy = totalCreneaux-2;
                        ca.gridwidth = 1;
                        ca.gridheight =2;
                        ca.weightx = 0;
                        ca.weighty = 1;
                        ca.ipadx = 100;

               JScrollPane scrollPane = new JScrollPane(info);
               scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                        pan().add(scrollPane, ca);


                newContentPane.getTree().setTransferHandler(kikoo);
                        newContentPane.getTree().addTreeSelectionListener(new TreeSelectionListener() {
                            @Override
                            public void valueChanged(TreeSelectionEvent e) {
                                int y;
                                int i;
                                if(!newContentPane.getTree().isSelectionEmpty()) {
                                    if(((DefaultMutableTreeNode)(newContentPane.getTreePanel().tree).getLastSelectedPathComponent()).getUserObject().getClass()==DCouple.class){
                                DCouple person = (DCouple) ((DefaultMutableTreeNode) (newContentPane.getTreePanel().tree).getLastSelectedPathComponent()).getUserObject();
                                if (person != null) {
                                    for (y = 0; y < jt.getRowCount(); y++) {
                                        for (i = 0; i < jt.getColumnCount(); i++) {
                                            DCreneau dte = (DCreneau) jt.getValueAt(y, i);
                                            if (dte.isProfIn(person.getTut()) && dte.toStringtest().size() < dte.getMax_sout())
                                                Main.colors[y][i] = "green";
                                            else
                                                Main.colors[y][i] = "red";
                                        }

                                }
                                jt.setDefaultRenderer(Object.class, new CustomRenderer());

                                jt.revalidate();
                                jt.repaint();

                                refresh(); }


                            }   }  }
                        });
                        newContentPane.removeButton.setVisible(false);

                    }
                });

                for (i = 0; i < recupDates.size(); i++)
                    titre[i] = "" + recupDates.get(i).get(Calendar.DAY_OF_MONTH) + "/" + (recupDates.get(i).get(Calendar.MONTH) + 1) + "/" + recupDates.get(i).get(Calendar.YEAR) + "";

                jt = new JTable(new CreneauTableModel(Datas.o,totalCreneaux,recupDates));
                /*
                jt = new JTable(o, titre) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                 */
                //jt.setDefaultRenderer(DCreneau.class, new CreneauCellRenderer());

                jt.setCellSelectionEnabled(true);
                jt.setRowSelectionAllowed(false);

                jt.setEnabled(true);
                jt.setRowHeight((pan().getHeight() - 20) / totalCreneaux);
                jt.setDropMode(DropMode.ON);
                jt.setDragEnabled(true);
                jt.setTransferHandler(kikoo);
                jt.setColumnSelectionAllowed(false);
                GridBagConstraints cc = new GridBagConstraints();
                cc.fill = GridBagConstraints.BOTH;
                cc.gridx = 1;
                cc.gridy = 0;

                cc.gridwidth = recupDates.size();
                cc.gridheight = totalCreneaux + 1;
                cc.weightx = 1;
                cc.weighty = 1;

                pan().add(new JScrollPane(jt), cc);
                jt.setDefaultRenderer(Object.class,new CustomRenderer());
                final DefaultComboBoxModel fs = new DefaultComboBoxModel();
                final JList jls = new JList(fs);
                c = new GridBagConstraints();


                c.fill = GridBagConstraints.BOTH;

                c.gridx = recupDates.size() + 1;
                c.gridy = 0;

                c.gridwidth = 1;
                c.gridheight = totalCreneaux /2;
                c.weightx = 0;
                c.weighty = 1;
                c.ipadx = 100;
                pan().add(new JScrollPane(jls), c);

                c.fill = GridBagConstraints.BOTH;

                c.gridx = recupDates.size() + 1;
                c.gridy = (totalCreneaux/2);

                c.gridwidth = 1;
                c.gridheight = 1;
                c.weightx = 0;
                c.weighty = 1;
                c.ipadx = 100;
                pan().add(formsalles,c);
                formsalles.setLayout(new GridLayout(6,0));
                 formsalles.add(supprimers);
                formsalles.add(labsalle);
                formsalles.add(jcbsalles);
                formsalles.add(labcandide);
                formsalles.add(jcbcandide);
                formsalles.add(okcand);




                refresh();
                supprimers.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(fs.getSelectedItem()!=null){
                            DCouple dfc= (DCouple) jls.getSelectedValue() ;
                            Datas.stages.addElement(dfc);


                            FStage.newContentPane.populateTree(FStage.newContentPane.getTreePanel());
                            newContentPane.populateTree(newContentPane.getTreePanel());
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
                okcand.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {


                        if(fs.getSelectedItem()!=null) {
                        if(jt.getValueAt(jt.getSelectedRow(), jt.getSelectedColumn())!=null){

                                DCouple dfc= (DCouple) jls.getSelectedValue() ;
                                DCreneau dc = (DCreneau) jt.getValueAt(jt.getSelectedRow(), jt.getSelectedColumn());

                                dc.getSout(dfc).setCdd((DProf)jcbcandide.getSelectedItem());
                                dc.getSout(dfc).setSalle((String)jcbsalles.getSelectedItem());
                              System.out.println("candide ok");


                                fs.removeAllElements();
                                jcbcandide.removeAllItems();
                                jcbsalles.removeAllItems();
                                info.setText("<html>Sélectionnez un stage pour afficher ses informations.</html>");
                                refresh();
                            }
                        }

                    }
                });
                 jls.addListSelectionListener(new ListSelectionListener() {
                     @Override
                     public void valueChanged(ListSelectionEvent e) {
                         jcbcandide.removeAllItems();
                         jcbsalles.removeAllItems();
                                              if(jt.getValueAt(jt.getSelectedRow(), jt.getSelectedColumn())!=null){
                                                  if(fs.getSelectedItem()!=null) {
                                                      DCouple dfc= (DCouple) jls.getSelectedValue() ;
                                                      DCreneau dc = (DCreneau) jt.getValueAt(jt.getSelectedRow(), jt.getSelectedColumn());
                                                      int i;
                                                      for(i=0;i<Datas.prof.size();i++){
                                                          if(dc.isProfIn(Datas.prof.getElementAt(i))){

                                                              jcbcandide.addItem(Datas.prof.getElementAt(i));


                                                          }




                                                      }
                                                      for(int k=0;k<Datas.salles.size();k++){

                                                          if(dc.isSalleIn(Datas.salles.getElementAt(k)))
                                                              jcbsalles.addItem(Datas.salles.getElementAt(k));
                                                      }

                                                      info.setText(setInfo(dc.getSout(dfc).getinfos()));

                                                  }
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
                                  }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        fs.removeAllElements();
                        DCreneau dc = (DCreneau) jt.getValueAt(jt.getSelectedRow(), jt.getSelectedColumn());
                        int m;
                        ArrayList<Soutenance> ars = dc.toStringtest();
                        for (m = 0; m < ars.size(); m++)
                            fs.addElement(ars.get(m).getCpl());



                        refresh();
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                                        }
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
        Dimension dim = new Dimension(1,1);
        // Marges
        pancreneau.setBorder(new EmptyBorder(5,5,5,5));
        panfin.setBorder(new EmptyBorder(5, 5, 5, 5));
        settingsPanel.setBorder(BorderFactory.createEtchedBorder());
        dateSettingsPanel.setBorder(new EmptyBorder(20,0,0,0));
        dateSettingsPanel.setOpaque(false);
        validPanel.setOpaque(false);
        validPanel.setBorder(new EmptyBorder(0,20,0,0));

        // Panel affichage selecteur dates
        dateChooserPanel.setLayout(new BorderLayout());
        dateChooserPanel.add(new JLabel("(1) Sélection des dates de soutenances :"), BorderLayout.NORTH);
        chooserDebut.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        dateChooserPanel.add(chooserDebut, BorderLayout.CENTER);
        //Panel paramètres
        settingsPanel.setLayout(new GridLayout(1,2));
        panfin.setLayout(new BoxLayout(panfin,BoxLayout.Y_AXIS));
        panfin.add(debutLabel);
        panfin.add(debutJour);
        panfin.add(finLabel);
        panfin.add(finJour);
        settingsPanel.add(panfin);
        pancreneau.setLayout(new BoxLayout(pancreneau,BoxLayout.Y_AXIS));
        pancreneau.add(creneauLabel);
        pancreneau.add(creneau);
        pancreneau.add(nbSoutenances);
        pancreneau.add(soutenance);
        settingsPanel.add(pancreneau);

        //Panel validation
        validPanel.setLayout(new GridLayout(2,1));
        validPanel.add(infoChooser);
        validPanel.add(okPlageJour);
        //Panel paramètres+validation des dates
        dateSettingsPanel.setLayout(new BorderLayout());
        dateSettingsPanel.add(settingsPanel,BorderLayout.CENTER);
        dateSettingsPanel.add(validPanel, BorderLayout.EAST);
        // Panel calendrier principal
        pan().setLayout(new GridLayout(2,0));
        pan().add(dateChooserPanel);
        pan().add(dateSettingsPanel);
    }
}
