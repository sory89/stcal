package com.stcal.don;

import java.util.ArrayList;


public class DProf extends DPersonne {

    public DProf(String nom_prof, String pre_prof, String dimi_prof, ArrayList<String> listInfos) {
        //To change body of created methods use File | Settings | File Templates.
        super(nom_prof, pre_prof, listInfos);
        this.diminutif=dimi_prof;

    }

    public String getDiminutif() {
        return diminutif;
    }

    public void setDiminutif(String diminutif) {
        this.diminutif = diminutif;
    }

    private String diminutif;

    public DProf(String nom, String prenom, ArrayList<String> infos) {
        super(nom, prenom, infos);
        diminutif="";
    }

    public DProf(String nom, String prenom) {
        super(nom, prenom);
        diminutif="";
    }

    public DProf() {
        diminutif="";
    }

    public String toString(){
        return super.toString();
    }

}
