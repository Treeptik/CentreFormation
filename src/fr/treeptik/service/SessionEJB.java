package fr.treeptik.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.SessionDAO;
import fr.treeptik.dao.StagiaireDAO;
import fr.treeptik.model.Session;
import fr.treeptik.model.Stagiaire;

@Stateless
public class SessionEJB {

	@EJB
	private SessionDAO sessionDAO;
	@EJB
	private StagiaireDAO stagiaireDAO;

	public void create(Session session) {
		sessionDAO.create(session);
	}

	public List<Session> findAll() {
		return sessionDAO.findAll();
	}

	public void delete(Session session) {
		sessionDAO.delete(session);
	}

	public void update(Session session) {
		sessionDAO.update(session);
	}
	
	public void refresh(Session session){
		sessionDAO.refresh(session);
	}

	public Session findById(int id) {
		return sessionDAO.findById(id);
	}

	public Session findWithStagiaire(int sessionId) {
		return sessionDAO.findWithStagiaire(sessionId);
	}

	public void addStagiaire(int sessionId, int stagiaireId) {
		Session session = sessionDAO.findWithStagiaire(sessionId);
		Stagiaire stagiaire = stagiaireDAO.findById(stagiaireId);
		session.getListStagiaires().add(stagiaire);
	}

}
