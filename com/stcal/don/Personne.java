package com.stcal.don;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: val
 * Date: 10/12/13
 * Time: 11:39
 * To change this template use File | Settings | File Templates.
 */
public class Personne {

    protected String nom;
    protected String prenom;
    protected ArrayList<String> infos; // catégorie : texte

    public Personne(String nom, String prenom, ArrayList<String> infos) {
        this.nom = nom;
        this.prenom = prenom;
        this.infos = infos;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public ArrayList<String> getInfos() {
        return infos;
    }

    public String getInfosAt(int index){
        return infos.get(index);
    }

    public void setInfos(ArrayList<String> infos) {
        this.infos = infos;
    }

    public String toString(){
        return getClass().getName()+" : prenom = "+this.getPrenom()+" - nom = "+this.getNom();
    }
}
