package fr.treeptik.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.AdministrateurDAO;
import fr.treeptik.model.Administrateur;

@Stateless
public class AdministrateurEJB {
	
	@EJB
	private AdministrateurDAO administrateurDAO;
	
	public void create(Administrateur administrateur){
		administrateur.setRole("ADMIN");
		administrateurDAO.create(administrateur);
	}
	
	public List<Administrateur> findAll(){
		return administrateurDAO.findAll();
	}
	
	public void delete(Administrateur administrateur){
		administrateurDAO.delete(administrateur);
	}
	
	public void update(Administrateur administrateur) {
		administrateur.setRole("ADMIN");
		administrateurDAO.update(administrateur);
	}

	public Administrateur findById(int id) {
		return administrateurDAO.findById(id);
	}
}