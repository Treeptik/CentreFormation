package fr.treeptik.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.FormateurFormationDAO;
import fr.treeptik.model.Formateur;
import fr.treeptik.model.FormateurFormation;
import fr.treeptik.model.Formation;
import fr.treeptik.model.PKFormateurFormation;

@Stateless
public class FormateurFormationEJB {

	@EJB
	private FormateurFormationDAO formateurFormationDAO;

	public void create(FormateurFormation formateurFormation) {
		formateurFormationDAO.create(formateurFormation);
	}

	public List<FormateurFormation> findAll() {
		return formateurFormationDAO.findAll();
	}

	public void delete(FormateurFormation formateurFormation) {
		formateurFormationDAO.delete(formateurFormation);
	}

	public void update(FormateurFormation formateurFormation) {
		formateurFormationDAO.update(formateurFormation);
	}

	public FormateurFormation findById(PKFormateurFormation id) {
		return formateurFormationDAO.findById(id);
	}
	
	public void addFormateurToFormation(FormateurFormation formateurFormation) {
		formateurFormationDAO.addFormateurToFormation(formateurFormation);
	}
	
	public List<FormateurFormation> findAllFormateurFormationFromFormateur(
			Formateur formateur) {
		return formateurFormationDAO.findAllFormateurFormationFromFormateur(formateur);
	}
	
	public List<FormateurFormation> findAllFormateurFormationFromFormation(
			Formation formation) {
		return formateurFormationDAO.findAllFormateurFormationFromFormation(formation);
	}
	
	public void removeFormateurFromFormation(Formateur formateur,Formation formation) {
		formateurFormationDAO.removeFormateurFromFormation(formateur, formation);
	}
	
	public FormateurFormation findByFormateurAndFormation(Formateur formateur,Formation formation) {
		return formateurFormationDAO.findByFormateurAndFormation(formateur, formation);
	}
	
	public List<Formateur> findAllFormateursOfFormation(Formation formation) {
		return formateurFormationDAO.findAllFormateursOfFormation(formation);
	}
}