package fr.treeptik.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.commons.lang3.RandomStringUtils;

import fr.treeptik.model.Stagiaire;

@Stateless
public class StagiaireDAO extends GenericDAO<Stagiaire> {

	public StagiaireDAO() {
		super(Stagiaire.class);
	}

	@SuppressWarnings("unchecked")
	public List<Stagiaire> findStagiairesInSession(long session_id) {
		Query query = em.createNamedQuery("findStagiairesInSession");
		query.setParameter(1, session_id);
		return query.getResultList();
	}
	
	public String generatePassword() {
		String generatedPassword = RandomStringUtils.randomAlphanumeric(10);
		return generatedPassword;
	}
}
