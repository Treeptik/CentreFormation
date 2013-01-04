package fr.treeptik.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.treeptik.model.Formation;

@Stateless
public class FormationDAO {

	@PersistenceContext(unitName = "formation")
	private EntityManager em;

	public Formation createUneFormation(Formation formation) {
		em.persist(formation);
		return formation;
	}

	@SuppressWarnings("unchecked")
	public List<Formation> findALLFormations() {
		Query query = em.createNamedQuery("findALLFormation");
		return query.getResultList();
	}

	public void delete(Formation formation) {
		formation = em.merge(formation);
		em.remove(formation);
	}

	public Formation update(Formation formation) {
		return em.merge(formation);
	}
}
