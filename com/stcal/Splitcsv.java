package com;

import java.io.File;
import java.io.IOException;
import java.lang.String;
import java.util.Scanner;

public class Splitcsv{

public static Liste splitcsvprof(String file) throws IOException, ClassNotFoundException {

            Liste<Prof> cont;
            cont = new Liste<Prof>();
            Scanner sc =new Scanner(new File(file));
            String ligne;


            while (sc.hasNextLine()) {
                ligne=sc.nextLine();
                String split[] = ligne.split(";");
                cont.add(new Prof(split[1], split[0], split[2]));

            }
            sc.close();

               return cont;
        }
    public static Liste splitcsvetu(String file) throws IOException, ClassNotFoundException {

        Liste<Etudiant> cont;
        cont = new Liste<Etudiant>();
        Scanner sc =new Scanner(new File(file));
        String ligne;


        while (sc.hasNextLine()) {
            ligne=sc.nextLine();
            String split[] = ligne.split(";");
            cont.add(new Etudiant(split[1], split[0], split[2],split[3]));

        }
        sc.close();

        return cont;
    }


}
