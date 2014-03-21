package com.stcal.don.manager;

import com.stcal.don.DCandide;
import com.stcal.don.Soutenance;

import java.util.List;

/**
 * Created by ismail on 21/03/14.
 */
public interface ManagerpourCouple<Bean> {

    public int create(Bean nooveau,DCandide dCandide, Soutenance soutenance);

    public Bean read(int id);

    public List<Bean> readall();

    public int update(Bean table,DCandide dCandide,Soutenance soutenance);

    public int delete(int id);

}
