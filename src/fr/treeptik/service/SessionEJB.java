package fr.treeptik.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.SessionDAO;
import fr.treeptik.model.Session;

@Stateless
public class SessionEJB {

	@EJB
	private SessionDAO sessionDAO;

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
/*	
	public List<Formation> findFormationsOfSession(int sessionId) {
		return sessionDAO.findFormationsOfSession(sessionId);
	}
*/	
}
