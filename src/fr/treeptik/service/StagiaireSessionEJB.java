package fr.treeptik.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.StagiaireSessionDAO;
import fr.treeptik.model.PKStagiaireSession;
import fr.treeptik.model.Session;
import fr.treeptik.model.Stagiaire;
import fr.treeptik.model.StagiaireSession;

@Stateless
public class StagiaireSessionEJB {

	@EJB
	private StagiaireSessionDAO stagiaireSessionDAO;

	public void create(StagiaireSession stagiaireSession) {
		stagiaireSessionDAO.create(stagiaireSession);
	}

	public List<StagiaireSession> findAll() {
		return stagiaireSessionDAO.findAll();
	}

	public void delete(StagiaireSession stagiaireSession) {
		stagiaireSessionDAO.delete(stagiaireSession);
	}

	public void update(StagiaireSession stagiaireSession) {
		stagiaireSessionDAO.update(stagiaireSession);
	}

	public StagiaireSession findById(PKStagiaireSession id) {
		return stagiaireSessionDAO.findById(id);
	}


	public void addStagiaireToSession(StagiaireSession stagiaireSession) {
		stagiaireSessionDAO.addStagiaireToSession(stagiaireSession);
	}
	
	public List<Session> findAllSessionsOfStagiaire(Stagiaire stagiaire) {
		return stagiaireSessionDAO.findAllSessionsOfStagiaire(stagiaire);
	}
	
	public void removeStagiaireFromSession(Stagiaire stagiaire,Session session) {
		stagiaireSessionDAO.removeStagiaireFromSession(stagiaire, session);
	}
	
	public StagiaireSession findByStagiaireAndSession(Stagiaire stagiaire,Session session) {
		return stagiaireSessionDAO.findByStagiaireAndSession(stagiaire, session);
	}
	
	public List<StagiaireSession> findAllStagiaireSessionsOfSession(Session session) {
		return stagiaireSessionDAO.findAllStagiaireSessionsOfSession(session);
	}
	
	public List<Stagiaire> findAllStagiairesOfSession(Session session) {
		return stagiaireSessionDAO.findAllStagiairesOfSession(session);
	}

}