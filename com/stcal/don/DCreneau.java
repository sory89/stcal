package com.stcal.don;

import java.util.ArrayList;
import java.util.Calendar;


public class DCreneau {
    private ArrayList<Soutenance> ListSoutenance = new ArrayList();
    private static int duree;
    private static int max_sout;
    private Calendar date_debut;
    private Calendar date_fin;

    public Calendar getDate_debut()
    {
        return date_debut;
    }

    public void setDate_debut(Calendar date_debut)
    {
        this.date_debut = date_debut;
    }

    public Calendar getDate_fin()
    {
        return date_fin;
    }

    public void setDate_fin(Calendar date_fin)
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
