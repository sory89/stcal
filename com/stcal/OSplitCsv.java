package com.stcal;

import com.stcal.don.DListe;
import com.stcal.don.DPersonne;

import java.io.File;
import java.io.PrintWriter;
import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class OSplitCsv {

    public static final String SEPs = ";";
    public static final char SEP = ';';


    public static DListe splitcsv(File file) throws Exception {
        DListe cont;
        cont = new DListe();
        Scanner sc = new Scanner(file);
        String ligne;
        char cp=' ';    int nlab=1;
        boolean er=false;
        if (sc.hasNextLine()){
            System.out.println("OSplitCsv: splitcsv");
            ligne=sc.nextLine();
            int tligne=ligne.length();


            for(int i=0;i<tligne;i++){
                if(ligne.charAt(i)==SEP){
                    if(i==0) er=true;
                    if(cp!=SEP){
                        nlab+=1;
                        cp=SEP;
                    } else {
                        er=true;
                    }
                } else {
                    cp=ligne.charAt(i);
                }
            }


            if(er==false) {
            System.out.println(nlab);
            String split[] = ligne.split(SEPs);
            ArrayList <String> test = new ArrayList<String>(Arrays.asList(split));
            cont.setLabels(test); } else {
            System.out.println("ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR");
        }
        }






        while (sc.hasNextLine()) {
            int ninf=1;
            cp=' ';
            ligne=sc.nextLine();
            int tligne=ligne.length();
            ninf=1;
            for(int i=0;i<tligne;i++){
                if(ligne.charAt(i)==SEP){
                    if(i==0) er=true;
                    if(cp!=SEP){
                        ninf+=1;
                        cp=SEP;
                    } else {
                        er=true;
                    }
                } else {
                    cp=ligne.charAt(i);
                }
            }

            if (er==false && nlab==ninf) {
            String split[] = ligne.split(SEPs);
            DPersonne perso = new DPersonne();
            for (int i=0;i<split.length;i++){
                perso.setInfo(split[i]);
            }
                cont.add(perso);
            } else {
                System.out.println("ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR");
            }
        }
        sc.close();
        return cont;
    }

    public static void exportcsv(String path,DListe etu) throws Exception {
        String ligne;
        PrintWriter writer = new PrintWriter(path, "UTF-8");
        for (int i=0;i<etu.nbPersonne();i++){
            if (etu.getPersonne(i)!=null){
                ligne = etu.getPersonne(i).getPrenom() + ";";
                ligne += etu.getPersonne(i).getNom() + ";";
                for (int j=0;j<etu.getPersonne(i).getInfo().size();j++){
                    ligne += etu.getPersonne(i).getInfo().get(j) + ";";
                }
                writer.println(ligne);
            }
        }
        writer.close();
    }

}
