package com.stcal.don;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: val
 * Date: 10/12/13
 * Time: 17:11
 * To change this template use File | Settings | File Templates.
 */
public class ModelList<T> {

    private ArrayList<T> list;


    public ModelList(ArrayList<T> list) {
        this.list = list;
    }
    public ModelList() {
        this.list = new ArrayList<T>();
    }
    public ArrayList<T> getList() {
        return list;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }

    public int nbPersonne(){
        return list.size();
    }

    public T getPersonne(int i){
        return list.get(i);
    }

    public void add() {

    }

    public void delete() {

    }

    public void remplir() {




























    }


}
