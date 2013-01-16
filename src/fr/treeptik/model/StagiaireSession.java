package fr.treeptik.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class StagiaireSession implements Serializable {

	@EmbeddedId
	private PKStagiaireSession id;

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
