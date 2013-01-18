package fr.treeptik.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.QuestionnaireSessionDAO;
import fr.treeptik.model.Evaluation;
import fr.treeptik.model.PKQuestionnaireSession;
import fr.treeptik.model.Questionnaire;
import fr.treeptik.model.QuestionnaireSession;
import fr.treeptik.model.Session;

@Stateless
public class QuestionnaireSessionEJB {

	@EJB
	private QuestionnaireSessionDAO questionnaireSessionDAO;

	public void create(QuestionnaireSession questionnaireSession) {
		questionnaireSessionDAO.create(questionnaireSession);
	}

	public List<QuestionnaireSession> findAll() {
		return questionnaireSessionDAO.findAll();
	}

	public void delete(QuestionnaireSession questionnaireSession) {
		questionnaireSessionDAO.delete(questionnaireSession);
	}

	public void update(QuestionnaireSession questionnaireSession) {
		questionnaireSessionDAO.update(questionnaireSession);
	}

	public QuestionnaireSession findById(PKQuestionnaireSession id) {
		return questionnaireSessionDAO.findById(id);
	}

	public void addEvalToSession(QuestionnaireSession questionnaireSession) {
		questionnaireSessionDAO.addEvalToSession(questionnaireSession);
	}

	public List<Questionnaire> findAllQuestionnairesOfSession(Session session) {
		return questionnaireSessionDAO.findAllQuestionnairesOfSession(session);
	}
	
	public List<Evaluation> findAllEvalsOfSession(Session session) {
		return questionnaireSessionDAO.findAllEvalsOfSession(session);
	}
}
