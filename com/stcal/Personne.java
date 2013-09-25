package com.stcal;

public class Personne{

	protected String nom;
	protected String prenom;

	public Personne(String lastname, String firstname)
	{
		this.nom = lastname;
		this.prenom = firstname;
	}

	public String getNom()	
	{
		return nom;
	}

	public String getPrenom()
	{
		return prenom;
	}

	public String getPersonne()
	{
		return this.nom + " " + this.prenom;
	}

	public void setNom(String lastname)
	{
		this.nom = lastname;
	}

	public void setPrenom(String firstname)
	{
		this.prenom = firstname;
	}

	public void setPersonne(String lastname, String firstname)
	{
		this.nom = lastname;
		this.prenom = firstname;
	}

}
