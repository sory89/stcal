package com.stcal.control;



import com.stcal.Main;
import com.stcal.control.exceptions.*;
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
     * Charge le fichier de config
     * @throws NoSettingFileException
     * @throws UnopenableSettingException
     */
    public void loadfile() throws NoSettingFileException, UnopenableSettingException {
        File config = new File(filepath);
        FileInputStream stream;
        try {
            stream = new FileInputStream(config);
            StringBuffer fileContent = new StringBuffer("");
            byte[] buffer = new byte[1024];
            while (stream.read(buffer) != -1) {
                fileContent.append(new String(buffer));
            }
            JSONObject jObject = new JSONObject(fileContent.toString());
            for(Map.Entry<String, String> entry : settings.entrySet()) {
                String key = entry.getKey();
                settings.put(key,jObject.getString(key));
            }
        } catch (FileNotFoundException e) {
            throw new NoSettingFileException() ;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new UnopenableSettingException();
        } catch (JSONException e) {
            System.err.println(e.getMessage());
            throw new UnopenableSettingException();
        }
    }

    /**
     * Sauvgarde les parametre dans le fichier
     * @throws UncreatableSettingException en cas d'echec lors de la creation du fichier
     */
    public void save() throws UncreatableSettingException, NothingToSaveException {
        if (settings.size()<=0) throw new NothingToSaveException();
        File config = new File(filepath);
        FileOutputStream stream;
        JSONObject jObject = new JSONObject();
        for(Map.Entry<String, String> entry : settings.entrySet()) {
            jObject.put(entry.getKey(),entry.getValue().toString());
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
            System.err.println(e.getMessage());
            throw new UncreatableSettingException();
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
