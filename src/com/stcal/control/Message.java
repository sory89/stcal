package com.stcal.control;

import com.stcal.Stcal;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    public static final String logfile;

    static {

        logfile = System.getProperty("user.home") + System.getProperty("file.separator") + ".stcal" + System.getProperty("file.separator") + "stcal.log";

        PrintStream inter = customLOG();
        out = inter;
        err = inter;

        // Decomenter ces lignes pour utiliser la sortie standar
        // out = System.out;
        // err = System.err;
    }

    private static PrintStream customLOG(){
        File log = new File(logfile);
        if (!log.exists()){
            log.getParentFile().mkdir();
            try {
                log.createNewFile();
            } catch (IOException e) {
                System.err.println("Erreure à la creation du journal.");
                return System.out;
            }
        }
        PrintStream out;
        try {
            out = new PrintStream(log);
        } catch (FileNotFoundException e) {
            System.err.println("Erreure à l'ouverture du journal.");
            return System.out;
        }
        return out;
    }

    public static void poperror(Exception e){
        poperror(e.getMessage());
    }

    public static void poperror(String msg){
        err.println("ERROR:   " + msg);
        Stcal.fen.setStatus("erreur: " + msg);
        JOptionPane pop = new JOptionPane();
        pop.showMessageDialog(null, retourligne(msg), "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    public static void popwarning(Exception e){
        popwarning(e.getMessage());
    }

    public static void popwarning(String msg){
        err.println("WARNING: " + msg);
        Stcal.fen.setStatus("warning: " + msg);
        JOptionPane pop = new JOptionPane();
        pop.showMessageDialog(null, retourligne(msg), "Warning", JOptionPane.WARNING_MESSAGE);
    }

    public static void popnotice(Exception e){
       popnotice(e.getMessage(),"Exception");
    }

    public static void popnotice(String msg){
        popnotice(msg,"Notice");
    }

    public static void popnotice(String msg,String title){
        out.println("NOTICE:  " + msg);
        Stcal.fen.setStatus("notice: " + msg);
        JOptionPane pop = new JOptionPane();
        pop.showMessageDialog(null, retourligne(msg), title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean popquestion(String msg){
        JOptionPane pop = new JOptionPane();
        boolean inter = (pop.showConfirmDialog(null,retourligne(msg),"coucou",2) == 0);
        out.println(msg + "\treponse: " + inter);
        status("ok");
        return inter;
    }

    /**
     * Met un message en footer de la fenetre
     * @param text message a afficher
     */
    public static void status(String text){
        Stcal.fen.setStatus(text);
        out.println("STATUT:  " + text);
    }

    private static String retourligne(String original){
        StringBuilder sb = new StringBuilder(original);
        int i = 0;
        while ((i = sb.indexOf(" ", i + 60)) != -1) {
            sb.replace(i, i + 1, "\n");
        }
        return sb.toString();
    }
}
