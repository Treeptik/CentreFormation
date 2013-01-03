package fr.treeptik.model;


import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@SuppressWarnings("serial")
@Entity
@NamedQueries({
	@NamedQuery(name="findALLFormateurs", query = " select f from Formateur f ")
})
@Access(AccessType.FIELD)
public class Formateur implements Serializable{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	@Column(nullable = false)
	private String nom;
	private String prenom;
//	@Column
//	private String specialite;

	@ManyToOne()
	private AdministrateurTreeptik administrateurTreeptik;

	public Formateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Formateur(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public AdministrateurTreeptik getAdministrateurTreeptik() {
		return administrateurTreeptik;
	}

	public void setAdministrateurTreeptik(
			AdministrateurTreeptik administrateurTreeptik) {
		this.administrateurTreeptik = administrateurTreeptik;
	}


}
