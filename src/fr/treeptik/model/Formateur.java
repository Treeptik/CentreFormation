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
		@NamedQuery(name = "findAllFormateurs", query = " select f from Formateur f "),
		@NamedQuery(name = "findFormateurByName", query = " select f from Formateur f where f.nom = :nom") })
@Access(AccessType.FIELD)
public class Formateur implements Serializable {

	@Id
	@GeneratedValue
	private int id;
	@Column(nullable = false)
	private String nom;
	private String prenom;
	private String specialite;
	@ManyToOne
	private Evaluation evaluation;

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

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((evaluation == null) ? 0 : evaluation.hashCode());
		result = prime * result + id;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result
				+ ((specialite == null) ? 0 : specialite.hashCode());
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
		if (evaluation == null) {
			if (other.evaluation != null) {
				return false;
			}
		} else if (!evaluation.equals(other.evaluation)) {
			return false;
		}
		if (id != other.id) {
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
		if (specialite == null) {
			if (other.specialite != null) {
				return false;
			}
		} else if (!specialite.equals(other.specialite)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return " " + nom + " " + prenom + " ";
	}
}
