package com.stcal.fen;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class FChooser {

    public static final int NOT_CHOSEN = 0;
    public static final int CHOSEN = 1;
    public static final int CANCELED = 2;
    public static final int ERROR = 3;

    protected JFileChooser finder = new JFileChooser();
    protected String ext = "csv";
    protected int result = NOT_CHOSEN;

    public void show (){
        result = NOT_CHOSEN;
        finder.addChoosableFileFilter(new FileNameExtensionFilter("fichiers " + ext.toUpperCase(),ext.toLowerCase()));
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        int ret = finder.showDialog(panel,"Ouvrir " + ext.toUpperCase());
        switch (ret){
            case JFileChooser.APPROVE_OPTION :
                result = CHOSEN;
                System.out.println("FChooser: Selected " + finder.getSelectedFile().getPath());
                break;
            case JFileChooser.CANCEL_OPTION :
                System.out.println("FChooser: Sélection annulée");
                result = CANCELED;
                break;
            default :
                System.out.println("FChooser: Erreur");
                result = ERROR;
                break;
        }
    }

    public void setExt(String ext){
        this.ext = ext;
    }

    public  String getExt(){
        return ext;
    }

    public int status() {
        return result;
    }

    public File file(){
        if (result==CHOSEN){
            return finder.getSelectedFile();
        }
        return null;
    }

    public String path(){
        if (result==CHOSEN){
            return finder.getSelectedFile().getPath();
        }
        return "";
    }

}