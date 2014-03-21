package com.stcal.don.manager;

import com.stcal.don.DCreneau;
import com.stcal.don.DSalle;

import java.util.List;

/**
 * Created by ismail on 18/03/14.
 */
public interface newManager <Bean> {

    public int create(Bean nooveau,DSalle salle,DCreneau dCreneau);

    public Bean read(int id);

    public List<Bean> readall();

    public int update(Bean table,DCreneau dCreneau);

    public int delete(int id);

}
