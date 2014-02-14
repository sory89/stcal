package com.stcal.don;

public class Soutenance
{
    private DCouple cpl;
    private DCandide cdd;
    private int salle;

    public Soutenance(DCouple couple, DCandide candide)
    {
        this.cpl = couple;
        this.cdd = candide;
        this.salle = 204;

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
