package com.stcal.control;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
import java.io.*;
import java.text.SimpleDateFormat;
import java.net.SocketException;
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


    public Outics(int op){

        this.option = op;
        this.donne= null;

    }



     public void export() throws IOException, ValidationException {

        switch ( this.option){
            case 1:
                this.exportprof((DProf)this.donne);

                break;
            case 0:
                this.exportsalle((String)this.donne);
                break;
            case 20:
                this.exportpdf();
                break;
            case 21:
                this.exportics();
                break;
            default:
                break;


        }}

    private void exportics() throws SocketException {

        Calendar calendar = new Calendar();
        calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);
        System.out.println("generation ics");
        DCreneau obj[][] = FCal.o ;
        for(int i = 0;i<obj.length;i++){
            for(int k=0;k<obj[i].length;k++){
                if(!obj[i][k].getListSoutenance().isEmpty()){
                    for(Soutenance sout : obj[i][k].getListSoutenance()){


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
                    calendar.getComponents().add(event);  }
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


    public void exportpdf(){


            DCreneau obj[][] = FCal.o ;


          com.itextpdf.text.Document document = new com.itextpdf.text.Document();
          try {

              JFileChooser chooser = new JFileChooser();
              chooser.setCurrentDirectory(new File(System.getProperty("user.home")));

              int retrival = chooser.showSaveDialog(null);
              if (retrival == JFileChooser.APPROVE_OPTION) {
                  OutputStream file = new FileOutputStream(new File(chooser.getSelectedFile()+".pdf"));
                  PdfWriter.getInstance(document, file);

                  document.open();
                  PdfPTable table = new PdfPTable(6); // 6 colones.
                  table.addCell(new PdfPCell(new Paragraph("Date")));
                  table.addCell(new PdfPCell(new Paragraph("Heure")));
                  table.addCell(new PdfPCell(new Paragraph("Étudiant")));
                  table.addCell(new PdfPCell(new Paragraph("Tuteur")));
                  table.addCell(new PdfPCell(new Paragraph("Candide")));
                  table.addCell(new PdfPCell(new Paragraph("Salle")));
                  for(int i = 0;i<obj.length;i++){
                      for(int k=0;k<obj[i].length;k++){
                          if(!obj[i][k].getListSoutenance().isEmpty()){
                              for(Soutenance sout : obj[i][k].getListSoutenance()){
                                  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy" );
                                  String str = sdf.format(obj[i][k].getDate_debut().getTime());
                                  sdf = new SimpleDateFormat("hh:mm" );
                                  String str1 = sdf.format(obj[i][k].getDate_debut().getTime());

                                  table.addCell(new PdfPCell(new Paragraph(str)));
                                  table.addCell(new PdfPCell(new Paragraph(str1)));
                                  table.addCell(new PdfPCell(new Paragraph(sout.getCpl().getEtu().toString())));
                                  table.addCell(new PdfPCell(new Paragraph(sout.getCpl().getTut().toString())));
                                  if(sout.getCdd()!=null)
                                  table.addCell(new PdfPCell(new Paragraph(sout.getCdd().toString())));
                                  else
                                  table.addCell(new PdfPCell(new Paragraph("non défini")));
                                  table.addCell(new PdfPCell(new Paragraph(sout.getSalle())));
                              }
                          }
                      }
                  }
                  document.add(table);
                  document.close();
                  file.close();
              }
          } catch(Exception err){
              err.printStackTrace();
          }






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
    public void exportsalle(String salle) throws SocketException {



        Calendar calendar = new Calendar();
        calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);
        System.out.println("generation ics");
        DCreneau obj[][] = FCal.o ;

        for(int i = 0;i<obj.length;i++){
            for(int k=0;k<obj[i].length;k++){

                if(obj[i][k].getSouts(salle)!=null){
                    System.out.println(obj[i][k].getSouts(salle)+"oui oui");
                    Soutenance sout = obj[i][k].getSouts(salle);
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
