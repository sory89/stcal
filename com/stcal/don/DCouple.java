package com.stcal.don;

public class DCouple {

    protected DPersonne etu = null;
    protected DPersonne prof = null;

    public DCouple(DPersonne etu, DPersonne prof){
        this.etu = etu;
        this.prof = prof;
    }

    public void setEtu(DPersonne etu){
        this.etu = etu;
    }

    public void setProf(DPersonne prof){
        this.prof = prof;
    }

    public DPersonne getEtu(){
        return etu;
    }

    public DPersonne getProf(){
        return prof;
    }




}