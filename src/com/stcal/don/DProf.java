package com.stcal.don;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jean
 * @version 16/03/2014
 */

public class DProf extends DPersonne {

    public DProf(String nom, String pre, List<String> info) {
        super(nom,pre,info);
    }

    public DProf(String nom, String prenom) {
        super(nom, prenom);
    }
}
