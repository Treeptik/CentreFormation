package fr.treeptik.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@SuppressWarnings("serial")
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllSessionsOfStagiaire", query = "select stss.id.session from StagiaireSession stss where stss.id.stagiaire= :stagiaire "),
		@NamedQuery(name = "findAllStagiairesOfSession", query = "select stss.id.stagiaire from StagiaireSession stss where stss.id.session= :session "),
		@NamedQuery(name = "findAllStagiaireSessionsOfSession", query = "select stss from StagiaireSession stss where stss.id.session= :session "),
		@NamedQuery(name = "removeStagiaireFromSession", query = "delete from StagiaireSession stss where stss.id.session= :session and stss.id.stagiaire= :stagiaire "),
		@NamedQuery(name = "findByStagiaireAndSession", query = "select stss from StagiaireSession stss where stss.id.session= :session and stss.id.stagiaire= :stagiaire ")
		
})
public class StagiaireSession implements Serializable {

	@EmbeddedId
	PKStagiaireSession id;

	public StagiaireSession() {
	}

	public PKStagiaireSession getId() {
		return id;
	}

	public void setId(PKStagiaireSession id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (!(obj instanceof StagiaireSession)) {
			return false;
		}
		StagiaireSession other = (StagiaireSession) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
