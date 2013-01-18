package fr.treeptik.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((listFormateurs == null) ? 0 : listFormateurs.hashCode());
		result = prime * result
				+ ((listStagiaires == null) ? 0 : listStagiaires.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Administrateur)) {
			return false;
		}
		Administrateur other = (Administrateur) obj;
		if (listFormateurs == null) {
			if (other.listFormateurs != null) {
				return false;
			}
		} else if (!listFormateurs.equals(other.listFormateurs)) {
			return false;
		}
		if (listStagiaires == null) {
			if (other.listStagiaires != null) {
				return false;
			}
		} else if (!listStagiaires.equals(other.listStagiaires)) {
			return false;
		}
		return true;
	}
}
