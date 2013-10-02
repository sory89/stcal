package com.stcal;

import java.lang.String;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Fenetre win = new Fenetre(Splitcsv.splitcsvprof("prof.csv"),Splitcsv.splitcsvetu("etu.csv"));
        win.visible();
    }
}
