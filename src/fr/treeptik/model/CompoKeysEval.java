package fr.treeptik.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Embeddable
public class CompoKeysEval implements Serializable {

	@ManyToOne
	private Stagiaire stagiaire;
	@ManyToOne
	private Session session;
	@ManyToOne
	private Formation formation;
	@ManyToOne
	private Formateur formateur;
	
	public CompoKeysEval() {
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
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
		result = prime * result + ((session == null) ? 0 : session.hashCode());
		result = prime * result
				+ ((stagiaire == null) ? 0 : stagiaire.hashCode());
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
		if (!(obj instanceof CompoKeysEval)) {
			return false;
		}
		CompoKeysEval other = (CompoKeysEval) obj;
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
		if (session == null) {
			if (other.session != null) {
				return false;
			}
		} else if (!session.equals(other.session)) {
			return false;
		}
		if (stagiaire == null) {
			if (other.stagiaire != null) {
				return false;
			}
		} else if (!stagiaire.equals(other.stagiaire)) {
			return false;
		}
		return true;
	}

}
