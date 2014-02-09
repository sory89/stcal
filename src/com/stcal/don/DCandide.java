package com.stcal.don;

import java.util.ArrayList;


public class DCandide extends DPersonne {

    public DCandide(String nom, String prenom, ArrayList<String> infos) {
        super(nom, prenom, infos);
    }

    public DCandide(String nom, String prenom) {
        super(nom, prenom);
    }

    public DCandide() {
    }

    public String toString() {
        return super.toString();
    }

}
