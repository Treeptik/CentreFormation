package fr.treeptik.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.treeptik.model.Formateur;
import fr.treeptik.model.Formation;
import fr.treeptik.model.Session;

@Stateless
public class SessionDAO {

	@PersistenceContext(unitName = "formation")
	private EntityManager em;

	public Session createUneSession(Session session) {

		ArrayList<Formation> tmpSession = new ArrayList<Formation>();
		for (Formation formation : session.getFormations()) {
			tmpSession.add(em.find(Formation.class, formation.getId()));

		}

		ArrayList<Formateur> tmpFormateur = new ArrayList<Formateur>();
		for (Formateur formateur : session.getFormateurs()) {
			tmpFormateur.add(em.find(Formateur.class, formateur.getId()));

		}

		session.setFormations(tmpSession);
		session.setFormateurs(tmpFormateur);

		em.persist(session);
		return session;
	}

	@SuppressWarnings("unchecked")
	public List<Session> findAllSession() {

		Query query = em.createQuery("Select s from Session s ");
		return query.getResultList();

	}

	public Session findWithStagiaire(long sessionId) {

		Query query = em
				.createQuery("Select sess From Session sess left join fetch sess.stagiaires s where sess.id=:id");
		query.setParameter("id", sessionId);

		return (Session) query.getSingleResult();

	}

	public void delete(Session session) {
		session = em.merge(session);
		em.remove(session);
	}

	public Session update(Session session) {
		return em.merge(session);
	}

}
