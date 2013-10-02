package com.stcal;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;

public class Fenetre extends JFrame {

    /** Construit une fenetre vide */
    public Fenetre (){
        this.setTitle("StCal");
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // new JPanneau(prof,etu)
        if (System.getProperty("os.name").contains("Mac")) {
            mac(this);
        }
        JMenuBar jmb = defineMenuBar();
        this.setContentPane(new JPanneau());
        this.setJMenuBar(jmb);
        //this.setLayout(new FlowLayout());

    }

    private JMenuBar defineMenuBar() {
        JMenuBar jmb = new JMenuBar();
        JMenu file = new JMenu("File");
        jmb.add(file);
        JMenuItem open = new JMenuItem("open file...");
        open.addActionListener(new Open(this, this.getContentPane()));
        file.add(open);
        JMenuItem exit = new JMenuItem("quit");
        exit.addActionListener(new Exit());
        file.add(exit);
        return jmb;
    }

    /** rend la fentre compatible mac */
    private static void mac(Window window){
        String className = "com.apple.eawt.FullScreenUtilities";
        String methodName = "setWindowCanFullScreen";
        try {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name","StCal");
            Class<?> clazz = Class.forName(className);
            Method method = clazz.getMethod(methodName, new Class<?>[] {Window.class, boolean.class });
            method.invoke(null, window, true);
        }
        catch (Throwable t) {
            System.err.println("Not compatible OSX");
            t.printStackTrace();
        }
    }

}
