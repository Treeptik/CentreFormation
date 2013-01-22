package fr.treeptik.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@SuppressWarnings("serial")
@Entity
@NamedQueries({
	@NamedQuery(name = "findAllEvalsOfFormation", query = "select ef.id.evaluation from EvaluationFormation ef where ef.id.formation= :formation ")	
})
public class EvaluationFormation implements Serializable {

	@EmbeddedId
	private PKEvaluationFormation id;

	public EvaluationFormation() {
	}

	public PKEvaluationFormation getId() {
		return id;
	}

	public void setId(PKEvaluationFormation id) {
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
		if (!(obj instanceof EvaluationFormation)) {
			return false;
		}
		EvaluationFormation other = (EvaluationFormation) obj;
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
