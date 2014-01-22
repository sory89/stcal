package com.stcal.don;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: val
 * Date: 10/12/13
 * Time: 11:42
 * To change this template use File | Settings | File Templates.
 */
public class Etudiant extends Personne{
    private boolean lie;

    public Etudiant(String nom, String prenom, ArrayList<String> infos, boolean lie) {
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
