package com.stcal.don;


public class Couple {
    private DPersonne etu;
    private DPersonne tut;

    public Couple(DPersonne etu, DPersonne tut) {
        this.etu = etu;
        this.tut = tut;
    }

    public DPersonne getEtu() {
        return etu;
    }

    public void setEtu(DPersonne etu) {
        this.etu = etu;
    }

    public DPersonne getTut() {
        return tut;
    }

    public void setTut(DPersonne tut) {
        this.tut = tut;
    }
}
