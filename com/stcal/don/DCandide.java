package com.stcal.don;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: val
 * Date: 10/12/13
 * Time: 16:56
 * To change this template use File | Settings | File Templates.
 */
public class DCandide extends Personne {

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
