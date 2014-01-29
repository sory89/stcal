package com.stcal.fen;

import com.stcal.Settings;

import javax.swing.*;

/**
 * User: Jean
 * Date: 29/01/2014
 */
public class FSettings {

    protected JFrame fen;
    protected Settings settings;

    public FSettings(Settings settings){
        this.settings = settings;
        fen = new JFrame();
        fen.setTitle("Paramettres");
        fen.setSize(300, 200);
        fen.setLocationRelativeTo(null);
    }

}
