package fr.treeptik.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.StagiaireDAO;
import fr.treeptik.model.Stagiaire;

@Stateless
public class StagiaireEJB {

	@EJB
	private StagiaireDAO stagiaireDAO;

	public Stagiaire createUnStagiaire(Stagiaire stagiaire) {
		return stagiaireDAO.createUnStagiaire(stagiaire);
	}

	public List<Stagiaire> findAllStagiaires() {
		return stagiaireDAO.findAllStagiaires();
	}

	public void removeStagiaire(long id) {
		stagiaireDAO.removeStagiaire(id);
	}

	public Stagiaire findStagiaireById(Long id) {
		return stagiaireDAO.findById(id);
	}
	public List<Stagiaire> findStagiairesInSession(Long session_id) {
		return stagiaireDAO.findStagiairesInSession(session_id);
	}
	public void update(Stagiaire stagiaire) {
		stagiaireDAO.update(stagiaire);
	}
	public void delete(Stagiaire stagiaire) {
		stagiaireDAO.delete(stagiaire);
	}
}
