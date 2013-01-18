package fr.treeptik.dao;

import javax.ejb.Stateless;

import fr.treeptik.model.Evaluation;

@Stateless
public class EvaluationDAO extends GenericDAO<Evaluation> {

	public EvaluationDAO() {
		super(Evaluation.class);
	}
}
