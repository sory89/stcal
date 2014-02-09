package com.stcal.don;

import java.util.ArrayList;



public class DEtudiant extends DPersonne {
    private boolean lie;

    public DEtudiant(String nom, String prenom, ArrayList<String> infos, boolean lie) {
        super(nom, prenom, infos);
        this.lie = lie;
    }

    public boolean getLie() {
        return lie;
    }

    public void setLie(boolean lie) {
        this.lie = lie;
    }

    public String toString(){
        return super.toString()+" - lié = "+this.getLie();
    }
}
