package com.stcal.don;


import java.util.ArrayList;
import java.util.List;

public class DCandide extends DPersonne {

    public DCandide(String nom, String prenom) {
        super(nom, prenom);
    }

    public DCandide(String nom, String prenom, List<String> infos) {
        super(nom, prenom,(ArrayList)infos);
    }

    public DCandide() {
    }
}
