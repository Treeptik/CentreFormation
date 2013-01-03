package fr.treeptik.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({ @NamedQuery(name = "findAllAdministrateurs", query = "select a from AdministrateurTreeptik a") })
public class AdministrateurTreeptik {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(length = 50)
	private String nom;
	
	@OneToMany(mappedBy = "administrateurTreeptik", cascade = CascadeType.PERSIST)
	private List<Stagiaire> stagiaires = new ArrayList<Stagiaire>();
	
	@OneToMany(mappedBy = "administrateurTreeptik", cascade = CascadeType.PERSIST)
	private List<Formateur> formateurs = new ArrayList<Formateur>();

	public AdministrateurTreeptik() {
		super();
	}

	public AdministrateurTreeptik(String nom) {
		super();
		this.nom = nom;
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

	public List<Stagiaire> getStagiaires() {
		return stagiaires;
	}

	public void setStagiaires(List<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}

	public List<Formateur> getFormateurs() {
		return formateurs;
	}

	public void setFormateurs(List<Formateur> formateurs) {
		this.formateurs = formateurs;
	}


}
