package com.stcal.calendar;

import modele.MaxSoutenanceException;

import java.util.ArrayList;
import java.util.Date;


public class Creneau {
    private ArrayList<Soutenance> ListSoutenance = new ArrayList();
    private static int duree;
    private static int max_sout;
    private Date date_debut;
    private Date date_fin;

    public Date getDate_debut()
    {
        return date_debut;
    }

    public void setDate_debut(Date date_debut)
    {
        this.date_debut = date_debut;
    }

    public Date getDate_fin()
    {
        return date_fin;
    }

    public void setDate_fin(Date date_fin)
    {
        this.date_fin = date_fin;
    }

    public int getDuree()
    {
        return duree;
    }

    public void setDuree(int duree)
    {
        this.duree = duree;
    }

    public int getMax_sout()
    {
        return max_sout;
    }

    public void setMax_sout(int max_sout)
    {
        this.max_sout = max_sout;
    }

    private void addSoutenance(Soutenance sout) throws MaxSoutenanceException
    {
        if (ListSoutenance.size()+1>this.max_sout) throw new MaxSoutenanceException();
        ListSoutenance.add(sout);
    }

    private void delSoutenance (Soutenance sout)
    {
        int position = ListSoutenance.indexOf(sout);
        ListSoutenance.remove(position);
    }

}
