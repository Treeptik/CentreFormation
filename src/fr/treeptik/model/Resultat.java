package fr.treeptik.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import fr.treeptik.model.Formation;
import fr.treeptik.model.Formateur;

@SuppressWarnings("serial")
@Entity
@NamedQueries({
	@NamedQuery(name = "findAllResultatsOfSession", query = "select r from Resultat r where r.id.stagiaireSession.id.session = :session")
	,@NamedQuery(name = "findAllResultatsOfStagiaire", query = "select r from Resultat r where r.id.stagiaireSession.id.stagiaire = :stagiaire")
	,@NamedQuery(name = "findAllQuestionsOfSession", query = "select r.id.question from Resultat r where r.id.stagiaireSession.id.session = :session")	
})
public class Resultat implements Serializable {

	@EmbeddedId
	private PKResultat id;
	private int note;
	private String commentaire;
	@OneToMany
	private List<Formateur> listFormateursEvalues;
	@OneToMany
	private List<Formation> listFormationsEvaluees;
	
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

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public List<Formateur> getListFormateursEvalues() {
		return listFormateursEvalues;
	}

	public void setListFormateursEvalues(List<Formateur> listFormateursEvalues) {
		this.listFormateursEvalues = listFormateursEvalues;
	}

	public List<Formation> getListFormationsEvaluees() {
		return listFormationsEvaluees;
	}

	public void setListFormationsEvaluees(List<Formation> listFormationsEvaluees) {
		this.listFormationsEvaluees = listFormationsEvaluees;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((commentaire == null) ? 0 : commentaire.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((listFormateursEvalues == null) ? 0 : listFormateursEvalues
						.hashCode());
		result = prime
				* result
				+ ((listFormationsEvaluees == null) ? 0
						: listFormationsEvaluees.hashCode());
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
		if (listFormateursEvalues == null) {
			if (other.listFormateursEvalues != null) {
				return false;
			}
		} else if (!listFormateursEvalues.equals(other.listFormateursEvalues)) {
			return false;
		}
		if (listFormationsEvaluees == null) {
			if (other.listFormationsEvaluees != null) {
				return false;
			}
		} else if (!listFormationsEvaluees.equals(other.listFormationsEvaluees)) {
			return false;
		}
		if (note != other.note) {
			return false;
		}
		return true;
	}

}
