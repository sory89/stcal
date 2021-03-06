package com.stcal;

import com.stcal.control.*;
import com.stcal.don.DListe;
import com.stcal.don.DPersonne;
import com.stcal.don.Type;
import com.stcal.fen.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;

public class Stcal {

    private static FChooser finder;
    private static FStage stage;
    private static FTab cal;

    public static FLier lier;

    public static final FInterface fen;
    public static final Settings calsettings;
    public static       String[][] colors;

    static {
        finder      = new FChooser();
        lier        = new FLier();
        stage       = new FStage();
        cal         = new FCal();
        fen         = new FInterface(800,600);
        calsettings = new CALsettings();
        colors      = null;
    }

    /**
     * Construit l'environement graphique de l'application
     * @param args
     */
    public static void main(String[] args) {
        Message.out.println("\n\n\n\n\n" + new Date().toString() + " Start up");
        DBTools.startup();
        fen.addTab(lier) ;
        fen.addTab(stage);
        fen.addTab(cal);
        fen.show();
    }

    /**
     * Exporte les couples étudiant prof dans un fichier CSV
     */
    public static void exporter(){
        finder.show();
        if (finder.status()==FChooser.CHOSEN){
            try {
                OSplitCsv.exportcsv(finder.path(), Datas.etu);
                Message.out.println("Stcal : exporter");
            }
            catch (Exception x){
                Message.err.println("Erreur écriture fichier");
            }
        }
    }

    public static ArrayList<String> personneInfo(Type type,String pre,String nom){
        ArrayList<String> details = new ArrayList<String>();
        DefaultListModel<DPersonne> all;
        if (type.equals(Type.ETUDIANT)){
            all = Datas.etu;
        }
        else if (type.equals(Type.TUTEUR)){
            all = Datas.prof;
        }
        else {
            Message.status("Err: Type de la personne inconnu.");
            Message.err.println("Err: Type de la personne inconnu.");
            return null;
        }
        /*
        DPersonne selected = all.search(pre,nom);
        if (selected==null){
            status("Err : personne non trouvée.");
            System.err.println("Err : personne non trouvée.");
            return null;
        }
        for (int i=0;i<all.getLabels().size();i++){
            if(i==0)
                details.add(" " + all.getLabels().get(i) + ": " + selected.getNom());
            else if(i==1)
                details.add(" " + all.getLabels().get(i) + ": " + selected.getPrenom());
            else
            details.add(" " + all.getLabels().get(i) + ": " + selected.getInfos().get(i-2));
        }
        status("Info de " + pre + " " + nom + ".");
        */
        return details;
    }

    /**
     * Lance la procedure d'ouverture de fichier
     * @param type type du contenu du fichier Stcal.TUTEUR/Stcal.ETUDIANT
     */
    public static void openFile(Type type){
        DListe nouveau;
        Message.status("Navigateur de fichier.");
        finder.show();
        if (finder.status()==FChooser.CHOSEN){
            try {
                // TODO fix bug here
                nouveau = OSplitCsv.splitcsv(finder.file(), type.toString());
                if (type.equals(Type.ETUDIANT)){
                    for (int i=0; i<nouveau.getSize();i++){
                        Datas.etu.addElement(nouveau.getElementAt(i));
                    }
                    Message.status(nouveau.getSize() + " étudiants ajoutés de " + finder.path());
                }
                else if (type.equals(Type.TUTEUR)){
                    for (int i=0; i<nouveau.getSize();i++){
                        Datas.prof.addElement(nouveau.getElementAt(i));
                    }
                    Message.status(nouveau.getSize() + " enseignants ajoutés de" + finder.path());
                }
                else {
                    Message.err.println("Stcal: openFile: type not defined");
                    return;
                }
            }
            catch (Exception ex){
                Message.status("Err: Impossible d'ouvrir le fichier.");
                Message.err.println("Erreur ouverture fichier : " + ex.getMessage());
            }
        }
        else {
            Message.status("Ouverture annulée.");
        }
        fen.refresh();
        lier.resetOption();
    }

    /**
     * Ajout les propriété propre au mac dans la fenetre
     * @param window fenetre JFrame
     */
    public static void mac(Window window){
        if (System.getProperty("os.name").contains("Mac")) {
            String className = "com.apple.eawt.FullScreenUtilities";
            String methodName = "setWindowCanFullScreen";
            try {
                ImageIcon img = new ImageIcon("res/stcal-icon.png");
                //com.apple.eawt.Application.getApplication().setDockIconImage(img.getImage());
                System.setProperty("apple.laf.useScreenMenuBar","true");
                System.setProperty("com.apple.mrj.application.apple.menu.about.name","StCal");
                Class<?> clazz = Class.forName(className);
                Method method = clazz.getMethod(methodName, new Class<?>[] {Window.class, boolean.class });
                method.invoke(null, window, true);
            }
            catch (Throwable t) {
                Message.poperror("Not compatible OSX: " + t.getMessage());
            }
        }
    }

}
