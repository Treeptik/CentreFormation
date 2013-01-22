package fr.treeptik.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.FormationSessionDAO;
import fr.treeptik.model.Formation;
import fr.treeptik.model.FormationSession;
import fr.treeptik.model.PKFormationSession;
import fr.treeptik.model.Session;

@Stateless
public class FormationSessionEJB {

	@EJB
	private FormationSessionDAO formationSessionDAO;

	public void create(FormationSession formationSession) {
		formationSessionDAO.create(formationSession);
	}

	public List<FormationSession> findAll() {
		return formationSessionDAO.findAll();
	}

	public void delete(FormationSession formationSession) {
		formationSessionDAO.delete(formationSession);
	}

	public void update(FormationSession formationSession) {
		formationSessionDAO.update(formationSession);
	}

	public FormationSession findById(PKFormationSession id) {
		return formationSessionDAO.findById(id);
	}
	
	public void addFormationToSession(FormationSession formationSession) {
		formationSessionDAO.addFormationToSession(formationSession);
	}
	
	public FormationSession findByFormationAndSession(Formation formation,Session session) {
		return formationSessionDAO.findByFormationAndSession(formation, session);
	}
	
	public List<Formation> findAllFormationsOfSession(Session session) {
		return formationSessionDAO.findAllFormationsOfSession(session);
	}
}