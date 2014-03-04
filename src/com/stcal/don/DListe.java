package com.stcal.don;

import javax.swing.*;
import java.util.Enumeration;

public class DListe extends DefaultListModel<DPersonne> {

    //protected ArrayList<DPersonne> liste = new ArrayList<DPersonne>();
    //protected ArrayList<String> labels = new ArrayList<String>();

    /*
    public ArrayList<String> getLabels (){
        return labels;
    }
    */

    /*
    public void setLabels (ArrayList<String> labels){
        for (int i=0;i<labels.size();i++){
            this.labels.add(labels.get(i));
        }
    }
    */

    /*
    public void add(DListe liste){
        for (int j=0;j<liste.getLabels().size();j++){
            labels.add(j,liste.getLabels().get(j));
        }
        for (int i=0;i<liste.nbPersonne();i++){
            this.liste.add(liste.getPersonne(i));
        }
    }
    */
    /*
    public void add(DPersonne humain){
        liste.add(humain);
    }
    */

    /*
    public boolean rmPersonne(DPersonne humain){

       return liste.remove(humain);
    }
    */

    /*
    public int nbPersonne(){
        return liste.size();
    }
    */

    /*
    public DPersonne getPersonne(int i){
        return liste.get(i);
    }
    */

    /*
    public DPersonne search(String pre,String nom){
        for (int i=0;i<liste.size();i++){

            if (liste.get(i).is(pre,nom)){

                return liste.get(i);

            }
        }

        return null;
    }
    */



    public DListe() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public int getSize() {
        return super.getSize();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public DPersonne getElementAt(int index) {
        return super.getElementAt(index);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void copyInto(Object[] anArray) {
        super.copyInto(anArray);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void trimToSize() {
        super.trimToSize();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void ensureCapacity(int minCapacity) {
        super.ensureCapacity(minCapacity);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void setSize(int newSize) {
        super.setSize(newSize);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public int capacity() {
        return super.capacity();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public int size() {
        return super.size();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Enumeration<DPersonne> elements() {
        return super.elements();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public boolean contains(Object elem) {
        return super.contains(elem);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public int indexOf(Object elem) {
        return super.indexOf(elem);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public int indexOf(Object elem, int index) {
        return super.indexOf(elem, index);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public int lastIndexOf(Object elem) {
        return super.lastIndexOf(elem);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public int lastIndexOf(Object elem, int index) {
        return super.lastIndexOf(elem, index);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public DPersonne elementAt(int index) {
        return super.elementAt(index);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public DPersonne firstElement() {
        return super.firstElement();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public DPersonne lastElement() {
        return super.lastElement();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void setElementAt(DPersonne element, int index) {
        super.setElementAt(element, index);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void removeElementAt(int index) {
        super.removeElementAt(index);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void insertElementAt(DPersonne element, int index) {
        super.insertElementAt(element, index);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void addElement(DPersonne element) {
        super.addElement(element);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public boolean removeElement(Object obj) {
        return super.removeElement(obj);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void removeAllElements() {
        super.removeAllElements();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public String toString() {
        return super.toString();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Object[] toArray() {
        return super.toArray();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public DPersonne get(int index) {
        return super.get(index);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public DPersonne set(int index, DPersonne element) {
        return super.set(index, element);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void add(int index, DPersonne element) {
        super.add(index, element);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public DPersonne remove(int index) {
        return super.remove(index);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void clear() {
        super.clear();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void removeRange(int fromIndex, int toIndex) {
        super.removeRange(fromIndex, toIndex);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
