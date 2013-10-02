package com.stcal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exit implements ActionListener {

    public Exit(){};

    public void actionPerformed(ActionEvent e) {
        System.out.print("bye :)");
        System.exit(0);
    }
}