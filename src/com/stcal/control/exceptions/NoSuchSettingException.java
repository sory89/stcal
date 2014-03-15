package com.stcal.control.exceptions;

/**
 * User: Jean
 * Date: 29/01/2014
 */
public class NoSuchSettingException extends Exception {

    private String filename;
    private String key;

    public NoSuchSettingException(String key,String filename) {
        super(key + " not found in " + filename);
        this.key = key;
        this.filename = filename;
    }
}
