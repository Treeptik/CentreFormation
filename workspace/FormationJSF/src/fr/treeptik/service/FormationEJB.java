package fr.treeptik.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.FormationDAO;
import fr.treeptik.model.Formation;

@Stateless
public class FormationEJB {

	@EJB
	private FormationDAO formationDAO;
	
	public Formation createUneFormation(Formation formation){
		return formationDAO.createUneFormation(formation);
	}
	
	public List<Formation> findAllFormations(){
		return formationDAO.findALLFormations();
	}
	
	public void update(Formation formation) {
		formationDAO.update(formation);
	}
	public void delete(Formation formation) {
		formationDAO.delete(formation);
	}
}
