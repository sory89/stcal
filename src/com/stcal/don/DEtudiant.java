package com.stcal.don;

import java.util.ArrayList;
import java.util.List;


public class DEtudiant extends DPersonne {
    private boolean lie;

    public DEtudiant(String nom, String prenom, List<String> infos, boolean lie) {
        super(nom, prenom,new ArrayList<String>(infos));
        this.lie = lie;
    }

    public DEtudiant(String nom, String prenom) {
        super(nom, prenom);
        this.lie = false;
    }

    public boolean getLie() {
        return lie;
    }

    public void setLie(boolean lie) {
        this.lie = lie;
    }


}
