package com.stcal.don;

/**
 * Created by ismail on 18/03/14.
 */
public class DSalle {
    private int id;
    private int num;


    public DSalle(int id,int num){
        this.id=id;
        this.num=num;
    }

    public DSalle(int num) {
        this.num=num;
    }

    public int getId(){
        return id;
    }
    public int getNum(){
        return num;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setNum(int num){
        this.num=num;
    }

}
