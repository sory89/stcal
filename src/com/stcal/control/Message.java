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
        Main.fenStatut("erreur: " + msg);
        JOptionPane pop = new JOptionPane();
        pop.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void popwarning(Exception e){
        popwarning(e.getMessage());
    }

    public static void popwarning(String msg){
        err.println("WARNING: " + msg);
        Main.fenStatut("warning: " + msg);
        JOptionPane pop = new JOptionPane();
        pop.showMessageDialog(null, msg, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    public static void popnotice(Exception e){
       popnotice(e.getMessage());
    }

    public static void popnotice(String msg){
        out.println("NOTICE:  " + msg);
        Main.fenStatut("notice: " + msg);
        JOptionPane pop = new JOptionPane();
        pop.showMessageDialog(null, msg, "Notice", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean popquestion(String msg){
        JOptionPane pop = new JOptionPane();
        boolean inter = (pop.showConfirmDialog(null,msg,"coucou",2) == 0);
        out.println(msg + "\treponse: " + inter);
        Main.fenStatut("ok");
        return inter;
    }

}
