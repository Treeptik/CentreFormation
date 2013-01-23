package fr.treeptik.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.QuestionnaireDAO;
import fr.treeptik.model.Evaluation;
import fr.treeptik.model.PKQuestionnaire;
import fr.treeptik.model.Question;
import fr.treeptik.model.Questionnaire;

@Stateless
public class QuestionnaireEJB {

	@EJB
	private QuestionnaireDAO questionnaireDAO;

	public void addQuestionToEval(Questionnaire questionnaire) {
		questionnaireDAO.addQuestionToEval(questionnaire);
	}

	public List<Questionnaire> findQuestionnaireByEval(Evaluation evaluation) {
		return questionnaireDAO.findQuestionnaireByEval(evaluation);
	}

	public List<Question> findAllQuestionsOfEval(Evaluation evaluation) {
		return questionnaireDAO.findAllQuestionsOfEval(evaluation);
	}

	public void create(Questionnaire questionnaire) {
		questionnaireDAO.create(questionnaire);
	}

	public List<Questionnaire> findAll() {
		return questionnaireDAO.findAll();
	}

	public void delete(Questionnaire questionnaire) {
		questionnaireDAO.delete(questionnaire);
	}

	public void update(Questionnaire questionnaire) {
		questionnaireDAO.update(questionnaire);
	}

	public Questionnaire findById(PKQuestionnaire id) {
		return questionnaireDAO.findById(id);
	}
}