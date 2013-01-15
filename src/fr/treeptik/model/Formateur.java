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
	@ManyToOne()
	private Administrateur administrateur;
	@ManyToMany(mappedBy = "listFormateurs")
	private List<Session> listSessions;
	@ManyToMany(mappedBy = "listFormateurs")
	private List<Formation> listFormations;

	

	public Formateur() {
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

	public List<Session> getListSessions() {
		return listSessions;
	}

	public void setListSessions(List<Session> listSessions) {
		this.listSessions = listSessions;
	}

	public List<Formation> getListFormations() {
		return listFormations;
	}

	public void setListFormations(List<Formation> listFormations) {
		this.listFormations = listFormations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((administrateur == null) ? 0 : administrateur.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((listFormations == null) ? 0 : listFormations.hashCode());
		result = prime * result
				+ ((listSessions == null) ? 0 : listSessions.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
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
		if (!(obj instanceof Formateur)) {
			return false;
		}
		Formateur other = (Formateur) obj;
		if (administrateur == null) {
			if (other.administrateur != null) {
				return false;
			}
		} else if (!administrateur.equals(other.administrateur)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (listFormations == null) {
			if (other.listFormations != null) {
				return false;
			}
		} else if (!listFormations.equals(other.listFormations)) {
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
		if (prenom == null) {
			if (other.prenom != null) {
				return false;
			}
		} else if (!prenom.equals(other.prenom)) {
			return false;
		}
		return true;
	}
	
}
