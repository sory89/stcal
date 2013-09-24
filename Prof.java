package com.stcal;

public class Prof extends Personne
{
	protected String uv;

	public Prof(String nom, String prenom, String uv)
	{
		super(nom, prenom);
		this.uv = uv;
	}

	public String getUv()
	{
		return "uv : " + this.uv;
	}

	public void setUv(String uv)
	{
		this.uv = uv;
	}
}
