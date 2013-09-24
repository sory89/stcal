package com.stcal;

import java.util.ArrayList;

public class Liste {

    protected ArrayList<Personne> liste = new ArrayList<Personne>();

    /** Initialise la liste et la laisse vide */
    public Liste(){
    }

    /** Initialise la liste avec la personne en parametre */
    public Liste(Personne humain){
        liste.add(humain);
    }

    /** ajoute une personne à la liste */
    public void addPersonne(Personne humain){
        liste.add(humain);
    }

    /** supprime une personne de la liste */
    public boolean rmPersonne(Personne humain){
        return liste.remove(humain);
    }

    /** renvoi le nombre de personne dans la liste */
    public int nbPersonne(){
        return liste.size();
    }

    /** renvoi la i eme personne de la liste */
    public Personne getPersonne(int i){
        return liste.get(i);
    }

}
