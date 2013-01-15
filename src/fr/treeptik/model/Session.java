package fr.treeptik.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQuery(name="findFormateursOfSession", query="select ft from Formateur ft join " +
		" ft.listSessions sess where sess.id= :id")
public class Session implements Serializable {

	public static final String FIND_FORMATEURS_OF_SESSION = "Session.findFormateursOfSession";
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String nom;
	@Temporal(TemporalType.DATE)
	private Date dateDebutStage;
	@Temporal(TemporalType.DATE)
	private Date dateFinStage;
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Formateur> listFormateurs;
	@ManyToMany
	private List<Formation> listFormations;
	@ManyToMany
	private List<Stagiaire> listStagiaires;

	public Session() {
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

	public Date getDateDebutStage() {
		return dateDebutStage;
	}

	public void setDateDebutStage(Date dateDebutStage) {
		this.dateDebutStage = dateDebutStage;
	}

	public Date getDateFinStage() {
		return dateFinStage;
	}

	public void setDateFinStage(Date dateFinStage) {
		this.dateFinStage = dateFinStage;
	}

	public List<Formateur> getListFormateurs() {
		return listFormateurs;
	}

	public void setListFormateurs(List<Formateur> listFormateurs) {
		this.listFormateurs = listFormateurs;
	}

	public List<Formation> getListFormations() {
		return listFormations;
	}

	public void setListFormations(List<Formation> listFormations) {
		this.listFormations = listFormations;
	}

	public List<Stagiaire> getListStagiaires() {
		return listStagiaires;
	}

	public void setListStagiaires(List<Stagiaire> listStagiaires) {
		this.listStagiaires = listStagiaires;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateDebutStage == null) ? 0 : dateDebutStage.hashCode());
		result = prime * result
				+ ((dateFinStage == null) ? 0 : dateFinStage.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((listFormateurs == null) ? 0 : listFormateurs.hashCode());
		result = prime * result
				+ ((listFormations == null) ? 0 : listFormations.hashCode());
		result = prime * result
				+ ((listStagiaires == null) ? 0 : listStagiaires.hashCode());
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
		if (!(obj instanceof Session)) {
			return false;
		}
		Session other = (Session) obj;
		if (dateDebutStage == null) {
			if (other.dateDebutStage != null) {
				return false;
			}
		} else if (!dateDebutStage.equals(other.dateDebutStage)) {
			return false;
		}
		if (dateFinStage == null) {
			if (other.dateFinStage != null) {
				return false;
			}
		} else if (!dateFinStage.equals(other.dateFinStage)) {
			return false;
		}
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
		if (listFormations == null) {
			if (other.listFormations != null) {
				return false;
			}
		} else if (!listFormations.equals(other.listFormations)) {
			return false;
		}
		if (listStagiaires == null) {
			if (other.listStagiaires != null) {
				return false;
			}
		} else if (!listStagiaires.equals(other.listStagiaires)) {
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
