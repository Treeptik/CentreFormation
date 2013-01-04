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
	
	public void create(Formateur formateur){
		formateurDAO.create(formateur);
	}
	
	public List<Formateur> findAll(){
		return formateurDAO.findAll();
	}
	
	public void delete(Formateur formateur){
		formateurDAO.delete(formateur);
	}
	
	public void update(Formateur formateur) {
		formateurDAO.update(formateur);
	}
}