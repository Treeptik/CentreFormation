package fr.treeptik.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.EvaluationDAO;
import fr.treeptik.dao.StagiaireDAO;
import fr.treeptik.model.Evaluation;

@Stateless
public class EvaluationEJB {

	@EJB
	private EvaluationDAO evaluationDAO = new EvaluationDAO();
	@EJB
    private StagiaireDAO stagiaireDAO = new StagiaireDAO();
	
	public Evaluation createUneEvaluation(Evaluation evaluation){
		
//		Stagiaire stagiaire = new Stagiaire();
//		evaluation.setStagiaire( stagiaireDAO.findById(stagiaire.getId()));
//		evaluation.getStagiaire().setId(stagiaire.getId());
//		return evaluationDAO.createUneEvaluation(evaluation);
		
		System.out.println("Evaluation : " + evaluation);
		System.out.println("Stagiaire : " + evaluation.getStagiaire());
		
		System.out.println("ID :" +evaluation.getStagiaire().getId());
		
		evaluation.setStagiaire(stagiaireDAO.findById(evaluation.getStagiaire().getId()));
		return evaluationDAO.createUneEvaluation(evaluation);
	}	
}
