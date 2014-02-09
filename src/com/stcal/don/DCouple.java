package com.stcal.don;

public class DCouple {

    protected DPersonne etu = null;
    protected DPersonne tut = null;

    public DCouple(DPersonne etu, DPersonne prof){
        this.etu = etu;
        this.tut = prof;
    }

    public void setEtu(DPersonne etu){
        this.etu = etu;
    }

    public void setTut(DPersonne tut){
        this.tut = tut;
    }

    public DPersonne getEtu(){
        return etu;
    }

    public DPersonne getTut(){
        return tut;
    }




}