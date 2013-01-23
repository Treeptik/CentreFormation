package fr.treeptik.dao;

import javax.ejb.Stateless;

import fr.treeptik.model.Question;

@Stateless
public class QuestionDAO extends GenericDAO<Question> {

	public QuestionDAO() {
		super(Question.class);
	}

	public Boolean checkAvecCommentaire(Question question) {
		if (question.getAvecCommentaire() == 'y') {
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean checkQuestion4Choix(Question question) {
		if (question.getQuestion4Choix() == 'y') {
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean checkQuestionFermee(Question question) {
		if (question.getQuestionFermee() == 'y') {
			return true;
		} else {
			return false;
		}
	}
}
