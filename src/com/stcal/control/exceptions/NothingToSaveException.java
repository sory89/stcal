package com.stcal.control.exceptions;

/**
 * @author Jean
 * @version 01/02/2014
 */

public class NothingToSaveException extends Exception {

    public NothingToSaveException() {
        super("Pas de paramètre dans cette classe.");
    }
}
