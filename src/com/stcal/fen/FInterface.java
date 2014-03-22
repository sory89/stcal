package com.stcal.fen;

import com.stcal.Main;
import com.stcal.control.Message;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class FInterface {

    protected JFrame fen =  new JFrame();
    protected JLabel status = new JLabel("Idle");
    protected JTabbedPane tabs = new JTabbedPane();

    public FInterface(int x,int y){
        ImageIcon img = new ImageIcon("res/stcal-icon.png");
        fen.setIconImage(img.getImage());
        Main.mac(fen);
        fen.setSize(x, y);
        fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fen.setLocationRelativeTo(null);
        fen.setTitle("STCal");
        FMenu menubar = new FMenu();
        fen.setJMenuBar(menubar.getMenubar());
        tabs.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Message.status("Changement d'onglet");
            }
        });
        fen.add(tabs);
        JPanel statusPanel = new JPanel();
        statusPanel.setOpaque(false);
        fen.add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setPreferredSize(new Dimension(fen.getWidth(), 16));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        Font newLabelFont=new Font(status.getFont().getName(),Font.ITALIC,status.getFont().getSize());
        status.setFont(newLabelFont);
        status.setHorizontalAlignment(SwingConstants.LEFT);
        statusPanel.add(status);
    }

    public void addTab(FTab tab){
        tabs.addTab(tab.getNom(),tab.pan());
    }

    public void setStatus(String text){
        status.setText(text);
        refresh();
    }

    public void show(){
        fen.setVisible(true);
    }

    public void hide(){
        fen.setVisible(false);
    }

    public void refresh(){
        fen.getContentPane().revalidate();
        fen.getContentPane().repaint();
    }

}
