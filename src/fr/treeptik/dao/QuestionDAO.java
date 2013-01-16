package fr.treeptik.dao;

import javax.ejb.Stateless;

import fr.treeptik.model.Question;

@Stateless
public class QuestionDAO extends GenericDAO<Question> {
	

	public QuestionDAO() {
		super(Question.class);
	}

}
