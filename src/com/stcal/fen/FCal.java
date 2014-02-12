package com.stcal.fen;

import com.stcal.Main;
import com.stcal.control.Datas;
import com.stcal.control.exceptions.NoSuchSettingException;
import com.stcal.control.exceptions.NothingToSaveException;
import com.stcal.control.exceptions.UncreatableSettingException;
import com.stcal.control.parserPeriod;
import datechooser.beans.DateChooserPanel;
import datechooser.events.SelectionChangedEvent;
import datechooser.events.SelectionChangedListener;
import datechooser.model.multiple.Period;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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


    protected JFormattedTextField creneau = null;
    protected JFormattedTextField soutenance = null;
    protected Iterator<Period> datechoisis=null;
    protected ArrayList<Calendar> recupDates = null;
    protected  parserPeriod PP = null;

    protected String titre[];
    protected Object mod[][];
    protected DefaultTableModel tableModel;
    protected JTable jTable;


    protected JButton okPlageJour = new JButton("Valider votre selection");
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
        okPlageJour.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                datechoisis = chooserDebut.getSelection().iterator();
                PP = new parserPeriod(datechoisis);
                recupDates = (ArrayList<Calendar>) PP.getDates();
                pan().removeAll();
                pan().setLayout(new GridLayout(0,recupDates.size()+1));

                int trololo = Integer.parseInt(finJour.getSelectedItem().toString())-Integer.parseInt(debutJour.getSelectedItem().toString()) ;
                trololo = (trololo*60)/Integer.parseInt(creneau.getText());
                final JList Fetu = new JList(etu);
                int j ;
                for(j=0;j< Datas.stages.size();j++){
                    etu.addElement(Datas.stages.get(j).getEtu());
                }
                titre=new String[recupDates.size()];
                mod=new Object[recupDates.size()][trololo];

                for(int i=0;i<recupDates.size();i++)
                    titre[i] = "" + recupDates.get(i).get(Calendar.DAY_OF_MONTH) + "/" + (recupDates.get(i).get(Calendar.MONTH) + 1) + "/" + recupDates.get(i).get(Calendar.YEAR) + "";
                tableModel=new DefaultTableModel(mod,titre);
                jTable=new JTable(tableModel);
                jTable.setRowHeight(60,60);
                jTable.setTransferHandler(new TransferHandler() {
                    public boolean canImport(TransferHandler.TransferSupport support) {
                        // for the demo, we'll only support drops (not clipboard paste)
                        if (!support.isDrop()) {
                            return false;
                        }

                        // we only import Strings
                        if (!support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                            return false;
                        }
                        return true;
                    }
                    public boolean importData(TransferSupport support) {
                        // if we can't handle the import, say so
                        if (!canImport(support)) {
                            return false;
                        }
                        // fetch the drop location
                        JTable.DropLocation dl = (JTable.DropLocation) support
                                .getDropLocation();
                        int row = dl.getRow();
                        // fetch the data and bail if this fails
                        String data;
                        try {
                            data = (String) support.getTransferable().getTransferData(
                                    DataFlavor.stringFlavor);
                        } catch (UnsupportedFlavorException e) {
                            return false;
                        } catch (IOException e) {
                            return false;
                        }
                        String[] rowData = data.split(",");
                        tableModel.insertRow(row, rowData);
                        Rectangle rect = jTable.getCellRect(row, 0, false);
                        if (rect != null) {
                            jTable.scrollRectToVisible(rect);
                        }
                        etu.removeAllElements();
                        return true;
                    }
                });
                Fetu.setFocusable(false);
                Fetu.setDragEnabled(true);
                Fetu.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent me) {
                        if (SwingUtilities.isLeftMouseButton(me) && me.getClickCount() % 2 == 0) {
                            String text = (String) etu.getElementAt(0);
                            String[] rowData = text.split(",");
                            tableModel.insertRow(jTable.getRowCount(), rowData);
                            etu.removeAllElements();
                        }
                    }
                });


                pan().setLayout(new BorderLayout());
                pan().add(new JScrollPane(jTable),BorderLayout.EAST);
                pan().add(Fetu,BorderLayout.CENTER);
                refresh();



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
