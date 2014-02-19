package com.stcal.don.manager;

import com.stcal.don.DPersonne;

import java.util.List;

/**
 * @author Jean
 * @version 19/02/2014
 */

public class DPersonneManager implements Manager<DPersonne> {


    @Override
    public int create(DPersonne nouveau) {
        return 0;
    }

    @Override
    public DPersonne read(int id) {
        return null;
    }

    @Override
    public List<DPersonne> readall() {
        return null;
    }

    @Override
    public int update(DPersonne table) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }
}
