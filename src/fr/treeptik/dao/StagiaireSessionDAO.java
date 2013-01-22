package fr.treeptik.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import fr.treeptik.model.PKStagiaireSession;
import fr.treeptik.model.Session;
import fr.treeptik.model.Stagiaire;
import fr.treeptik.model.StagiaireSession;

@Stateless
public class StagiaireSessionDAO extends GenericDAO<StagiaireSession> {

	public StagiaireSessionDAO() {
		super(StagiaireSession.class);
	}

	public StagiaireSession findById(PKStagiaireSession id) {
		return em.find(StagiaireSession.class, id);
	}

	public void addStagiaireToSession(StagiaireSession stagiaireSession) {
		em.persist(stagiaireSession);
	}

	@SuppressWarnings("unchecked")
	public List<Session> findAllSessionsOfStagiaire(Stagiaire stagiaire) {
		Query query = em.createNamedQuery("findAllSessionsOfStagiaire");
		query.setParameter("stagiaire", stagiaire);

		List<Session> listSessionsOfStagiaire = query.getResultList();
		return listSessionsOfStagiaire;		
	}
	

	public List<StagiaireSession> findAllStagiaireSessionsOfSession(Session session) {
		Query query = em.createNamedQuery("findAllStagiaireSessionsOfSession");
		query.setParameter("session", session);

		@SuppressWarnings("unchecked")
		List<StagiaireSession> listStagiaireSession = query.getResultList();
		return listStagiaireSession;		
	}
	
	public void removeStagiaireFromSession(Stagiaire stagiaire,Session session) {
		Query query = em.createNamedQuery("removeStagiaireFromSession");
		query.setParameter("session", session);
		query.setParameter("stagiaire", stagiaire);
		query.executeUpdate();
	}
	
	
	public StagiaireSession findByStagiaireAndSession(Stagiaire stagiaire,Session session) {
		Query query = em.createNamedQuery("findByStagiaireAndSession");
		query.setParameter("session", session);
		query.setParameter("stagiaire", stagiaire);
		
		StagiaireSession stagiaireSession = (StagiaireSession) query.getSingleResult();
		return stagiaireSession;		
	}
	
	@SuppressWarnings("unchecked")
	public List<Stagiaire> findAllStagiairesOfSession(Session session) {
		Query query = em.createNamedQuery("findAllStagiairesOfSession");
		query.setParameter("session", session);

		List<Stagiaire> listStagiairesOfSession = query.getResultList();
		return listStagiairesOfSession;
	}

}
