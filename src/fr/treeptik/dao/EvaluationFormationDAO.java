package fr.treeptik.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import fr.treeptik.model.Evaluation;
import fr.treeptik.model.EvaluationFormation;
import fr.treeptik.model.Formation;
import fr.treeptik.model.PKEvaluationFormation;

@Stateless
public class EvaluationFormationDAO extends GenericDAO<EvaluationFormation> {

	public EvaluationFormationDAO() {
		super(EvaluationFormation.class);
	}

	public EvaluationFormation findById(PKEvaluationFormation id) {
		return em.find(EvaluationFormation.class, id);
	}
	
	/**
	 * create renommer pour mieux définir l'action
	 **/
	public void addEvalToFormation(EvaluationFormation evaluationFormation) {
		em.persist(evaluationFormation);
	}

	@SuppressWarnings("unchecked")
	public List<Evaluation> findAllEvalsOfFormation(Formation formation) {
		Query query = em.createNamedQuery("findAllEvalsOfFormation");
		query.setParameter("formation", formation);

		List<Evaluation> listEvalsOfFormation = query.getResultList();
		return listEvalsOfFormation;
	}
	
	
}
