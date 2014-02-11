package com.stcal.control;

import com.stcal.control.parserDate;
import datechooser.model.multiple.Period;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Mehdi
 * Date: 26/01/14
 * Time: 19:22
 * To change this template use File | Settings | File Templates.
 */
public class parserPeriod {
    protected parserDate parse = null;
    protected Iterator<Period> period = null;
    protected List<Calendar> dates = new ArrayList<Calendar>();




    public parserPeriod(Iterator<Period> p){
        Period tmp=null;
        Calendar start=null;
        Calendar end=null;
        Calendar starttmp = null;
        Calendar endtmp = null;
        this.period=p;

        while(period.hasNext()){
              tmp=period.next();

              parse=null;
            if(tmp.isOneDate()){

              parse = new parserDate(tmp.getStartDate());


               dates.add(parse.getD());


            }
            else{
               start=tmp.getStartDate();
               end = tmp.getEndDate();

                if(start.get(Calendar.YEAR)!=end.get(Calendar.YEAR)){
                    endtmp=new GregorianCalendar(start.get(Calendar.YEAR),11,31);
                    starttmp=new GregorianCalendar(end.get(Calendar.YEAR),0,1);
                     while(endtmp.get(Calendar.DAY_OF_YEAR)>=start.get(Calendar.DAY_OF_YEAR)){
                         if(start.get(Calendar.DAY_OF_WEEK)!=Calendar.SATURDAY && start.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY) {

                             dates.add(start);

                         }
                         start.add(Calendar.DAY_OF_YEAR,1);
                     }
                    while(end.get(Calendar.DAY_OF_YEAR)>=starttmp.get(Calendar.DAY_OF_YEAR)){
                        if(starttmp.get(Calendar.DAY_OF_WEEK)!=Calendar.SATURDAY && starttmp.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY) {

                            dates.add(starttmp);

                        }
                        starttmp.add(Calendar.DAY_OF_YEAR,1);
                    }

                }
                else{

                    while(end.get(Calendar.DAY_OF_YEAR)>=start.get(Calendar.DAY_OF_YEAR)){
                        if(start.get(Calendar.DAY_OF_WEEK)!=Calendar.SATURDAY && start.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY) {

                            dates.add(start);


                        }
                        start.add(Calendar.DAY_OF_YEAR,1);
                    }

                }




            }

        }

    }

    public List<Calendar> getDates(){


        return dates;
    }


}
