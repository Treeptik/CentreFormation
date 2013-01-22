package fr.treeptik.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Embeddable
public class PKEvaluationFormation implements Serializable {

	@ManyToOne(cascade={CascadeType.ALL})
	private Evaluation evaluation;
	@ManyToOne(cascade={CascadeType.ALL})
	private Formation formation;

	public PKEvaluationFormation() {
	}

	public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((evaluation == null) ? 0 : evaluation.hashCode());
		result = prime * result
				+ ((formation == null) ? 0 : formation.hashCode());
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
		if (!(obj instanceof PKEvaluationFormation)) {
			return false;
		}
		PKEvaluationFormation other = (PKEvaluationFormation) obj;
		if (evaluation == null) {
			if (other.evaluation != null) {
				return false;
			}
		} else if (!evaluation.equals(other.evaluation)) {
			return false;
		}
		if (formation == null) {
			if (other.formation != null) {
				return false;
			}
		} else if (!formation.equals(other.formation)) {
			return false;
		}
		return true;
	}

}
