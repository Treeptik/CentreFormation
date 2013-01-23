package fr.treeptik.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@SuppressWarnings("serial")
@Entity
@NamedQueries({ @NamedQuery(name = "findAllQuestions", query = "select q from Question q") })
public class Question implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String libelle;
	private char question4Choix; 
	private char questionFermee;
	private char avecCommentaire;

	public Question() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public char getQuestion4Choix() {
		return question4Choix;
	}

	public void setQuestion4Choix(char question4Choix) {
		this.question4Choix = question4Choix;
	}

	public char getQuestionFermee() {
		return questionFermee;
	}

	public void setQuestionFermee(char questionFermee) {
		this.questionFermee = questionFermee;
	}

	public char getAvecCommentaire() {
		return avecCommentaire;
	}

	public void setAvecCommentaire(char avecCommentaire) {
		this.avecCommentaire = avecCommentaire;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + avecCommentaire;
		result = prime * result + id;
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		result = prime * result + question4Choix;
		result = prime * result + questionFermee;
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
		if (!(obj instanceof Question)) {
			return false;
		}
		Question other = (Question) obj;
		if (avecCommentaire != other.avecCommentaire) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (libelle == null) {
			if (other.libelle != null) {
				return false;
			}
		} else if (!libelle.equals(other.libelle)) {
			return false;
		}
		if (question4Choix != other.question4Choix) {
			return false;
		}
		if (questionFermee != other.questionFermee) {
			return false;
		}
		return true;
	}

}
