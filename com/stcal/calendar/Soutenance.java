package com.stcal.calendar;
import modele.*;

public class Soutenance
{
    private Couple cpl;
    private Candide cdd;
    private int salle;

    public Soutenance(Couple couple, Candide candide)
    {
        this.cpl = couple;
        this.cdd = candide;
        this.salle = 204;

    }

    public Couple getCpl()
    {
        return cpl;
    }

    public void setCpl(Couple cpl)
    {
        this.cpl = cpl;
    }

    public Candide getCdd()
    {
        return cdd;
    }

    public void setCdd(Candide cdd)
    {
        this.cdd = cdd;
    }

    public int getSalle()
    {
        return salle;
    }

    public void setSalle(int salle)
    {
        this.salle = salle;
    }
}
