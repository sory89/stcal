package com.stcal.control;


import net.fortuna.ical4j.model.DateTime;

import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * Created with IntelliJ IDEA.
 * User: Mehdi
 * Date: 26/01/14
 * Time: 20:02
 * To change this template use File | Settings | File Templates.
 */
public class parserDate {



    public parserDate(){





    }
     public int getTS(GregorianCalendar d){

         DateTime da = new DateTime(d.getTime());
         Integer.parseInt(da.toString());

         return Integer.parseInt(da.toString());

     }
    public GregorianCalendar getGC(int ts){

        String parse = ""+ts+"";

        return new GregorianCalendar(Integer.parseInt(parse.substring(0,3)),Integer.parseInt(parse.substring(4,5)),Integer.parseInt(parse.substring(6,7)),Integer.parseInt(parse.substring(8,9)),Integer.parseInt(parse.substring(10,11)),Integer.parseInt(parse.substring(12,13))) ;





    }


}
