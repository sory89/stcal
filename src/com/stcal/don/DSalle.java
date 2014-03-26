package com.stcal.don;

/**
 * @author Jean
 * @version 24/03/2014
 */

public class DSalle {

    private String num;
    private Integer id;

    public DSalle(Integer id,String num) {
        this.num = num;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return num;
    }
}
