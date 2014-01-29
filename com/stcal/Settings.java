package com.stcal;



import com.stcal.exceptions.NoSettingFileException;
import com.stcal.exceptions.UncreatableSettingException;
import com.stcal.exceptions.UnopenableSettingException;
import com.stcal.json.JSONException;
import com.stcal.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Jean
 * Date: 29/01/2014
 */
public class Settings {

    protected HashMap<String,String> settings;
    protected String path;

    public Settings(){
        path = System.getProperty("user.home") + "/.stcal/settings.json";
        settings = new HashMap<String, String>();
        settings.put("DBUser","stcal");
        settings.put("DBPassword","stcal");
        settings.put("DBServer","localhost");
        settings.put("DBPort","3306");
    }

    /**
     * for dev pupose only
     * @param args
     */
    public static void main(String[] args) {
        Settings test = new Settings();
        try {
            test.loadfile();
        } catch (NoSettingFileException e) {
            e.printStackTrace();
        } catch (UnopenableSettingException e) {
            e.printStackTrace();
        }
        System.out.println(test.settings);
    }

    /**
     * Charge le fichier de config
     * @throws NoSettingFileException
     * @throws UnopenableSettingException
     */
    public void loadfile() throws NoSettingFileException, UnopenableSettingException {
        File bundlePlist = new File(path);
        FileInputStream bundlePlistIS;
        try {
            bundlePlistIS = new FileInputStream(bundlePlist);
            StringBuffer fileContent = new StringBuffer("");
            byte[] buffer = new byte[1024];
            while (bundlePlistIS.read(buffer) != -1) {
                fileContent.append(new String(buffer));
            }
            JSONObject jObject = new JSONObject(fileContent.toString());
            for(Map.Entry<String, String> entry : settings.entrySet()) {
                String key = entry.getKey();
                settings.put(key,jObject.getString(key));
            }
        } catch (FileNotFoundException e) {
            throw new NoSettingFileException();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new UnopenableSettingException();
        } catch (JSONException e) {
            System.err.println(e.getMessage());
            throw new UnopenableSettingException();
        }
    }

    public void save() throws UncreatableSettingException {
        // TODO save setting into file
    }

    public void ask(){
        // TODO ask user for settings
    }

}
