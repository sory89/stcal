package com.stcal.control.exceptions;

public class MaxSoutenanceException extends Exception
{
    public MaxSoutenanceException()
    {
        super("Le nombre de soutenances pour ce créneau est déjà au maximum");
    }
}
