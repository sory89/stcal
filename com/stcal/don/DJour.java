package com.stcal.don;

/**
 * Created with IntelliJ IDEA.
 * User: Mehdi
 * Date: 27/01/14
 * Time: 01:05
 * To change this template use File | Settings | File Templates.
 */
public class DJour {
    protected int nummJour;

    @Override
    public String toString() {
        return "Jour{" +
                "nummJour=" + nummJour +
                ", numMois=" + numMois +
                ", numAnnee=" + numAnnee +
                '}';
    }

    protected int numMois;
    protected int numAnnee;
    public DJour(int nj, int nm, int na){


        this.numAnnee=na;
        this.nummJour=nj;
        this.numMois=nm;



    }
    public int getNumAnnee() {
        return numAnnee;
    }

    public void setNumAnnee(int numAnnee) {
        this.numAnnee = numAnnee;
    }

    public int getNummJour() {
        return nummJour;
    }

    public void setNummJour(int nummJour) {
        this.nummJour = nummJour;
    }

    public int getNumMois() {
        return numMois;
    }

    public void setNumMois(int numMois) {
        this.numMois = numMois;
    }





}
