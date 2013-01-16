package fr.treeptik.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@SuppressWarnings("serial")
@Entity
@NamedQueries({
	@NamedQuery(name = "findAllQuestionsOfEval", query = "select n.id.question from Note n where n.id.evaluation= :evaluationId ") })
public class Note implements Serializable {

	@EmbeddedId
	private PKEvaluationQuestion id;
	private int note;
	private String commentaire;
	
	public Note() {
	}

	public PKEvaluationQuestion getId() {
		return id;
	}

	public void setId(PKEvaluationQuestion id) {
		this.id = id;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((commentaire == null) ? 0 : commentaire.hashCode());
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
		if (!(obj instanceof Note)) {
			return false;
		}
		Note other = (Note) obj;
		if (commentaire == null) {
			if (other.commentaire != null) {
				return false;
			}
		} else if (!commentaire.equals(other.commentaire)) {
			return false;
		}
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
