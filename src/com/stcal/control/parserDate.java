package com.stcal.control;


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

        GregorianCalendar d = null;

    public parserDate(Calendar c){



         d = new GregorianCalendar(c.get(Calendar.DAY_OF_MONTH),c.get(Calendar.MONTH),c.get(Calendar.YEAR)) ;

    }
     public GregorianCalendar getD(){

         return d;
     }


}
