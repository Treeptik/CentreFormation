package fr.treeptik.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.ResultatDAO;
import fr.treeptik.model.Evaluation;
import fr.treeptik.model.Formation;
import fr.treeptik.model.PKResultat;
import fr.treeptik.model.Question;
import fr.treeptik.model.Resultat;
import fr.treeptik.model.Session;
import fr.treeptik.model.Stagiaire;

@Stateless
public class ResultatEJB {

	@EJB
	private ResultatDAO resultatDAO;

	public void create(Resultat resultat) {
		resultatDAO.create(resultat);
	}

	public List<Resultat> findAll() {
		return resultatDAO.findAll();
	}

	public void delete(Resultat resultat) {
		resultatDAO.delete(resultat);
	}

	public void update(Resultat resultat) {
		resultatDAO.update(resultat);
	}

	public Resultat findById(PKResultat id) {
		return resultatDAO.findById(id);
	}

	public List<Resultat> findAllResultatsOfEval(Evaluation evaluation) {
		return resultatDAO.findAllResultatsOfEval(evaluation);
	}
	
	public List<Resultat> findAllResultatsOfFormation(Formation formation) {
		return resultatDAO.findAllResultatsOfFormation(formation);
	}

	public List<Resultat> findAllResultatsOfSession(Session session) {
		return resultatDAO.findAllResultatsOfSession(session);
	}

	public List<Resultat> findAllResultatsOfStagiaire(Stagiaire stagiaire) {
		return resultatDAO.findAllResultatsOfStagiaire(stagiaire);
	}

	public List<Question> findAllQuestionsOfSession(Session session) {
		return resultatDAO.findAllQuestionsOfSession(session);
	}
}