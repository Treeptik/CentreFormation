package fr.treeptik.dao;

import javax.ejb.Stateless;

import fr.treeptik.model.PKStagiaireSession;
import fr.treeptik.model.Evaluation;
import fr.treeptik.model.Formation;
import fr.treeptik.model.Session;
import fr.treeptik.model.Stagiaire;

@Stateless
public class EvaluationDAO extends GenericDAO<Evaluation> {

	public EvaluationDAO() {
		super(Evaluation.class);
	}
	
	public void create(Evaluation evaluation) {
		em.persist(evaluation);
	}
	/*
	public Evaluation findById(Stagiaire stagiaire, Session session,
	Formation formation){
		return em.find(PKStagiaireSession);
	}
	*/
}
