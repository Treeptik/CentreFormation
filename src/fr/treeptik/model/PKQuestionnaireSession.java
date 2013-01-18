package fr.treeptik.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Embeddable
public class PKQuestionnaireSession implements Serializable {

	@ManyToOne(cascade={CascadeType.ALL})
	private Questionnaire questionnaire;
	@ManyToOne(cascade={CascadeType.ALL})
	private Session session;

	public PKQuestionnaireSession() {
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
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
		result = prime * result
				+ ((questionnaire == null) ? 0 : questionnaire.hashCode());
		result = prime * result + ((session == null) ? 0 : session.hashCode());
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
		if (!(obj instanceof PKQuestionnaireSession)) {
			return false;
		}
		PKQuestionnaireSession other = (PKQuestionnaireSession) obj;
		if (questionnaire == null) {
			if (other.questionnaire != null) {
				return false;
			}
		} else if (!questionnaire.equals(other.questionnaire)) {
			return false;
		}
		if (session == null) {
			if (other.session != null) {
				return false;
			}
		} else if (!session.equals(other.session)) {
			return false;
		}
		return true;
	}

}
