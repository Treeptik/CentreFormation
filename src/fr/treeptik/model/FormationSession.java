package fr.treeptik.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@SuppressWarnings("serial")
@Entity
@NamedQueries({
	@NamedQuery(name = "findAllFormationsOfSession", query = "select foss.id.formation from FormationSession foss where foss.id.session= :session "),
	@NamedQuery(name = "findByFormationAndSession", query = "select foss from FormationSession foss where foss.id.session= :session and foss.id.formation= :formation") 
	})
public class FormationSession implements Serializable {

	@EmbeddedId
	private PKFormationSession id;

	public FormationSession() {
	}

	public PKFormationSession getId() {
		return id;
	}

	public void setId(PKFormationSession id) {
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
		if (!(obj instanceof FormationSession)) {
			return false;
		}
		FormationSession other = (FormationSession) obj;
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
