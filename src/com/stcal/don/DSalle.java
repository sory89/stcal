package com.stcal.don;

/**
 * @author Jean
 * @version 24/03/2014
 */

public class DSalle {

    private Integer num;
    private Integer id;

    public DSalle(Integer id,Integer num) {
        this.num = num;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return num.toString();
    }
}
