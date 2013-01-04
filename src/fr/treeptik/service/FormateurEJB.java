package fr.treeptik.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.FormateurDAO;
import fr.treeptik.model.Formateur;

@Stateless
public class FormateurEJB {
	@EJB
	private FormateurDAO formateurDAO;
	
	public Formateur createUnFormateur(Formateur formateur){
		return formateurDAO.createUnFormateur(formateur);
	}
	
	public List<Formateur> findAllFormateurs(){
		return formateurDAO.findAllFormateurs();
		
	}
	
	public void update(Formateur formateur) {
		formateurDAO.update(formateur);
	}
	public void delete(Formateur formateur) {
		formateurDAO.delete(formateur);
	}
}

