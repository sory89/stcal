package com.stcal.don;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: val
 * Date: 10/12/13
 * Time: 11:39
 * To change this template use File | Settings | File Templates.
 */
public abstract class DPersonne {

    protected String nom;
    protected String prenom;
    protected ArrayList<String> infos; // catégorie : texte
    protected int db_id = -1;


    public DPersonne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;

    }
    public DPersonne(String nom, String prenom, ArrayList<String> infos) {
        this.nom = nom;
        this.prenom = prenom;
        this.infos = infos;
    }
    public DPersonne() {

    }

    public int getDb_id() {
        return db_id;
    }

    public void setDb_id(int db_id) {
        this.db_id = db_id;
    }

    /*
    public boolean is(String prenom,String nom){
        return this.prenom.equals(prenom) && this.nom.equals(nom);
    }
    */
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
        return this.getPrenom()+" "+this.getNom();
    }
    public String toStringc(){
        return this.getPrenom()+" "+this.getNom();
    }
    public void setInfos(String s) {
        this.infos.add(s);
    }
}
