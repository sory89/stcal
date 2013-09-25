package com.stcal;

import java.io.*;
import java.util.ArrayList;

public class Fichier {

    protected String file = "";
    ArrayList <String> liste = new ArrayList<String>();

    public Fichier (String file) throws IOException {
        this.file = file;
        BufferedReader lecteurAvecBuffer = null;
        String ligne;
        try {
            lecteurAvecBuffer = new BufferedReader(new FileReader(file));
            while ((ligne = lecteurAvecBuffer.readLine()) != null) {
                liste.add(ligne);
            }
            lecteurAvecBuffer.close();
        }
        catch(FileNotFoundException exc) {
            System.out.println("Erreur d'ouverture");
        }
    }

    public ArrayList getCont(){
        return liste;
    }

}