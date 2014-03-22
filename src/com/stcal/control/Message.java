package com.stcal.control;

import com.stcal.Main;

import javax.swing.*;
import java.io.PrintStream;

/**
 * @author Jean
 * @version 10/03/2014
 *
 * Cette classe redefinie toute les sortie pour aller dans les logs ou dans les popup
 */

public class Message {

    public static final PrintStream out;
    public static final PrintStream err;

    static {
        out = System.out;
        err = System.err;
    }

    public static void poperror(Exception e){
        poperror(e.getMessage());
    }

    public static void poperror(String msg){
        err.println("ERROR:   " + msg);
        Main.fen.setStatus("erreur: " + msg);
        JOptionPane pop = new JOptionPane();
        pop.showMessageDialog(null, msg, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    public static void popwarning(Exception e){
        popwarning(e.getMessage());
    }

    public static void popwarning(String msg){
        err.println("WARNING: " + msg);
        Main.fen.setStatus("warning: " + msg);
        JOptionPane pop = new JOptionPane();
        pop.showMessageDialog(null, msg, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    public static void popnotice(Exception e){
       popnotice(e.getMessage(),"Exception");
    }

    public static void popnotice(String msg){
        popnotice(msg,"Notice");
    }

    public static void popnotice(String msg,String title){
        out.println("NOTICE:  " + msg);
        Main.fen.setStatus("notice: " + msg);
        JOptionPane pop = new JOptionPane();
        pop.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean popquestion(String msg){
        JOptionPane pop = new JOptionPane();
        boolean inter = (pop.showConfirmDialog(null,msg,"coucou",2) == 0);
        out.println(msg + "\treponse: " + inter);
        status("ok");
        return inter;
    }

    /**
     * Met un message en footer de la fenetre
     * @param text message a afficher
     */
    public static void status(String text){
        Main.fen.setStatus(text);
        out.println("STATUS:  " + text);
    }
}
