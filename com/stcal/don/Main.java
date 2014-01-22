package com.stcal.don;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: val
 * Date: 21/01/14
 * Time: 09:34
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<String> infos = new ArrayList<String>();
        ModelListCouple listCouple = new ModelListCouple();

        Etudiant etu1 = new Etudiant("NomEtu_1", "PrenomEtu_1",infos, false);
        Tuteur tut1 = new Tuteur("NomProf_1","PrenomProf_1",infos);

        Etudiant etu2 = new Etudiant("NomEtu_2", "PrenomEtu_2",infos, false);
        Tuteur tut2 = new Tuteur("NomProf_2","PrenomProf_2",infos);

        Etudiant etu3 = new Etudiant("NomEtu_3", "PrenomEtu_3",infos, false);
        Tuteur tut3 = new Tuteur("NomProf_3","PrenomProf_3",infos);

        System.out.println(etu1.toString());
        System.out.println(etu2.toString());
        System.out.println(etu3.toString());

        listCouple.add(etu1,tut1);
        System.out.println(etu1.toString());
        System.out.println(tut1.toString());


        listCouple.add(etu2,tut2);
        System.out.println(etu2.toString());
        System.out.println(tut2.toString());

        listCouple.add(etu3,tut3);
        System.out.println(etu3.toString());
        System.out.println(tut3.toString());

        System.out.println(listCouple.getList().get(0).getEtu().toString());
        System.out.println(listCouple.getList().get(1).getEtu().toString());
        System.out.println(listCouple.getList().get(2).getEtu().toString());


        listCouple.delete(1);
        System.out.println(etu1.toString());
        System.out.println(etu2.toString());
        System.out.println(etu3.toString());
        System.out.println(etu3.toString());

    }
}
