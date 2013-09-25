package com.stcal;

import java.util.ArrayList;

public class Liste<T> {

    protected ArrayList<T> liste = new ArrayList<T>();

    /** Initialise la liste et la laisse vide */
    public Liste(){
    }

    /** Initialise la liste avec la personne en parametre */
    public Liste(T humain){
        liste.add(humain);
    }

    /** ajoute une personne à la liste */
    public void add(T humain){
        liste.add(humain);
    }

    /** supprime une personne de la liste */
    public boolean rmPersonne(T humain){
        return liste.remove(humain);
    }

    /** renvoi le nombre de personne dans la liste */
    public int nbPersonne(){
        return liste.size();
    }

    /** renvoi la i eme personne de la liste */
    public T getPersonne(int i){
        return liste.get(i);
    }

}
