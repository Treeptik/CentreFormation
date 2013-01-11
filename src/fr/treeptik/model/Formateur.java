package fr.treeptik.model;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
	private int id;
	@Column(nullable = false)
	private String nom;
	private String prenom;
//	@Column
//	private String specialite;

	@ManyToMany(mappedBy = "formateurs")
	private List<Session> sessions;

	@ManyToOne()
	private Administrateur administrateur;

	public Formateur() {
	}

	public Formateur(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	public Administrateur getAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(
			Administrateur administrateur) {
		this.administrateur = administrateur;
	}


}
