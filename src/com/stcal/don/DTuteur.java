package com.stcal.don;

import java.util.ArrayList;


public class DTuteur extends DPersonne {

    public DTuteur(String nom, String prenom, ArrayList<String> infos) {
        super(nom, prenom, infos);
    }

    public DTuteur(String nom, String prenom) {
        super(nom, prenom);
    }

    public DTuteur() {
    }

    public String toString(){
        return super.toString();
    }

    public boolean getLie() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }


    public void setLie(boolean lie) {

    }
}
