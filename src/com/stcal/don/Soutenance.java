package com.stcal.don;

public class Soutenance
{
    private DCouple cpl;
    private DProf cdd;
    private int salle;
    private int id;
    public Soutenance(DCouple couple, DProf candide)
    {
        this.cpl = couple;
        this.cdd = candide;
        this.salle = 204;
    }
    public Soutenance(int id,DCouple couple, DProf candide,int salle)
    {
        this.id=id;
        this.cpl = couple;
        this.cdd = candide;
        this.salle = salle;
    }

    public Soutenance(DCouple couple)
    {
        this.cpl = couple;
        this.cdd = null;
        this.salle = 204;

    }

    public DCouple getCpl()
    {
        return cpl;
    }

    public void setCpl(DCouple cpl)
    {
        this.cpl = cpl;
    }

    public DProf getCdd()
    {
        return cdd;
    }

    public void setCdd(DProf cdd)
    {
        this.cdd = cdd;
    }

    public int getSalle()
    {
        return salle;
    }
    public int getId(){
        return id;
    }

    public void setSalle(int salle)
    {
        this.salle = salle;
    }

    public void setId(int id){
        this.id=id;
    }
}
