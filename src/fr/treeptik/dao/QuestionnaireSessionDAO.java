package fr.treeptik.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import fr.treeptik.model.Evaluation;
import fr.treeptik.model.PKQuestionnaireSession;
import fr.treeptik.model.Questionnaire;
import fr.treeptik.model.QuestionnaireSession;
import fr.treeptik.model.Session;

@Stateless
public class QuestionnaireSessionDAO extends GenericDAO<QuestionnaireSession> {

	public QuestionnaireSessionDAO() {
		super(QuestionnaireSession.class);
	}

	public QuestionnaireSession findById(PKQuestionnaireSession id) {
		return em.find(QuestionnaireSession.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Questionnaire> findAllQuestionnairesOfSession(
			Session session) {
		Query query = em.createNamedQuery("findAllQuestionnairesOfSession");
		query.setParameter("session", session);

		List<Questionnaire> listQuestionnairesOfSession = query.getResultList();
		return listQuestionnairesOfSession;
	}

	public void addEvalToSession(QuestionnaireSession questionnaireSession) {
		em.persist(questionnaireSession);
	}

	@SuppressWarnings("unchecked")
	public List<Evaluation> findAllEvalsOfSession(Session session) {
		Query query = em.createNamedQuery("findAllEvalsOfSession");
		query.setParameter("session", session);

		List<Evaluation> listEvalsOfSession = query.getResultList();
		return listEvalsOfSession;
	}
	
	
}
