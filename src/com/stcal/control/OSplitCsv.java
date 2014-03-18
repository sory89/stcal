package com.stcal.control;

import com.stcal.don.*;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class OSplitCsv {

    public static final String SEPs = ";";
    public static final char SEP = ';';


    public static List splitcsv(File file, String type) throws FileNotFoundException {
        String ligne,errf="Les erreurs suivantes ont été detectées dans le fichier CSV : \n",warf="";
        char cp=' ';
        int nlab=1,nlig=1,nligb=0,nligv=0;
        boolean er=false,wr=false;
        List cont;
        cont = new ArrayList<DCouple>();
        Scanner sc = new Scanner(file);
        if (sc.hasNextLine()){
            Message.out.println("OSplitCsv: splitcsv");
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
                Message.out.println(nlab);
                String split[] = ligne.split(SEPs);
                ArrayList <String> test = new ArrayList<String>(Arrays.asList(split));
                //cont.setLabels(test);
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
                DPersonne perso = null;
                if(type.equals(Type.ETUDIANT.toString()))
                    perso = new DEtudiant(split[0],split[1]);
                if(type.equals(Type.TUTEUR.toString()))
                    perso = new DTuteur(split[0],split[1]);

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
        if (er) {
            Message.poperror(errf);
            sc.close();
            return null;
        }
        if (wr) {
            Message.popwarning(warf);
        }
        sc.close();
        return cont;
    }

    public static void exportcsv(String path,List<DPersonne> etu) throws Exception {
        String ligne="";
        int ind=0;
        PrintWriter writer = new PrintWriter(path, "UTF-8");
        for (int i=0;i<etu.size();i++){
            ligne="";
            if (etu.get(i)!=null){
                //ligne = etu.getPersonne(i).getPrenom() + ";";
                //ligne += etu.getPersonne(i).getNom() + ";";
                for (int j=0;j<etu.get(i).getInfos().size();j++){
                    ligne += etu.get(i).getInfos().get(j) + ";";
                }
                ind=ligne.length()-1;
                ligne=ligne.substring(0,ind);
                writer.println(ligne);
            }
        }
        writer.close();
    }

}
