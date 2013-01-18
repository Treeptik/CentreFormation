package fr.treeptik.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Embeddable
public class PKQuestionnaire implements Serializable {

	@ManyToOne(cascade={CascadeType.ALL})
	private Evaluation evaluation;
	@ManyToOne(cascade={CascadeType.ALL})
	private Question question;
	
	public PKQuestionnaire() {
	}

	public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((evaluation == null) ? 0 : evaluation.hashCode());
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
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
		if (!(obj instanceof PKQuestionnaire)) {
			return false;
		}
		PKQuestionnaire other = (PKQuestionnaire) obj;
		if (evaluation == null) {
			if (other.evaluation != null) {
				return false;
			}
		} else if (!evaluation.equals(other.evaluation)) {
			return false;
		}
		if (question == null) {
			if (other.question != null) {
				return false;
			}
		} else if (!question.equals(other.question)) {
			return false;
		}
		return true;
	}
	
}
