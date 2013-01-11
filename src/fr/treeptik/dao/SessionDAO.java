package fr.treeptik.dao;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.Query;

import fr.treeptik.model.Formateur;
import fr.treeptik.model.Formation;
import fr.treeptik.model.Session;

@Stateless
public class SessionDAO extends GenericDAO<Session> {

	public SessionDAO() {
		super(Session.class);
	}

	public Session createUneSession(Session session) {
		ArrayList<Formation> tmpSession = new ArrayList<Formation>();
		for (Formation formation : session.getListFormations()) {
			tmpSession.add(em.find(Formation.class, formation.getId()));
		}
		ArrayList<Formateur> tmpFormateur = new ArrayList<Formateur>();
		for (Formateur formateur : session.getListFormateurs()) {
			tmpFormateur.add(em.find(Formateur.class, formateur.getId()));
		}
		session.setFormations(tmpSession);
		session.setFormateurs(tmpFormateur);

		em.persist(session);
		return session;
	}

	public Session findWithStagiaire(int sessionId) {
		Query query = em
				.createQuery("Select sess From Session sess left join fetch sess.stagiaires s where sess.id=:id");
		query.setParameter("id", sessionId);

		return (Session) query.getSingleResult();

	}

}
