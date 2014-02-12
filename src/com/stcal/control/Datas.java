package com.stcal.control;

import com.stcal.don.DListe;
import com.stcal.don.ModelListCouple;

/**
 * @author Jean
 * @version 12/02/2014
 */

public class Datas {

    public static ModelListCouple stages = new ModelListCouple();
    public static DListe etu = new DListe();
    public static  DListe prof = new DListe();


    public static ModelListCouple getStages() {
        return stages;
    }

    public static void setStages(ModelListCouple stages) {
        Datas.stages = stages;
    }

    public static DListe getEtu() {
        return etu;
    }

    public static void setEtu(DListe etu) {
        Datas.etu = etu;
    }

    public static DListe getProf() {
        return prof;
    }

    public static void setProf(DListe prof) {
        Datas.prof = prof;
    }
}
