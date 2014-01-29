package com.stcal.don;

public class Soutenance
{
    private Couple cpl;
    private DCandide cdd;
    private int salle;

    public Soutenance(Couple couple, DCandide candide)
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

    public DCandide getCdd()
    {
        return cdd;
    }

    public void setCdd(DCandide cdd)
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
