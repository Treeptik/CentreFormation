package fr.treeptik.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Session implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String nom;
	@Temporal(TemporalType.DATE)
	private Date dateDebutStage;
	@Temporal(TemporalType.DATE)
	private Date dateFinStage;
	@ManyToMany
	private List<Formateur> listFormateurs;
	@ManyToMany
	private List<Formation> listFormations;
	@ManyToMany
	private List<Stagiaire> listStagiaires;
//	@OneToMany(mappedBy = "session", cascade = CascadeType.PERSIST)
//	private List<Evaluation> listEvaluations = new ArrayList<Evaluation>();
	
	public Session() {
	}

	public Session(Date dateDebuStage, Date dateFinStage) {
		super();
		this.dateDebutStage = dateDebuStage;
		this.dateFinStage = dateFinStage;
	}

	public Date getDateDebutStage() {
		return dateDebutStage;
	}

	public void setDateDebutStage(Date dateDebuStage) {
		this.dateDebutStage = dateDebuStage;
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

	public void setFormateurs(List<Formateur> listFormateurs) {
		this.listFormateurs = listFormateurs;
	}

	public List<Formation> getListFormations() {
		return listFormations;
	}

	public void setFormations(List<Formation> listFormations) {
		this.listFormations = listFormations;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Stagiaire> getListStagiaires() {
		return listStagiaires;
	}

	public void setListStagiaires(List<Stagiaire> listStagiaires) {
		this.listStagiaires = listStagiaires;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
/*
	public List<Evaluation> getListEvaluations() {
		return listEvaluations;
	}

	public void setEvaluations(List<Evaluation> listEvaluations) {
		this.listEvaluations = listEvaluations;
	}
*/

	/**
	 * @param listFormateurs the listFormateurs to set
	 */
	public void setListFormateurs(List<Formateur> listFormateurs) {
		this.listFormateurs = listFormateurs;
	}

	/**
	 * @param listFormations the listFormations to set
	 */
	public void setListFormations(List<Formation> listFormations) {
		this.listFormations = listFormations;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
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

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
