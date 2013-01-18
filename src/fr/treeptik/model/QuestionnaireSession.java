package fr.treeptik.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@SuppressWarnings("serial")
@Entity
@NamedQueries({ @NamedQuery(name = "findAllEvalsOfSession", query = "select DISTINCT qs.id.questionnaire.id.evaluation from QuestionnaireSession qs where qs.id.session= :session "),
	@NamedQuery(name = "findAllQuestionnairesOfSession", query = "select qs.id.questionnaire from QuestionnaireSession qs where qs.id.session= :session ")	
})
public class QuestionnaireSession implements Serializable {

	@EmbeddedId
	private PKQuestionnaireSession id;

	public QuestionnaireSession() {
	}

	public PKQuestionnaireSession getId() {
		return id;
	}

	public void setId(PKQuestionnaireSession id) {
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
		if (!(obj instanceof QuestionnaireSession)) {
			return false;
		}
		QuestionnaireSession other = (QuestionnaireSession) obj;
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
