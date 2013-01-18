package fr.treeptik.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import fr.treeptik.model.Evaluation;
import fr.treeptik.model.PKQuestionnaire;
import fr.treeptik.model.Question;
import fr.treeptik.model.Questionnaire;

@Stateless
public class QuestionnaireDAO extends GenericDAO<Questionnaire> {

	public QuestionnaireDAO() {
		super(Questionnaire.class);
	}

	public Questionnaire findById(PKQuestionnaire id) {
		return em.find(Questionnaire.class, id);
	}

	public void addQuestionToEval(Questionnaire questionnaire) {
		em.persist(questionnaire);
	}
	
	public List<Questionnaire> findQuestionnaireByEval(Evaluation evaluation) {
		Query query = em.createNamedQuery("findQuestionnaireByEval");
		query.setParameter("evaluation", evaluation);

		List<Questionnaire> listQuestionnaire = query.getResultList();
		return listQuestionnaire;
	}

	@SuppressWarnings("unchecked")
	public List<Question> findAllQuestionsOfEval(Evaluation evaluation) {
		Query query = em.createNamedQuery("findAllQuestionsOfEval");
		query.setParameter("evaluation", evaluation);

		List<Question> listQuestionsOfEval = query.getResultList();
		return listQuestionsOfEval;
	}

}
