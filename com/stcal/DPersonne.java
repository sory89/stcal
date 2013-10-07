package com.stcal;

import java.util.ArrayList;

public class DPersonne{

    protected ArrayList<String> info = new ArrayList<String>();
    protected DPersonne linked;

    public DPersonne(){}

	public DPersonne(String lastname, String firstname){
		info.add(0,firstname);
        info.add(1,lastname);
	}

    public void setLinked(DPersonne linked){
        this.linked = linked;
    }

    public DPersonne getLinked(){
        return linked;
    }

    public void setInfo(String inf){
        info.add(inf);
    }

    public ArrayList<String> getInfo(){
        return info;
    }

	public String getNom()	{
		return info.get(1);
	}

	public String getPrenom(){
		return info.get(0);
	}

	public void setNom(String lastname){
        info.set(1,lastname);
	}

	public void setPrenom(String firstname){
        info.set(1,firstname);
	}

    public boolean is(String prenom,String nom){
        return info.get(0).toLowerCase().equals(prenom.toLowerCase()) && info.get(1).toLowerCase().equals(nom.toLowerCase());
    }

}
