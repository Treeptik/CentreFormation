package fr.treeptik.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@SuppressWarnings("serial")
@Entity
@NamedQueries({
	@NamedQuery(name = "findAllResultatsOfEval", query= "select r from Resultat r where r.id.questionnaire.id.evaluation= :evaluation ")
	,@NamedQuery(name = "findAllResultatsOfSession", query = "select r from Resultat r where r.id.stagiaireSession.id.session = :session")
	,@NamedQuery(name = "findAllResultatsOfStagiaire", query = "select r from Resultat r where r.id.stagiaireSession.id.stagiaire = :stagiaire")
	,@NamedQuery(name = "findAllQuestionsOfSession", query = "select r.id.questionnaire.id.question from Resultat r where r.id.stagiaireSession.id.session = :session")	
})
public class Resultat implements Serializable {

	@EmbeddedId
	private PKResultat id;
	private int note;
	
	public Resultat() {
	}

	public PKResultat getId() {
		return id;
	}

	public void setId(PKResultat id) {
		this.id = id;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + note;
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
		if (!(obj instanceof Resultat)) {
			return false;
		}
		Resultat other = (Resultat) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (note != other.note) {
			return false;
		}
		return true;
	}

}
