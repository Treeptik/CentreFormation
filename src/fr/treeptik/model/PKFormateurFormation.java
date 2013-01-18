package fr.treeptik.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Embeddable
public class PKFormateurFormation implements Serializable {

	@ManyToOne
	private Formation formation;
	@ManyToOne
	private Formateur formateur;
	
	public PKFormateurFormation() {
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((formateur == null) ? 0 : formateur.hashCode());
		result = prime * result
				+ ((formation == null) ? 0 : formation.hashCode());
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
		if (!(obj instanceof PKFormateurFormation)) {
			return false;
		}
		PKFormateurFormation other = (PKFormateurFormation) obj;
		if (formateur == null) {
			if (other.formateur != null) {
				return false;
			}
		} else if (!formateur.equals(other.formateur)) {
			return false;
		}
		if (formation == null) {
			if (other.formation != null) {
				return false;
			}
		} else if (!formation.equals(other.formation)) {
			return false;
		}
		return true;
	}

}
