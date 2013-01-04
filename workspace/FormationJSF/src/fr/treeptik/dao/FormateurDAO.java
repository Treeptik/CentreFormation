package fr.treeptik.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.treeptik.model.Formateur;


@Stateless
public class FormateurDAO {
	@PersistenceContext(unitName = "formation")
	private EntityManager em;
	
	
	public Formateur createUnFormateur(Formateur formateur){
		em.persist(formateur);
		return formateur;
	}
	
	@SuppressWarnings("unchecked")
	public List<Formateur> findAllFormateurs(){
		Query query = em.createNamedQuery("findALLFormateurs");
		return query.getResultList();
	}
	
	public void delete(Formateur formateur) {
		formateur = em.merge(formateur);
		em.remove(formateur);
	}
	
	public Formateur update(Formateur formateur) {
		return em.merge(formateur);
	}
}
