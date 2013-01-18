package fr.treeptik.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@SuppressWarnings("serial")
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllQuestionsOfEval", query = "select q.id.question from Questionnaire q where q.id.evaluation= :evaluation "),
		@NamedQuery(name = "findQuestionnaireByEval", query = "select q from Questionnaire q where q.id.evaluation= :evaluation ") })
public class Questionnaire implements Serializable {

	@EmbeddedId
	private PKQuestionnaire id;

	public Questionnaire() {
	}

	public PKQuestionnaire getId() {
		return id;
	}

	public void setId(PKQuestionnaire id) {
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
		if (!(obj instanceof Questionnaire)) {
			return false;
		}
		Questionnaire other = (Questionnaire) obj;
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
