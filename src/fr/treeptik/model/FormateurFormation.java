package fr.treeptik.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@SuppressWarnings("serial")
@Entity
@NamedQueries({ @NamedQuery(name = "findAllFormateursOfFormation", query = "select fefo.id.formateur from FormateurFormation fefo where fefo.id.formation= :formation ") })
public class FormateurFormation implements Serializable {

	@EmbeddedId
	private PKFormateurFormation id;

	public FormateurFormation() {
	}

	public PKFormateurFormation getId() {
		return id;
	}

	public void setId(PKFormateurFormation id) {
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
		if (!(obj instanceof FormateurFormation)) {
			return false;
		}
		FormateurFormation other = (FormateurFormation) obj;
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
