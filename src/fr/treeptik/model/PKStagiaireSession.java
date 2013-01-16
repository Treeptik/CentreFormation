package fr.treeptik.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Embeddable
public class PKStagiaireSession implements Serializable {

	@ManyToOne
	private Stagiaire stagiaire;
	@ManyToOne
	private Session session;
	
	public PKStagiaireSession() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		if (!(obj instanceof PKStagiaireSession)) {
			return false;
		}
		PKStagiaireSession other = (PKStagiaireSession) obj;
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
