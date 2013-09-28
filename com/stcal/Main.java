package com;

import java.lang.String;
import java.io.IOException;



public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {


        com.Fenetre win = new com.Fenetre(com.Splitcsv.splitcsvprof("C:\\prof.csv"),com.Splitcsv.splitcsvetu("C:\\etu.csv"));
        win.visible();
    }



}
