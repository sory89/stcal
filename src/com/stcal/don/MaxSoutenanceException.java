package com.stcal.don;

public class MaxSoutenanceException extends Exception
{
    public MaxSoutenanceException()
    {
        super("Le nombre de soutenances pour ce créneau est déjà au maximum");
    }
}
