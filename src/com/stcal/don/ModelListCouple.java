package com.stcal.don;

import java.util.ArrayList;


public class ModelListCouple {
    ArrayList<DCouple> listCouple;

    public ModelListCouple(ArrayList<DCouple> list) {
        this.listCouple = list;
    }

    public ModelListCouple() {
        this.listCouple = new ArrayList<DCouple>();
    }

    public ArrayList<DCouple> getList() {
        return listCouple;
    }

    public void setList(ArrayList<DCouple> list) {
        this.listCouple = list;
    }

    public void add(DCouple cpl) {
        listCouple.add(cpl);
        cpl.getEtu().setLie(true);
    }

    public void add(DEtudiant etu, DTuteur tut) {
        if(etu.getLie()==false) {
            DCouple cpl = new DCouple(etu, tut);
            listCouple.add(cpl);
            cpl.getEtu().setLie(true);
        }
    }

    public void delete(int index) {
        listCouple.get(index).getEtu().setLie(false);
        listCouple.remove(index);
    }

    public int size() {

        return this.listCouple.size();
    }

    public DCouple get(int i) {
        return this.listCouple.get(i);
    }

    public void remove(DCouple dc) {
        this.listCouple.remove(dc);
    }
    public void remove(int selectedIndex) {
        this.listCouple.remove(selectedIndex);
    }

    /*
    public void remplir() {

    }*/
}

/*

add(E e)
add( int index, E e)
remove (int index)
remove (Object o)


*/