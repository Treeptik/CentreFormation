package fr.treeptik.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.StagiaireDAO;
import fr.treeptik.model.Stagiaire;
import fr.treeptik.model.User;

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
		stagiaire.setRole("USER");
		stagiaireDAO.update(stagiaire);
	}
	
	public Stagiaire findById(int id) {
		return stagiaireDAO.findById(id);
	}
	
	public Stagiaire findStagiaireByEmail(String email) {
		return stagiaireDAO.findStagiaireByEmail(email);
	}
	

	public List<Stagiaire> findStagiairesInSession(long session_id) {
		return stagiaireDAO.findStagiairesInSession(session_id);
	}
	
	public String generatePassword() {
		return stagiaireDAO.generatePassword();
	}
}
