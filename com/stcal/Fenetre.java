package com.stcal;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;

public class Fenetre extends JFrame {

    /** Construit une fenetre vide */
    public Fenetre (com.stcal.Liste etu, com.stcal.Liste prof){
        this.setTitle("StCal");
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new com.stcal.Panneau(prof,etu));
        if (System.getProperty("os.name").contains("Mac")) {
            mac(this);
        }
        JMenuBar jmb = new JMenuBar();
        this.setJMenuBar(jmb);
        JMenu file = new JMenu("File");
        jmb.add(file);
    }

    /** rend la fenetre visible */
    public void visible() {
        this.setVisible(true);
    }

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
