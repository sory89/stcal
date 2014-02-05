package com.stcal.don;

import javax.swing.*;
import java.util.ArrayList;

public class DListe extends AbstractListModel{

    protected ArrayList<Personne> liste = new ArrayList<Personne>();
    protected ArrayList<String> labels = new ArrayList<String>();

    public ArrayList<String> getLabels (){
        return labels;
    }

    public void setLabels (ArrayList<String> labels){
        for (int i=0;i<labels.size();i++){
            this.labels.add(labels.get(i));
        }
    }

    public void add(DListe liste){
        for (int j=0;j<liste.getLabels().size();j++){
            labels.add(j,liste.getLabels().get(j));
        }
        for (int i=0;i<liste.nbPersonne();i++){
            this.liste.add(liste.getPersonne(i));
        }
    }

    public void add(Personne humain){
        liste.add(humain);
    }

    public boolean rmPersonne(Personne humain){

       return liste.remove(humain);
    }

    public int nbPersonne(){
        return liste.size();
    }

    public Personne getPersonne(int i){
        return liste.get(i);
    }

    public Personne search(String pre,String nom){
        for (int i=0;i<liste.size();i++){

            if (liste.get(i).is(pre,nom)){

                return liste.get(i);

            }
        }

        return null;
    }

    @Override
    public int getSize() {
        return liste.size();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getElementAt(int index) {
        return liste.get(index);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
