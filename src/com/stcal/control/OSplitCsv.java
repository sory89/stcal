package com.stcal.control;

import com.stcal.don.*;

import javax.swing.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class OSplitCsv {

    public static DListe splitcsv(File file, String type) throws Exception {
        char SEP = Datas.Csv;
        String SEPs = ""+SEP+"";
        String ligne,errs="Le séparateur du fichier CSV semble être différent de la configuration actuelle. (Changer dans préférences)";
        String errf="Les erreurs suivantes ont été detectées dans le fichier CSV : \n",warf="";
        char cp=' ';
        String test[] = new String[1];
        int nlab=1,nlig=1,nligb=0,nligv=0;
        boolean er=false,wr=false,sr=false;
        DListe cont;
        cont = new DListe();
        Scanner sc = new Scanner(file);
        if (sc.hasNextLine()){
            Message.out.println("OSplitCsv: splitcsv");
            ligne=sc.nextLine();
            int tligne=ligne.length();
            for(int i=0;i<tligne;i++){
                if(ligne.charAt(i)==';' && SEP==',' || ligne.charAt(i)==',' && SEP==';') {
                    sr=true;
                }
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
                test = ligne.split(SEPs);
               } else { }

            if (sr) {
                Message.poperror(errs);
                sc.close();
                return cont;
            }
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
                    perso = new DProf(split[0],split[1]);

                ArrayList<String> essai = new ArrayList<String>();


            for (int i=0;i<split.length;i++){

                essai.add(test[i]+" : "+split[i]);
            }
                perso.setInfos(essai);
                cont.addElement(perso);

            }

            nlig+=1;
        }

        if (nligb!=0) {
            errf=errf + "\n- Il y a "+nligb+"/"+nlig+" lignes ne correspondant pas à la ligne de libellés";
        }
        if (er) {
            Message.poperror(errf);
            sc.close();
            return cont;
        }
        if (wr) {
            Message.popwarning(warf);
        }
        sc.close();
        return cont;
    }

    public static void exportcsv(String path,DefaultListModel<DPersonne> etu) throws Exception {
        String ligne="";
        int ind=0;
        PrintWriter writer = new PrintWriter(path, "UTF-8");
        for (int i=0;i<etu.getSize();i++){
            ligne="";
            if (etu.getElementAt(i)!=null){
                //ligne = etu.getPersonne(i).getPrenom() + ";";
                //ligne += etu.getPersonne(i).getNom() + ";";
                for (int j=0;j<etu.getElementAt(i).getInfos().size();j++){
                    ligne += etu.getElementAt(i).getInfos().get(j) + ";";
                }

                ind=ligne.length()-1;
                ligne=ligne.substring(0,ind);
                writer.println(ligne);
            }
        }
        writer.close();
    }

}
