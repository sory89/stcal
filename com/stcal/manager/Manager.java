package com.stcal.manager;

import java.sql.Connection;
import java.util.List;

/**
 * User: Jean
 * Date: 22/01/2014
 */
public interface Manager<Bean> {

    public int create(Bean nouveau);

    public Bean read(int id);

    public List<Bean> readall();

    public int update(Bean table);

    public int delete(int id);

}
