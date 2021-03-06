package com.stcal.don;

import java.util.ArrayList;

public class Soutenance
{
    private DCouple cpl;
    private DProf cdd;
    private String salle;
    private int id;
    public Soutenance(DCouple couple, DProf candide)
    {
        this.cpl = couple;
        this.cdd = candide;
        this.salle = null;
    }
    public Soutenance(int id,DCouple couple, DProf candide,String salle)
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
        this.salle = null;

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

    public String getSalle()
    {
        return salle;
    }
    public int getId(){
        return id;
    }

    public void setSalle(String salle)
    {
        this.salle = salle;
    }

    public void setId(int id){
        this.id=id;
    }
    public ArrayList<String> getinfos(){

        ArrayList<String> arr = new ArrayList<String>();

        arr.add("Tuteur :"+this.getCpl().getTut().toString());
        if(this.getCdd()!=null)
            arr.add("Candide : "+this.getCdd().toString());
        else
            arr.add("Candide : non défini");
        arr.add("Salle :"+this.getSalle());



        return arr;
    }
}
