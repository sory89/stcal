package com.stcal;

import java.lang.String;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        // CREATION DES LISTES PROF ET ETU
        com.stcal.Liste etu = new com.stcal.Liste();
        com.stcal.Liste prof = new com.stcal.Liste();

        // OUVERTURE DES FICHIERS
        com.stcal.Fichier fileEtu = new com.stcal.Fichier("etu.csv");
        com.stcal.Fichier fileProf = new com.stcal.Fichier("prof.csv");

        // RECUPERATION DU CONTENU DES FICHIER
        ArrayList <String> csvEtu = fileEtu.getCont();
        ArrayList <String> csvProf = fileProf.getCont();

        // CREATION DES PERSONNE A PARTIR DES DONNEES DES FICHIERS ET AJOUT A LA LISTE
        for (String ligne: csvEtu) {
            String split[] = ligne.split(";");
            etu.addPersonne(new com.stcal.Etudiant(split[0],split[1],split[2],split[3]));
        }
        for (String ligne: csvProf) {
            String split[] = ligne.split(";");
            prof.addPersonne(new com.stcal.Prof(split[0],split[1],split[2]));
        }


    }

}
