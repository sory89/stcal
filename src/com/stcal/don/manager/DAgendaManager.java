package com.stcal.don.manager;

import com.stcal.don.DAgenda;

import java.util.List;

/**
 * Created by ismail on 24/02/14.
 */
public class DAgendaManager implements Manager<DAgenda> {


    @Override
    public int create(DAgenda nouveau) {
        String sql="INSERT INTO 'dagenda'('') ";
        return 0;
    }

    @Override
    public DAgenda read(int id) {
        return null;
    }

    @Override
    public List readall() {
        return null;
    }

    @Override
    public int update(DAgenda table) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }
}
