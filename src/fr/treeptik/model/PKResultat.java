package fr.treeptik.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Embeddable
public class PKResultat implements Serializable {

	@ManyToOne
	private Questionnaire questionnaire;
	@ManyToOne
	private StagiaireSession stagiaireSession;
	@ManyToOne
	private FormateurFormation formateurFormation;

	public PKResultat() {
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public StagiaireSession getStagiaireSession() {
		return stagiaireSession;
	}

	public void setStagiaireSession(StagiaireSession stagiaireSession) {
		this.stagiaireSession = stagiaireSession;
	}

	public FormateurFormation getFormateurFormation() {
		return formateurFormation;
	}

	public void setFormateurFormation(FormateurFormation formateurFormation) {
		this.formateurFormation = formateurFormation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((formateurFormation == null) ? 0 : formateurFormation
						.hashCode());
		result = prime * result
				+ ((questionnaire == null) ? 0 : questionnaire.hashCode());
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
		if (formateurFormation == null) {
			if (other.formateurFormation != null) {
				return false;
			}
		} else if (!formateurFormation.equals(other.formateurFormation)) {
			return false;
		}
		if (questionnaire == null) {
			if (other.questionnaire != null) {
				return false;
			}
		} else if (!questionnaire.equals(other.questionnaire)) {
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