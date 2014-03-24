package com.stcal.fen;

import com.stcal.control.Message;
import com.stcal.control.Settings;
import com.stcal.control.exceptions.NoSuchSettingException;
import com.stcal.control.exceptions.NothingToSaveException;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Jean
 * Date: 29/01/2014
 */
public class FSettings {

    Settings settings;

    public FSettings(Settings settings){
        this.settings = settings;
    }

    public void pop(){
        HashMap<String,JTextField> form;
        JPanel pan = new JPanel();
        pan.setLayout(new GridLayout(settings.getNbChangeable(),2));
        form = new HashMap<String, JTextField>();
        for(Map.Entry<String, String> entry : settings.getall().entrySet()) {
            JLabel label;
            try {
                label = new JLabel(settings.getDescOf(entry.getKey()));
            } catch (NoSuchSettingException e) {
                Message.err.println(e.getMessage());
                label = new JLabel(entry.getKey());
            }
            JTextField input = new JTextField(entry.getValue());
            form.put(entry.getKey(), input);
            pan.add(label);
            pan.add(input);
        }
        JOptionPane pop = new JOptionPane();
        Message.out.println("Ouverture paramètres " + settings.getFilename());
        pop.showMessageDialog(null, pan, "Paramètres", JOptionPane.PLAIN_MESSAGE);
        for(Map.Entry<String,JTextField> entry : form.entrySet()) {
            try {
                settings.set(entry.getKey(),entry.getValue().getText());
            } catch (NoSuchSettingException e) {
                Message.err.println(e);
                continue;
            }
        }
        try {
            settings.save();
        } catch (NothingToSaveException e) {
            Message.out.println(e.getMessage());
        }
    }

}
