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
		return "Nom : " + this.nom;
	}

	public String getPrenom()
	{
		return "Prenom : " + this.prenom;
	}

	public String getPersonne()
	{
		return "Nom : " + this.nom + "\nPrenom : " + this.prenom;
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
