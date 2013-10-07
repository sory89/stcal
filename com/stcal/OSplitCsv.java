package com.stcal;

import java.io.File;
import java.io.PrintWriter;
import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class OSplitCsv {

    public static final String SEP = ";";

    public static DListe splitcsv(File file) throws Exception {
        DListe cont;
        cont = new DListe();
        Scanner sc = new Scanner(file);
        String ligne;
        if (sc.hasNextLine()){
            System.out.println("OSplitCsv: splitcsv");
            ligne=sc.nextLine();
            String split[] = ligne.split(SEP);
            ArrayList <String> test = new ArrayList<String>(Arrays.asList(split));
            cont.setLabels(test);
        }
        while (sc.hasNextLine()) {
            ligne=sc.nextLine();
            String split[] = ligne.split(SEP);
            DPersonne perso = new DPersonne();
            for (int i=0;i<split.length;i++){
                perso.setInfo(split[i]);
            }
            cont.add(perso);
        }
        sc.close();
        return cont;
    }

    public static void exportcsv(String path,DListe etu) throws Exception {
        String ligne;
        PrintWriter writer = new PrintWriter(path, "UTF-8");
        for (int i=0;i<etu.nbPersonne();i++){
            if (etu.getPersonne(i).getLinked()!=null){
                ligne = etu.getPersonne(i).getLinked().getPrenom() + ";";
                ligne += etu.getPersonne(i).getLinked().getNom() + ";";
                for (int j=0;j<etu.getPersonne(i).getInfo().size();j++){
                    ligne += etu.getPersonne(i).getInfo().get(j) + ";";
                }
                writer.println(ligne);
            }
        }
        writer.close();
    }

}
