package com.stcal;

import java.lang.reflect.Method;
import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static final String ETU = "etu";
    public static final String PROF = "prof";
    public static final String NONE = "";

    private static ArrayList<DStage> stages = new ArrayList<DStage>();
    private static DListe etu = new DListe();
    private static DListe prof = new DListe();
    private static FChooser finder = new FChooser();
    private static FInterface fen = new FInterface(800,600);
    private static FLier lier = new FLier();
    private static FStage stage = new FStage();

    public static void main(String[] args) {
        fen.addTab(lier);
        fen.addTab(stage);
        fen.show();
    }

    public static void exporter(){
        finder.show();
        if (finder.status()==FChooser.CHOSEN){
            try {
                OSplitCsv.exportcsv(finder.path(),etu);
                System.out.println("Main: exoprter");
            }
            catch (Exception x){
                System.err.println("erreur ecriture fichier");
            }
        }
    }

    public static boolean lier(String etuPre, String etuNom, String tutPre, String tutNom, String candPre, String candNom){
        DPersonne setu = etu.search(etuPre,etuNom);
        DPersonne stut = prof.search(tutPre,tutNom);
        DPersonne scand = prof.search(candPre,candNom);
        if (setu!=null && stut!=null && scand!=null && !stut.equals(scand)){
            stages.add(new DStage(setu,stut,scand));
            fenStatut("étudiant: " + etuPre + " " + etuNom + ", tuteur: " + tutPre + " " + tutNom + ", candide:" + candPre + " " + candNom + ", stage crée.");
            return true;
        }
        return false;
    }

    public static void personneInfo(String type,String pre,String nom){
        ArrayList<String> details = new ArrayList<String>();
        DListe all;
        if (type.equals(ETU)){
            all = etu;
        }
        else if (type.equals(PROF)){
            all = prof;
        }
        else {
            fenStatut("Err: type de la personne inconnu.");
            System.err.println("Err: type de la personne inconnu.");
            return;
        }
        DPersonne selected = all.search(pre,nom);
        if (selected==null){
            fenStatut("Err: personne non trouvé.");
            System.err.println("Err: personne non trouvé.");
            return;
        }
        for (int i=0;i<all.getLabels().size();i++){
            details.add(" " + all.getLabels().get(i) + ": " + selected.getInfo().get(i));
        }
        lier.setInfo(details);
        fenStatut("Info de " + pre + " " + nom + ".");
        fen.refresh();
    }

    public static void openFile(String type){
        DListe nouveau;
        fenStatut("Navigateur de fichier.");
        finder.show();
        if (finder.status()==FChooser.CHOSEN){
            try {
                nouveau = OSplitCsv.splitcsv(finder.file());
                if (type.equals(ETU)){
                    for (int i=0;i<nouveau.nbPersonne();i++){
                        lier.addEtu(nouveau.getPersonne(i).getPrenom(),nouveau.getPersonne(i).getNom());
                    }
                    etu.add(nouveau);
                    fenStatut(nouveau.nbPersonne() + " étudiants ajoutés de " + finder.path());
                }
                else if (type.equals(PROF)){
                    for (int i=0;i<nouveau.nbPersonne();i++){
                        lier.addProf(nouveau.getPersonne(i).getPrenom(),nouveau.getPersonne(i).getNom());
                    }
                    prof.add(nouveau);
                    fenStatut(nouveau.nbPersonne() + " enseignants ajoutés de" + finder.path());
                }
                else {
                    System.err.println("Main: openFile: type not defined");
                    return;
                }
            }
            catch (Exception ex){
                fenStatut("Err: impossible d'ouvrire le fichier.");
                System.err.println("erreur ouverture fichier : " + ex.getMessage());
            }
        }
        else {
            fenStatut("Ouverture annulé.");
        }
        fen.refresh();
        lier.resetOption();
    }

    public static void fenStatut(String text){
        fen.setStatus(text);
    }

    public static void mac(Window window){
        if (System.getProperty("os.name").contains("Mac")) {
            String className = "com.apple.eawt.FullScreenUtilities";
            String methodName = "setWindowCanFullScreen";
            try {
                System.setProperty("apple.laf.useScreenMenuBar","true");
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

}