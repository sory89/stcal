package com.stcal.don;

/**
 * Created with IntelliJ IDEA.
 * User: val
 * Date: 10/12/13
 * Time: 11:50
 * To change this template use File | Settings | File Templates.
 */
public class Couple {
    private DEtudiant etu;
    private Tuteur tut;

    public Couple(DEtudiant etu, Tuteur tut) {
        this.etu = etu;
        this.tut = tut;
    }

    public DEtudiant getEtu() {
        return etu;
    }

    public void setEtu(DEtudiant etu) {
        this.etu = etu;
    }

    public Tuteur getTut() {
        return tut;
    }

    public void setTut(Tuteur tut) {
        this.tut = tut;
    }
}
