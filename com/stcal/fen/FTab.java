package com.stcal.fen;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

public class FTab {

    private JPanel tabpan = new JPanel();
    private String nom = "";

    /**
     * Permet de creer un onglet
     * @param titre: nom de l'ongelet
     */
    public FTab(String titre){
        nom = titre;
        tabpan.setOpaque(false);
    }

    /**
     * Repaint et redessine l'ongelet
     */
    public void refresh(){
        tabpan.revalidate();
        tabpan.repaint();
    }

    /**
     *
     * @return JPanel de l'ongelet
     */
    public JPanel pan(){
        return tabpan;
    }

    /**
     * @return Le nom de l'onglet
     */
    public String getNom(){
        return nom;
    }

    /**
     * Chanque ongelet possede une boite de text pour afficher des infos.
     * Cette methode permet de definir le texte a afficher.
     * Elle est faite pour etre réecite dans les classes heritant de cette classe.
     * @param details les chaines de caratere à afficher
     */
    public void setInfo(ArrayList<String> details){}


}
