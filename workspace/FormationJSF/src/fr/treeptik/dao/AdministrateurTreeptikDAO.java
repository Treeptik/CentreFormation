package fr.treeptik.dao;

import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.treeptik.model.AdministrateurTreeptik;

@Stateless
public class AdministrateurTreeptikDAO {
	
	@PersistenceContext(unitName = "formation")
	private EntityManager em;
	
	public AdministrateurTreeptik createAdministrateurTreeptik(AdministrateurTreeptik administrateurTreeptik){
		em.persist(administrateurTreeptik);
		return administrateurTreeptik;
	}
	
	@SuppressWarnings("unchecked")
	public List<AdministrateurTreeptik> findAllAdministrateurs(){
       Query query =  em.createNamedQuery("findAllAdministrateurs");
       return query.getResultList();
	}
}