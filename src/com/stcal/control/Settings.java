package com.stcal.control;


import com.stcal.Main;
import com.stcal.control.exceptions.NoSuchSettingException;
import com.stcal.control.exceptions.NothingToSaveException;
import com.stcal.fen.FSettings;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Jean
 * Date: 29/01/2014
 */
public class Settings {

    protected HashMap<String,String> settings;
    private String filepath;
    private String filename;

    public Settings(String nom){
        filename = nom + ".json";
        filepath = System.getProperty("user.home") + System.getProperty("file.separator") + ".stcal" + System.getProperty("file.separator") + filename;
        settings = new HashMap<String, String>();
    }

    /**
     * Charge le fichier de configuration
     * @throws IOException
     * @throws JSONException
     */
    public boolean loadfile() throws FileNotFoundException {
        File config = new File(filepath);
        FileInputStream stream;
        stream = new FileInputStream(config);
        StringBuffer fileContent = new StringBuffer("");
        byte[] buffer = new byte[1024];
        try {
            while (stream.read(buffer) != -1) {
                fileContent.append(new String(buffer));
            }
        } catch (IOException e) {
            Message.poperror("Erreure à l'ouverture du fichier de configuration: " + e.getMessage());
            return false;
        }
        JSONObject jObject = null;
        try {
            jObject = new JSONObject(fileContent.toString());
        } catch (JSONException e) {
            Message.poperror("Erreure à la lercture de " + filename + ": " + e.getMessage());
            return false;
        }
        for(Map.Entry<String, String> entry : settings.entrySet()) {
            String key = entry.getKey();
            try {
                settings.put(key,jObject.getString(key));
            } catch (JSONException e) {
                Message.popwarning("Erreure à la lercture de " + key + " dans " + filename + ": " + e.getMessage());
            }
        }
        return true;
    }

    /**
     * Sauvgarde les parametre dans le fichier
     * @throws NothingToSaveException si le fichier ne contient pas de parametre
     */
    public void save() throws NothingToSaveException {
        if (settings.size()<=0) throw new NothingToSaveException();
        File config = new File(filepath);
        FileOutputStream stream;
        JSONObject jObject = new JSONObject();
        for(Map.Entry<String, String> entry : settings.entrySet()) {
            try {
                jObject.put(entry.getKey(),entry.getValue().toString());
            } catch (JSONException e) {
                Message.err.println(e.getMessage());
            }
        }
        try {
            if (!config.exists()){
                config.getParentFile().mkdir();
                config.createNewFile();
            }
            BufferedWriter buffer = new BufferedWriter(new FileWriter(config.getAbsoluteFile()));
            buffer.write(jObject.toString());
            buffer.close();
        }
        catch (IOException e) {
            Message.poperror("Errerue lors de la sauvegarde de " + filename + ": " + e.getMessage());
        }
        Main.fenStatut("Parametre mis à jour (" + filename + ")");
    }

    /**
     * Ouvre une fenettre avec l'utilisateur afin de pouvoir modifier les parametres
     * @return la fenetre cree
     */
    public FSettings ask(){
        return new FSettings(this);
    }

    /**
     * Defini une propriete dans les parametres
     * @param propertie
     * @param value
     * @throws NoSuchSettingException si propertie n'existe pas dans les parametre
     */
    public void set(String propertie,String value) throws NoSuchSettingException {
        if (!settings.containsKey(propertie)) throw new NoSuchSettingException();
        settings.put(propertie,value);
    }

    /**
     * Renvoie la valeur de la propriete en parametre
     * @param propertie
     * @return renvoie la valeur de la propertie
     * @throws NoSuchSettingException
     */
    public String get(String propertie) throws NoSuchSettingException {
        if (!settings.containsKey(propertie)) throw new NoSuchSettingException();
        return settings.get(propertie);
    }

    /**
     * @return renvoie tout les parametre
     */
    public HashMap<String,String> getall(){
        return settings;
    }

    /**
     * @return renvoie le chemin du ficher de config
     */
    public String getSettingsPath(){
        return filepath;
    }

    /**
     * @return renvoie le nombre de propriete changeable
     */
    public int getNbChangeable(){
        return settings.size();
    }

    /**
     * @return le chemin du fichier de parametre
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * @return renvoie le nom du fichier de prametre
     */
    public String getFilename() {
        return filename;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "settings=" + settings +
                ", filepath='" + filepath + '\'' +
                ", filename='" + filename + '\'' +
                '}';
    }
}
