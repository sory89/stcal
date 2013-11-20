package com.stcal.don;

public class DStage {

    protected DPersonne etu;
    protected DPersonne prof;
    protected DPersonne cand;

    public DStage(){}

    public DStage(DPersonne etu,DPersonne prof,DPersonne cand){
        this.etu = etu;
        this.prof = prof;
        this.cand = cand;
    }

    public void setEtu(DPersonne etu){
        this.etu = etu;
    }

    public void setProf(DPersonne prof){
        this.prof = prof;
    }

    public void setCand(DPersonne cand){
        this.cand = cand;
    }

    public DPersonne getEtu(){
        return etu;
    }

    public DPersonne getProf(){
        return prof;
    }

    public DPersonne getCand(){
        return cand;
    }

}