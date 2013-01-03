package fr.treeptik.dao;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.treeptik.model.Stagiaire;

@Stateless
public class StagiaireDAO {
	
	@PersistenceContext(unitName = "formation")
	private EntityManager em;
	
	public Stagiaire createUnStagiaire(Stagiaire stagiaire){
		em.persist(stagiaire);
		return stagiaire;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Stagiaire> findAllStagiaires(){
		Query query = em.createNamedQuery("findALLStagiaires");
		return query.getResultList();
	 
	}
	
	@SuppressWarnings("unchecked")
	public List<Stagiaire> 	findStagiairesInSession(Long session_id){
		Query query = em.createNamedQuery("findStagiairesInSession");
		query.setParameter(1, session_id);
		return query.getResultList();
	 
	}
	public Stagiaire findById(long id){
		Stagiaire stagiaire = em.find(Stagiaire.class, id);
		return  stagiaire;
	}
	
	public void removeStagiaire(long id){
		Stagiaire stagiaire = em.find(Stagiaire.class, id);
		em.remove(stagiaire);
	}
	
	public void delete(Stagiaire stagiaire) {
		stagiaire = em.merge(stagiaire);
		em.remove(stagiaire);
	}
	
	public Stagiaire update(Stagiaire stagiaire) {
		return em.merge(stagiaire);
	}
}
