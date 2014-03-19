package com.stcal.don;

import java.util.ArrayList;


public class DProf extends DPersonne {

    public DProf(String nom, String prenom, ArrayList<String> infos) {
        super(nom, prenom, infos);
    }

    public DProf(String nom, String prenom) {
        super(nom, prenom);
    }

    public DProf() {
    }

    public String toString(){
        return super.toString();
    }

}
