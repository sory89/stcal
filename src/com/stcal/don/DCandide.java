package com.stcal.don;

import java.util.ArrayList;


public class DCandide extends DPersonne {

    public DCandide(String nom, String prenom, ArrayList<String> infos) {
        super(nom, prenom, infos);
    }
    public String toString() {
        return super.toString();
    }


    public boolean getLie() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }


    public void setLie(boolean lie) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
