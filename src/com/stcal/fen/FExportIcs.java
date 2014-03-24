package com.stcal.fen;

import com.stcal.control.Datas;
import com.stcal.control.Message;
import com.stcal.control.Outics;
import net.fortuna.ical4j.model.ValidationException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Mehdi
 * Date: 23/03/14
 * Time: 14:49
 * To change this template use File | Settings | File Templates.
 */
public class FExportIcs {


    JPanel jfics ;
    JLabel laboptions = new JLabel("Selectionez une option");

    JComboBox jcboptions ;
    JLabel  laboptions2 ;
    JComboBox jcboptions2 ;
    JButton okexp ;



    public FExportIcs(){

     jfics = new JPanel();
        laboptions = new JLabel("Selectionez une option");
        String[] tab =  {"Salles","Professeurs","Total"} ;
         jcboptions = new JComboBox(tab);
         laboptions2 = new JLabel("Format");
       jcboptions2 = new JComboBox();
        okexp = new JButton("Exporter");



    }

    public void pop(){


        jfics.setLayout(new GridLayout(5,0));
        jfics.add(laboptions);
        jfics.add(jcboptions);
        jfics.add(laboptions2);
        jfics.add(jcboptions2);
        jfics.add(okexp);

        jcboptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jcboptions2.removeAllItems();
                switch (jcboptions.getSelectedIndex()){

                    case 1:
                        for(int i=0;i< Datas.prof.size();i++){
                            jcboptions2.addItem(Datas.prof.getElementAt(i)); }
                        break;
                    case 0:
                        for(int i=0;i< Datas.salles.size();i++)   {
                            jcboptions2.addItem(Datas.salles.getElementAt(i));  }
                        break;
                    case 2:
                        jcboptions2.addItem("Pdf");
                        jcboptions2.addItem("Ics");
                        break;
                    default:
                        break;




                }
                jfics.revalidate();
                jfics.repaint();
            }
        });
        okexp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (jcboptions.getSelectedIndex()){

                    case 1:
                        Outics test = new Outics(1,jcboptions2.getSelectedItem());
                        try {
                            System.out.println("start export");
                            test.export();

                        } catch (IOException e1) {
                            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        } catch (ValidationException e1) {
                            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                     break;
                    case 2:
                        switch (jcboptions2.getSelectedIndex())  {

                            case 0 :
                        Outics test1 = new Outics(20);
                        try {
                            System.out.println("start export pdf");
                            test1.export();

                        } catch (IOException e1) {
                            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        } catch (ValidationException e1) {
                            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                        break;
                            case 1:
                                Outics test2 = new Outics(21);
                                try {
                                    System.out.println("start export ics");
                                    test2.export();

                                } catch (IOException e1) {
                                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                                } catch (ValidationException e1) {
                                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                                }

                                break;
                        }
                        break;
                    case 0:
                        Outics testa = new Outics(0,jcboptions2.getSelectedItem());
                        try {
                            System.out.println("start export");
                            testa.export();

                        } catch (IOException e1) {
                            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        } catch (ValidationException e1) {
                            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }

                        break;
                    default:
                        break;

                }


            }
        });


        JOptionPane pop = new JOptionPane();
        Message.out.println("Ouverture export ics ");
        pop.showMessageDialog(null, jfics, "Export", JOptionPane.PLAIN_MESSAGE);



    }



}
