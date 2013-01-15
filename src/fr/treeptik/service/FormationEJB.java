package fr.treeptik.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.FormationDAO;
import fr.treeptik.model.Formateur;
import fr.treeptik.model.Formation;

@Stateless
public class FormationEJB {

	@EJB
	private FormationDAO formationDAO;
	
	public void create(Formation formation){
		formationDAO.create(formation);
	}
	
	public List<Formation> findAll(){
		return formationDAO.findAll();
	}
	
	public void delete(Formation formation){
		formationDAO.delete(formation);
	}
	
	public void update(Formation formation) {
		formationDAO.update(formation);
	}
	
	public Formation findById(int id) {
		return formationDAO.findById(id);
	}
}