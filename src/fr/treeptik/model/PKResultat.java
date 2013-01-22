package fr.treeptik.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Embeddable
public class PKResultat implements Serializable {

	@ManyToOne
	private Question question;
	@ManyToOne
	private StagiaireSession stagiaireSession;

	public PKResultat() {
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public StagiaireSession getStagiaireSession() {
		return stagiaireSession;
	}

	public void setStagiaireSession(StagiaireSession stagiaireSession) {
		this.stagiaireSession = stagiaireSession;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
		result = prime
				* result
				+ ((stagiaireSession == null) ? 0 : stagiaireSession.hashCode());
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
		if (!(obj instanceof PKResultat)) {
			return false;
		}
		PKResultat other = (PKResultat) obj;
		if (question == null) {
			if (other.question != null) {
				return false;
			}
		} else if (!question.equals(other.question)) {
			return false;
		}
		if (stagiaireSession == null) {
			if (other.stagiaireSession != null) {
				return false;
			}
		} else if (!stagiaireSession.equals(other.stagiaireSession)) {
			return false;
		}
		return true;
	}
}