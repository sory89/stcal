package com.stcal.fen;

import com.stcal.control.Datas;
import com.stcal.control.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Zamal
 * Date: 23/03/14
 * Time: 23:53
 * To change this template use File | Settings | File Templates.
 */
public class FCsv {

    private JPanel pan;
    private JButton swtch;
    private JLabel sep;

    public FCsv(){
        pan = new JPanel();
        swtch= new JButton("Changer");
        sep= new JLabel("Séparateur actuel :        "+Datas.Csv);
    }

    public void pop(){
        pan.setLayout(new BorderLayout());
        pan.add(sep, BorderLayout.NORTH);
        pan.add(new JLabel("  "), BorderLayout.CENTER);
        pan.add(swtch, BorderLayout.SOUTH);

        swtch.setToolTipText("Appuyer pour changer de séparateur pour l'import de fichiers CSV. ");
        swtch.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (Datas.Csv==';') {
                    Datas.Csv=',';
                } else {
                    Datas.Csv=';';
                }
                sep.setText("Séparateur actuel :        "+Datas.Csv);

            }
        });
        JOptionPane pop = new JOptionPane();
        Message.out.println("Ouverture paramètres CSV ");
        pop.showMessageDialog(null, pan, "Paramètres CSV", JOptionPane.PLAIN_MESSAGE);

    }
}
