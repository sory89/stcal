package com.stcal.control;

import com.stcal.don.*;
import javax.swing.*;

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
        String ligne,errf="Les erreurs suivantes ont été detectées dans le fichier CSV : \n",warf="";
        char cp=' ';
        int nlab=1,nlig=1,nligb=0,nligv=0;
        boolean er=false,wr=false;
        DListe cont;
        cont = new DListe();
        Scanner sc = new Scanner(file);
        if (sc.hasNextLine()){
            System.out.println("OSplitCsv: splitcsv");
            ligne=sc.nextLine();
            int tligne=ligne.length();
            for(int i=0;i<tligne;i++){
                if(ligne.charAt(i)==SEP){
                    if(i==0) {
                        errf=errf+"\n- La ligne des libellés commence par un \";\"";
                        er=true;
                    }
                    if(cp!=SEP){
                        nlab+=1;
                        cp=SEP;
                    } else {
                        errf=errf+"\n- La ligne des libellés contient une entrée vide";
                        er=true;
                    }
                } else {
                    cp=ligne.charAt(i);
                }
            }
            if(!er) {
                System.out.println(nlab);
                String split[] = ligne.split(SEPs);
                ArrayList <String> test = new ArrayList<String>(Arrays.asList(split));
                cont.setLabels(test);
               } else { }

        }






        while (sc.hasNextLine()) {
            int ninf=1;
            cp=' ';
            ligne=sc.nextLine();
            int tligne=ligne.length();
            for(int i=0;i<tligne;i++){
                if(ligne.charAt(i)==SEP){
                    if(i==0) {
                        errf=errf+"\n- La ligne n°" + nlig + " du fichier commence par un \";\"";
                        er=true;
                    }
                    if(cp!=SEP){
                        ninf+=1;
                        cp=SEP;
                    } else {
                        ninf+=1;
                        nligv+=1;
                        warf=nligv+" entrée(s) vide(s) ont été detectées";
                        wr=true;
                    }
                } else {
                    cp=ligne.charAt(i);
                }
            }
            if (nlab!=ninf) {
                nligb+=1;
                er=true;

            }

            if (!er) {
            String split[] = ligne.split(SEPs);
            Personne perso = new Personne(split[0],split[1]);
            ArrayList<String> essai = new ArrayList<String>();


            for (int i=2;i<split.length;i++){

                essai.add(split[i]);
            }
                perso.setInfos(essai);
                cont.add(perso);

            } else {  }

            nlig+=1;
        }

        JOptionPane msg;
        if (nligb!=0) {
            errf=errf + "\n- Il y a "+nligb+"/"+nlig+" lignes ne correspondant pas à la ligne de libellés";
        }
        msg = new JOptionPane();
        if (er) {
            msg.showMessageDialog(null, errf, "Erreur de fichier CSV :", JOptionPane.ERROR_MESSAGE);
            sc.close();
            return null;
        }
        if (wr) {
            msg.showMessageDialog(null, warf, "Attention :", JOptionPane.WARNING_MESSAGE);
        }
        sc.close();
        return cont;
    }

    public static void exportcsv(String path,DListe etu) throws Exception {
        String ligne="";
        int ind=0;
        PrintWriter writer = new PrintWriter(path, "UTF-8");
        for (int i=0;i<etu.nbPersonne();i++){
            ligne="";
            if (etu.getPersonne(i)!=null){
                //ligne = etu.getPersonne(i).getPrenom() + ";";
                //ligne += etu.getPersonne(i).getNom() + ";";
                for (int j=0;j<etu.getPersonne(i).getInfos().size();j++){
                    ligne += etu.getPersonne(i).getInfos().get(j) + ";";
                }
                ind=ligne.length()-1;
                ligne=ligne.substring(0,ind);
                writer.println(ligne);
            }
        }
        writer.close();
    }

}
