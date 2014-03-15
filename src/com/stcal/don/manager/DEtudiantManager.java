package com.stcal.don.manager;

import com.stcal.don.DEtudiant;

import java.util.List;

/**
 * @author Jean
 * @version 15/03/2014
 */

public class DEtudiantManager implements Manager<DEtudiant> {

    @Override
    public int create(DEtudiant nouveau) {
        return 0;
    }

    @Override
    public DEtudiant read(int id) {
        return null;
    }

    @Override
    public List<DEtudiant> readall() {
        return null;
    }

    @Override
    public int update(DEtudiant table) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }
}
