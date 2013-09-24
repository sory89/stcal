package com.stcal;

public class Etudiant extends com.stcal.Personne
{
	protected String lieu;
	protected String sujet;

	public Etudiant(String nom, String prenom, String lieu, String sujet)
	{
		super(nom,prenom);
		this.lieu = lieu;
		this.sujet = sujet;
	}

	public String getNom()
	{
		return super.getNom();
	}

	public String getPrenom()
	{
		return super.getPrenom();
	}

	public String getLieu()
	{
		return "Lieu : " + this.lieu;
	}

	public String getSujet()
	{
		return "Sujet : " + this.sujet;
	}

	public String getEtudiant()
	{
		return super.getPersonne() + "\n" + this.lieu + "\n" + this.sujet;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
	}

	public void setLieu(String lieu)
	{
		this.lieu = lieu;
	}

	public void setSujet(String sujet)
	{
		this.sujet = sujet;
	}

	public void setEtudiant(String nom, String prenom, String lieu, String sujet)
	{
		super.setPersonne(nom,prenom);
		this.lieu = lieu;
		this.sujet = sujet;
	}

}
