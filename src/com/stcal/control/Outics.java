package com.stcal.control;


import com.stcal.don.DCreneau;
import com.stcal.don.DProf;
import com.stcal.don.Soutenance;
import com.stcal.fen.FCal;


import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.*;
import net.fortuna.ical4j.util.UidGenerator;

import javax.swing.*;
import java.awt.*;
import java.io.*;

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




    public Outics(int opt,Object don){


        this.option=opt;
        this.donne= don;




    }


    public Outics(){

        this.option = 20;
        this.donne= null;

    }


     public void export() throws IOException, ValidationException {

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
    public void exportprof(DProf prof) throws IOException, ValidationException {
        Calendar calendar = new Calendar();
        calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);
         System.out.println("generation ics");
        DCreneau obj[][] = FCal.o ;

        for(int i = 0;i<obj.length;i++){
            for(int k=0;k<obj[i].length;k++){

                if(obj[i][k].getSoutp(prof)!=null){
                     System.out.println(obj[i][k].getSoutp(prof)+"oui oui");
                    Soutenance sout = obj[i][k].getSoutp(prof);
                    DCreneau cren = obj[i][k];
                    DateTime startt = new DateTime(cren.getDate_debut().getTime());
                    DateTime finn = new DateTime(cren.getDate_fin().getTime());
                    VEvent event = new VEvent(startt,finn,sout.getCpl().getEtu().toString());
                    if(sout.getCdd()!=null)
                    event.getProperties().add(new Description("Tuteur : "+sout.getCpl().getTut().toString()+" Candide : "+sout.getCdd().toString()));
                    else
                        event.getProperties().add(new Description("Tuteur : "+sout.getCpl().getTut().toString()+" Candide : non défini"));
                    event.getProperties().add(new Location(sout.getSalle()));
                    UidGenerator ug = new UidGenerator("uidGen");
                    Uid uid = ug.generateUid();

                    event.getProperties().add(uid);
                    calendar.getComponents().add(event);
                }
            }
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try {
                FileWriter fw = new FileWriter(chooser.getSelectedFile()+".ics");
                CalendarOutputter outputter = new CalendarOutputter();
                outputter.output(calendar,fw);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }





     }



}
