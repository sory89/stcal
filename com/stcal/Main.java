package com.stcal;

import java.lang.reflect.Method;
import java.awt.*;

public class Main {

    public static final String ETU = "etu";
    public static final String PROF = "prof";

    private static FHome home = new FHome();
    private static FChooser finder = new FChooser();
    private static DListe etu = new DListe();
    private static DListe prof = new DListe();
    private static FListe etuWin = new FListe("Etudiant");
    private static FListe profWin = new FListe("Enseignants");
    private static FResult result = new FResult();

    public static void main(String[] args) {
        home.show();
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

    public static void lier(){
        if (!etuWin.getSelectedPre().equals("") && !etuWin.getSelectedNom().equals("") && !profWin.getSelectedPre().equals("") && !profWin.getSelectedNom().matches("")){
            result.add(etuWin.getSelectedPre() + " " + etuWin.getSelectedNom(),profWin.getSelectedPre() + " " + profWin.getSelectedNom());
            result.show();
            DPersonne selectedProf = prof.search(profWin.getSelectedPre(),profWin.getSelectedNom());
            DPersonne selectedEtu = etu.search(etuWin.getSelectedPre(),etuWin.getSelectedNom());
            if (selectedEtu!=null && selectedProf!=null){
                selectedEtu.setLinked(selectedProf);
            }
        }
    }

    public static void personneInfo(String pre,String nom){
        DPersonne selected = etu.search(pre,nom);
        FListe current = etuWin;
        DListe all = etu;
        if (selected==null){
            selected = prof.search(pre,nom);
            if (selected==null){
                System.err.println("Main: personneInfo: info personne not found");
                return;
            }
            current = profWin;
            all = prof;
        }
        System.out.println("Main: info " + pre + " " + nom);
        current.resetInfo();
        for (int i=0;i<all.getLabels().size();i++){
            current.addInfo(" " + all.getLabels().get(i) + ": " + selected.getInfo().get(i));
        }
        current.showInfo();
    }

    public static void openFile(String type){
        DListe nouveau;
        System.out.println("Main: openFile " + type);
        finder.show();
        if (finder.status()==FChooser.CHOSEN){
            try {
                nouveau = OSplitCsv.splitcsv(finder.file());
                if (type.equals(ETU)){
                    for (int i=0;i<nouveau.nbPersonne();i++){
                        etuWin.add(nouveau.getPersonne(i).getPrenom(),nouveau.getPersonne(i).getNom());
                    }
                    etu.add(nouveau);
                    etuWin.show();
                }
                else if (type.equals(PROF)){
                    for (int i=0;i<nouveau.nbPersonne();i++){
                        profWin.add(nouveau.getPersonne(i).getPrenom(),nouveau.getPersonne(i).getNom());
                    }
                    prof.add(nouveau);
                    profWin.show();
                }
                else {
                    System.err.println("Main: openFile: type not defined");
                    return;
                }
            }
            catch (Exception ex){
                System.err.println("erreur ouverture fichier");
            }
        }
        else {
            home.show();
        }
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