package com.stcal.don;

import com.stcal.control.Message;
import com.stcal.control.exceptions.MaxSoutenanceException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class DCreneau {

    private ArrayList<Soutenance> ListSoutenance = new ArrayList();
    private static int max_sout;
    private Calendar date_debut;
    private Calendar date_fin;
    private Integer id;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public ArrayList<Soutenance> getListSoutenance() {
        return ListSoutenance;
    }

    public void setListSoutenance(ArrayList<Soutenance> listSoutenance) {
        ListSoutenance = listSoutenance;
    }

    public String toString(){
        Calendar dateDepart = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm" );
        String str = sdf.format(date_debut.getTime())+"\n";
        /*for(Soutenance sout : ListSoutenance){
            str += sout.getCpl().toString()+"\n";
        }*/
        return  str;

    }
    public void addSBC(DCouple dc){

        ListSoutenance.add(new Soutenance(dc,null));
        Message.out.println(dc + " ajouter à " + this.date_debut);


    }
    public ArrayList<Soutenance> toStringtest(){

        return ListSoutenance;



    }
    public void removedcp(DCouple dcp){
         int i ;
        for(i=0;i<ListSoutenance.size();i++){

            if(ListSoutenance.get(i).getCpl() == dcp)
                ListSoutenance.remove(i);


        }



    }
    public boolean isProfIn(DPersonne proff){

        int i;
        for(i=0;i<ListSoutenance.size();i++){

            if(ListSoutenance.get(i).getCdd() == proff)
                return false ;
            else if(ListSoutenance.get(i).getCpl().getTut()==proff)
                return false;


        }
        return true;



    }
    public boolean isSalleIn(String salle){

        int i;
        for(i=0;i<ListSoutenance.size();i++){

            if(ListSoutenance.get(i).getSalle() == salle)
                return false ;



        }
        return true;



    }
    public Soutenance getSout(DCouple dfc){
            int i;
        for(i=0;i<ListSoutenance.size();i++){

            if(ListSoutenance.get(i).getCpl()==dfc)
                return ListSoutenance.get(i);
        }


       return null;
    }
    public Soutenance getSoutp(DProf dfc){
        int i;
        for(i=0;i<ListSoutenance.size();i++){

            if(ListSoutenance.get(i).getCdd()==dfc)
                return ListSoutenance.get(i);
            else if(ListSoutenance.get(i).getCpl().getTut()==dfc)
                return ListSoutenance.get(i);
        }


        return null;
    }
    public Soutenance getSouts(String salle){
        int i;
        for(i=0;i<ListSoutenance.size();i++){

            if(ListSoutenance.get(i).getSalle()==salle)
                return ListSoutenance.get(i);

        }


        return null;
    }
}
