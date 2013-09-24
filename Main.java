package com.stcal;

import java.lang.String;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        com.stcal.Liste etu = new com.stcal.Liste();
        com.stcal.Liste prof = new com.stcal.Liste();

        com.stcal.Fichier fileEtu = new com.stcal.Fichier("etu.csv");
        com.stcal.Fichier fileProf = new com.stcal.Fichier("prof.csv");

        ArrayList <String> csvEtu = fileEtu.getCont();
        ArrayList <String> csvProf = fileProf.getCont();

        for (String ligne: csvEtu) {
            String split[] = ligne.split(";");
            etu.addPersonne(new com.stcal.Etudiant(split[1], split[0], split[2], split[3]));
        }
        for (String ligne: csvProf) {
            String split[] = ligne.split(";");
            prof.addPersonne(new com.stcal.Prof(split[1],split[0],split[2]));
        }

        com.stcal.Fenetre win = new com.stcal.Fenetre(prof,etu);
        win.visible();
    }

}
