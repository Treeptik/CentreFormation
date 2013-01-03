package fr.treeptik.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.treeptik.model.Evaluation;

@Stateless
public class EvaluationDAO {

	@PersistenceContext(unitName = "formation")
	private EntityManager em;
	
	public Evaluation createUneEvaluation(Evaluation evaluation){
		em.persist(evaluation);
		return evaluation;
	}	
}
