package fr.treeptik.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class CompoKeysEval implements Serializable {

	@ManyToOne
	private Stagiaire stagiaire;
	@ManyToOne
	private Session session;
	
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((session == null) ? 0 : session.hashCode());
		result = prime * result
				+ ((stagiaire == null) ? 0 : stagiaire.hashCode());
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
		if (!(obj instanceof CompoKeysEval)) {
			return false;
		}
		CompoKeysEval other = (CompoKeysEval) obj;
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
