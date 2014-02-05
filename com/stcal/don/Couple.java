package com.stcal.don;

/**
 * Created with IntelliJ IDEA.
 * User: val
 * Date: 10/12/13
 * Time: 11:50
 * To change this template use File | Settings | File Templates.
 */
public class Couple {
    private Personne etu;
    private Personne tut;

    public Couple(Personne etu, Personne tut) {
        this.etu = etu;
        this.tut = tut;
    }

    public Personne getEtu() {
        return etu;
    }

    public void setEtu(Personne etu) {
        this.etu = etu;
    }

    public Personne getTut() {
        return tut;
    }

    public void setTut(Personne tut) {
        this.tut = tut;
    }
}
