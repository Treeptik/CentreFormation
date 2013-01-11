package fr.treeptik.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.lang3.RandomStringUtils;

import fr.treeptik.dao.StagiaireDAO;
import fr.treeptik.model.Stagiaire;

@Stateless
public class StagiaireEJB {

	@EJB
	private StagiaireDAO stagiaireDAO;

	public void create(Stagiaire stagiaire) {
		stagiaire.setPassword(stagiaireDAO.generatePassword());
		stagiaire.setRole("USER");
		stagiaireDAO.create(stagiaire);
	}

	public List<Stagiaire> findAll() {
		return stagiaireDAO.findAll();
	}

	public void delete(Stagiaire stagiaire) {
		stagiaireDAO.delete(stagiaire);
	}

	public void update(Stagiaire stagiaire) {
		stagiaireDAO.update(stagiaire);
	}
	
	public Stagiaire findById(long id) {
		return stagiaireDAO.findById(id);
	}

	public List<Stagiaire> findStagiairesInSession(long session_id) {
		return stagiaireDAO.findStagiairesInSession(session_id);
	}
	
	public String generatePassword() {
		return stagiaireDAO.generatePassword();
	}
}
