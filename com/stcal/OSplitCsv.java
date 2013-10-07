package com.stcal;

import java.io.File;
import java.io.IOException;
import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class OSplitCsv {

    public static final String SEP = ";";

    public static DListe splitcsv(File file) throws IOException {
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

}
