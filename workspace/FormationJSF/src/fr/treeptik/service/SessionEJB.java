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
	
	public Session createSession(Session session){
		return sessionDAO.createUneSession(session);
	}
	
	public List<Session> findAllSession(){
		return sessionDAO.findAllSession();
	}
	
	public Session findWithStagiaire(long sessionId){
		return sessionDAO.findWithStagiaire(sessionId);
	}
	
	public void addStagiaire(long sessionId, long stagiaireId){
		
		Session session = sessionDAO.findWithStagiaire(sessionId);
		Stagiaire stagiaire = stagiaireDAO.findById(stagiaireId);
		
		session.getStagiaires().add(stagiaire);
		
	}
	
	public void update(Session session) {
		sessionDAO.update(session);
	}
	public void delete(Session session) {
		sessionDAO.delete(session);
	}
	
}
