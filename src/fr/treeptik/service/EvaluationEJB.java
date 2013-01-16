package fr.treeptik.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.EvaluationDAO;
import fr.treeptik.dao.StagiaireDAO;
import fr.treeptik.model.Evaluation;
import fr.treeptik.model.Formation;
import fr.treeptik.model.Session;
import fr.treeptik.model.Stagiaire;

@Stateless
public class EvaluationEJB {

	@EJB
	private EvaluationDAO evaluationDAO = new EvaluationDAO();
	@EJB
    private StagiaireDAO stagiaireDAO = new StagiaireDAO();

	public void create(Evaluation evaluation){
		evaluationDAO.create(evaluation);
	}
	
	public List<Evaluation> findAll(){
		return evaluationDAO.findAll();
	}
	
	public void delete(Evaluation evaluation){
		evaluationDAO.delete(evaluation);
	}
	
	public void update(Evaluation evaluation) {
		evaluationDAO.update(evaluation);
	}
	/*
	public Evaluation findById(Stagiaire stagiaire, Session session,
								Formation formation) {
		return formateurDAO.findById(Stagiaire;
	}
	*/
}