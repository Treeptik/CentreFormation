package fr.treeptik.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllFormations", query = "select f from Formation f ") })
public class Formation implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(length = 50)
	private String nom;
	@ManyToMany(mappedBy = "listFormations")
	private List<Session> listSessions;
	@ManyToMany
	private List<Formateur> listFormateurs;
	
	public Formation() {
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

	public List<Session> getListSessions() {
		return listSessions;
	}

	public void setListSessions(List<Session> listSessions) {
		this.listSessions = listSessions;
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
		int result = 1;
		result = prime * result + id;
		result = prime * result
				+ ((listFormateurs == null) ? 0 : listFormateurs.hashCode());
		result = prime * result
				+ ((listSessions == null) ? 0 : listSessions.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Formation)) {
			return false;
		}
		Formation other = (Formation) obj;
		if (id != other.id) {
			return false;
		}
		if (listFormateurs == null) {
			if (other.listFormateurs != null) {
				return false;
			}
		} else if (!listFormateurs.equals(other.listFormateurs)) {
			return false;
		}
		if (listSessions == null) {
			if (other.listSessions != null) {
				return false;
			}
		} else if (!listSessions.equals(other.listSessions)) {
			return false;
		}
		if (nom == null) {
			if (other.nom != null) {
				return false;
			}
		} else if (!nom.equals(other.nom)) {
			return false;
		}
		return true;
	}

	
}
