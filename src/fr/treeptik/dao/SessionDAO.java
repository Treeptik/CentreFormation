package fr.treeptik.dao;

import javax.ejb.Stateless;

import fr.treeptik.model.Session;

@Stateless
public class SessionDAO extends GenericDAO<Session> {

	public SessionDAO() {
		super(Session.class);
	}
/*
	public List<Formation> findFormationsOfSession(int sessionId) {
		TypedQuery<Formation> query = em.createNamedQuery(
				"findFormationsOfSession", Formation.class);
		query.setParameter("id", sessionId);
		List<Formation> listFormations = query.getResultList();
		return listFormations;
	}*/
}
