package fr.treeptik.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.EvaluationFormationDAO;
import fr.treeptik.model.Evaluation;
import fr.treeptik.model.EvaluationFormation;
import fr.treeptik.model.Formation;
import fr.treeptik.model.PKEvaluationFormation;

@Stateless
public class EvaluationFormationEJB {

	@EJB
	private EvaluationFormationDAO evaluationFormationDAO;

	public void create(EvaluationFormation evaluationFormation) {
		evaluationFormationDAO.create(evaluationFormation);
	}

	public List<EvaluationFormation> findAll() {
		return evaluationFormationDAO.findAll();
	}

	public void delete(EvaluationFormation evaluationFormation) {
		evaluationFormationDAO.delete(evaluationFormation);
	}

	public void update(EvaluationFormation evaluationFormation) {
		evaluationFormationDAO.update(evaluationFormation);
	}

	public EvaluationFormation findById(PKEvaluationFormation id) {
		return evaluationFormationDAO.findById(id);
	}

	public void addEvalToFormation(EvaluationFormation evaluationFormation) {
		evaluationFormationDAO.addEvalToFormation(evaluationFormation);
	}

	
	public List<Evaluation> findAllEvalsOfFormation(Formation formation) {
		return evaluationFormationDAO.findAllEvalsOfFormation(formation);
	}
}
