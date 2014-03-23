package com.stcal.control;


import com.stcal.don.DCreneau;
import com.stcal.don.DProf;
import com.stcal.fen.FCal;


import net.fortuna.ical4j.model.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: Mehdi
 * Date: 22/03/14
 * Time: 22:44
 * To change this template use File | Settings | File Templates.
 */
public class Outics {
    private int option;
    private Object donne;
    private Calendar calendar = new Calendar();

    public Outics(int opt,Object don){


        this.option=opt;
        this.donne= don;




    }


    public Outics(){

        this.option = 20;
        this.donne= null;

    }


     public void export(){

        switch ( this.option){
            case 1:
                this.exportprof((DProf)this.donne);

                break;
            case 2:
                break;
            case 20:
                this.exportpdf();
                break;
            default:
                break;


        }}

      public void exportpdf(){


            DCreneau obj[][] = FCal.o ;







     }
    public void exportprof(DProf prof){

        DCreneau obj[][] = FCal.o ;




    }



}
