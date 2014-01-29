package com.stcal.fen;

import com.stcal.Main;
import com.stcal.Settings;
import com.stcal.exceptions.NoSuchSettingException;
import com.stcal.exceptions.UncreatableSettingException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Jean
 * Date: 29/01/2014
 */
public class FSettings {

    protected JFrame fen;
    protected Settings settings;
    protected HashMap<String,JTextField> form;

    public FSettings(Settings settings){
        this.settings = settings;
        fen = new JFrame();
        fen.setTitle("Parametres");
        fen.setSize(300, 200);
        fen.setLocationRelativeTo(null);
        JPanel pan = new JPanel();
        pan.setLayout(new GridLayout(settings.getNbChangeable()+1,2));
        fen.add(pan);
        form = new HashMap<String, JTextField>();
        for(Map.Entry<String, String> entry : settings.getall().entrySet()) {
            JLabel label = new JLabel(entry.getKey());
            JTextField input = new JTextField(entry.getValue());
            form.put(entry.getKey(),input);
            pan.add(label);
            pan.add(input);
        }
        JButton ok = new JButton("Valider");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    submit();
                    fen.setVisible(false);
                } catch (UncreatableSettingException e1) {
                    Main.fenStatut("Impossible de creer le fichier de configuration");
                }
            }
        });
        pan.add(ok);
        fen.setVisible(true);
    }

    public void submit() throws UncreatableSettingException {
        for(Map.Entry<String,JTextField> entry : form.entrySet()) {
            try {
                settings.set(entry.getKey(),entry.getValue().getText());
            } catch (NoSuchSettingException e) {
                continue;
            }
        }
        settings.save();
    }

}
