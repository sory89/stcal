package com.stcal;

import com.stcal.control.CALsettings;
import com.stcal.control.DBsettings;
import com.stcal.control.OSplitCsv;
import com.stcal.control.Settings;
import com.stcal.control.exceptions.NoSettingFileException;
import com.stcal.control.exceptions.NothingToSaveException;
import com.stcal.control.exceptions.UncreatableSettingException;
import com.stcal.control.exceptions.UnopenableSettingException;
import com.stcal.don.*;
import com.stcal.fen.*;

import java.awt.*;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Main {

    public static ModelListCouple stages = new ModelListCouple();
    private static DListe etu = new DListe();
    private static  DListe prof = new DListe();
    private static FChooser finder = new FChooser();
    public static FInterface fen = new FInterface(800,600);
    private static FLier lier = new FLier();
    private static FStage stage = new FStage();
    private static FTab cal = new FCal();
    private static Settings dbsettings = new DBsettings();
    public static Settings calsettings= new CALsettings();
    public static String[][] colors=null;
    /**
     * Construit l'environement graphique de l'application
     * @param args
     */
    public static void main(String[] args) {
        fen.addTab(lier) ;
        fen.addTab(stage);
        fen.addTab(cal);
        fen.show();
        try {
            dbsettings.loadfile();
        } catch (NoSettingFileException e) {
            askdbsetting();
        } catch (UnopenableSettingException e) {
            fenStatut("Impossible de charger le fichier de config");
        }
    }

    public static void askdbsetting(){
        try {
            dbsettings.loadfile();
        }
        catch (UnopenableSettingException e) {
            fenStatut("Impossible de charger le fichier de config");
        } catch (NoSettingFileException e) {
            try {
                dbsettings.save();
            } catch (UncreatableSettingException e1) {
                fenStatut("Impossible de creer le fichier de config");
            } catch (NothingToSaveException e1) {}
        }
        dbsettings.ask();
    }

    /**
     * Exporte les couples étudiant prof dans un fichier CSV
     */
    public static void exporter(){
        finder.show();
        if (finder.status()==FChooser.CHOSEN){
            try {
                OSplitCsv.exportcsv(finder.path(), etu);
                System.out.println("Main: exporter");
            }
            catch (Exception x){
                System.err.println("Erreur écriture fichier");
            }
        }
    }

    /**
     *
     * @param etuPre prénom de l'etudiant
     * @param etuNom nom de l'étudiant
     * @param tutPre prénom du tuteur
     * @param tutNom prénom du tuteur
     * @return faux si le nom/prenom de l'etudiant/prof ne corresponde à personne
     */
    public static boolean lier(String etuPre, String etuNom, String tutPre, String tutNom){
        DPersonne setu =  etu.search(etuPre,etuNom);
        DPersonne stut =  prof.search(tutPre,tutNom);
        if (setu!=null && stut!=null){
            stages.add(new DCouple(setu,stut));
            stage.change();
            fenStatut("étudiant: " + etuPre + " " + etuNom + ", tuteur: " + tutPre + " " + tutNom + ", stage créé.");
            return true;
        }
        return false;
    }

    /**
     * Supprime le lien entre un étudiant et un prof
     * @return vrai si la delier se passe correctement
     */
    public static void delier(DPersonne etu){


                      etu.setLie(false);

                fenStatut(", étudiant: " + etu.getPrenom() + " " + etu.getNom() + ", stage supprimé.");



    }

    public static ArrayList<String> etuStage(String profPre,String profNom){
        ArrayList<String> etu = new ArrayList<String>();
        for (int i=0;i<stages.size();i++){
            if (stages.get(i).getTut().getPrenom()==profPre && stages.get(i).getTut().getNom()==profNom){
                etu.add(stages.get(i).getEtu().toString());
            }
        }
        return etu;
    }

    public static ArrayList<String> personneInfo(Type type,String pre,String nom){
        ArrayList<String> details = new ArrayList<String>();
        DListe all;
        if (type.equals(Type.ETUDIANT)){
            all = etu;
        }
        else if (type.equals(Type.TUTEUR)){
            all = prof;
        }
        else {
            fenStatut("Err: type de la personne inconnu.");
            System.err.println("Err: type de la personne inconnu.");
            return null;
        }
        DPersonne selected = all.search(pre,nom);
        if (selected==null){
            fenStatut("Err : personne non trouvée.");
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
        fenStatut("Info de " + pre + " " + nom + ".");
        return details;
    }

    /**
     * Lance la procedure d'ouverture de fichier
     * @param type type du contenu du fichier Main.TUTEUR/Main.ETUDIANT
     */
    public static void openFile(Type type){
        DListe nouveau;
        fenStatut("Navigateur de fichier.");
        finder.show();
        if (finder.status()==FChooser.CHOSEN){
            try {
                nouveau = OSplitCsv.splitcsv(finder.file(), type.toString());
                if (type.equals(Type.ETUDIANT)){
                    for (int i=0;i<nouveau.nbPersonne();i++){
                        lier.addEtu(nouveau.getPersonne(i).getPrenom(),nouveau.getPersonne(i).getNom());
                    }
                    etu.add(nouveau);
                    fenStatut(nouveau.nbPersonne() + " étudiants ajoutés de " + finder.path());
                }
                else if (type.equals(Type.TUTEUR)){
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
                fenStatut("Err: impossible d'ouvrir le fichier.");
                System.err.println("erreur ouverture fichier : " + ex.getMessage());
            }
        }
        else {
            fenStatut("Ouverture annulée.");
        }
        fen.refresh();
        lier.resetOption();
    }

    /**
     * Met un message en footer de la fenetre
     * @param text message a afficher
     */
    public static void fenStatut(String text){
        fen.setStatus(text);
        System.out.println(text);
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
