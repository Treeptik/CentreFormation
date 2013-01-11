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

	@OneToMany(mappedBy = "administrateur", cascade = CascadeType.PERSIST)
	private List<Stagiaire> listStagiaires = new ArrayList<Stagiaire>();
	@OneToMany(mappedBy = "administrateur", cascade = CascadeType.PERSIST)
	private List<Formateur> listFormateurs = new ArrayList<Formateur>();

	public Administrateur() {
		super();
	}

	public List<Stagiaire> getListStagiaires() {
		return listStagiaires;
	}

	public void setListStagiaires(List<Stagiaire> listStagiaires) {
		this.listStagiaires = listStagiaires;
	}

	public List<Formateur> getListFormateurs() {
		return listFormateurs;
	}

	public void setListFormateurs(List<Formateur> listFormateurs) {
		this.listFormateurs = listFormateurs;
	}
}
