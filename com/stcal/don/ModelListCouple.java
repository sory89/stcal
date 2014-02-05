package com.stcal.don;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: val
 * Date: 21/01/14
 * Time: 09:18
 * To change this template use File | Settings | File Templates.
 */
public class ModelListCouple {
    ArrayList<Couple> listCouple;

    public ModelListCouple(ArrayList<Couple> list) {
        this.listCouple = list;
    }

    public ModelListCouple() {
        this.listCouple = new ArrayList<Couple>();
    }

    public ArrayList<Couple> getList() {
        return listCouple;
    }

    public void setList(ArrayList<Couple> list) {
        this.listCouple = list;
    }

    public void add(Couple cpl) {
        listCouple.add(cpl);
        cpl.getEtu().setLie(true);
    }

    public void add(DEtudiant etu, Tuteur tut) {
        if(etu.getLie()==false) {
            Couple cpl = new Couple(etu, tut);
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

    public Couple get(int i) {
        return this.listCouple.get(i);
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