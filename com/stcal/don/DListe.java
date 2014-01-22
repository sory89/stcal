package com.stcal.don;

import java.util.ArrayList;

public class DListe {

    protected ArrayList<DPersonne> liste = new ArrayList<DPersonne>();
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

    public void add(DPersonne humain){
        liste.add(humain);
    }

    public boolean rmPersonne(DPersonne humain){
        return liste.remove(humain);
    }

    public int nbPersonne(){
        return liste.size();
    }

    public DPersonne getPersonne(int i){
        return liste.get(i);
    }

    public DPersonne search(String pre,String nom){
        for (int i=0;i<liste.size();i++){
            if (liste.get(i).is(pre,nom)){
                return liste.get(i);
            }
        }
        return null;
    }

}
