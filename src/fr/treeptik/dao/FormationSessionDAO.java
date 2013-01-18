package fr.treeptik.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import fr.treeptik.model.Formation;
import fr.treeptik.model.FormationSession;
import fr.treeptik.model.PKFormationSession;
import fr.treeptik.model.Session;

@Stateless
public class FormationSessionDAO extends GenericDAO<FormationSession> {

	public FormationSessionDAO() {
		super(FormationSession.class);
	}

	public FormationSession findById(PKFormationSession id) {
		return em.find(FormationSession.class, id);
	}

	public void addFormationToSession(FormationSession formationSession) {
		em.persist(formationSession);
	}

	@SuppressWarnings("unchecked")
	public List<Formation> findAllFormationsOfSession(Session session) {
		Query query = em.createNamedQuery("findAllFormationsOfSession");
		query.setParameter("session", session);

		List<Formation> listFormationOfSession = query.getResultList();
		return listFormationOfSession;
	}
}
