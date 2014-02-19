package com.stcal.don;

public class DCouple {

    protected DEtudiant etu = null;
    protected DTuteur tut = null;

    public DCouple(DEtudiant etu, DTuteur prof){
        this.etu = etu;
        this.tut = prof;
    }

    public void setEtu(DEtudiant etu){
        this.etu = etu;
    }

    public void setTut(DTuteur tut){
        this.tut = tut;
    }

    public DEtudiant getEtu(){
        return etu;
    }

    public DTuteur getTut(){
        return tut;
    }
    public String toString(){


        return "Étudiant : "+etu.getPrenom() +" "+etu.getNom()+" - Tuteur : "+tut.getPrenom()+" "+tut.getNom()  ;

    }




}