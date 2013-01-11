package fr.treeptik.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
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
@NamedQueries({ @NamedQuery(name = "findAllAdministrateurs", query = "select a from Administrateur a") })
public class Administrateur extends User {

/*
	@Column(length = 50)
	private String nom;
	private String email;
	private String password;
	private String role ="ADMIN";*/
	
	@OneToMany(mappedBy = "administrateur", cascade = CascadeType.PERSIST)
	private List<Stagiaire> stagiaires = new ArrayList<Stagiaire>();
	
	@OneToMany(mappedBy = "administrateur", cascade = CascadeType.PERSIST)
	private List<Formateur> formateurs = new ArrayList<Formateur>();

	public Administrateur() {
		super();
	}

/*
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
*/
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
/*
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
*/

}
