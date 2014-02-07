package com.stcal.control;

import com.stcal.Main;

/**
 * Created with IntelliJ IDEA.
 * User: Mehdi
 * Date: 07/02/14
 * Time: 07:55
 * To change this template use File | Settings | File Templates.
 */
public class CALsettings extends Settings {
    public CALsettings() {
        super("calsettings");
        settings.put("cal","stcal");
        settings.put("dursoutenance","stcal");
        settings.put("nbsoutenance","stcal");
        settings.put("debutj","stcal");
        settings.put("finj","stcal");


    }
}
