package fr.treeptik.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import fr.treeptik.model.Formateur;
import fr.treeptik.model.FormateurFormation;
import fr.treeptik.model.Formation;
import fr.treeptik.model.PKFormateurFormation;
import fr.treeptik.service.FormateurEJB;
import fr.treeptik.service.FormateurFormationEJB;
import fr.treeptik.service.FormationEJB;

@ManagedBean
@RequestScoped
public class FormationController {

	// *********ENTITE******************************************************
	private Formation formation = new Formation();
	private Formateur formateur = new Formateur();
	private FormateurFormation formateurFormation = new FormateurFormation();
	private PKFormateurFormation pKFormateurFormation = new PKFormateurFormation();

	// *********EJB*********************************************************
	@EJB
	private FormationEJB formationEJB;
	@EJB
	private FormateurEJB formateurEJB;
	@EJB
	private FormateurFormationEJB formateurFormationEJB;

	// **********LISTES*****************************************************
	private List<Formation> listFormations = new ArrayList<Formation>();
	private List<SelectItem> selectFormation;

	// **********DATAMODEL**************************************************
	@SuppressWarnings("rawtypes")
	private DataModel lDMFormations;
	@SuppressWarnings("rawtypes")
	private DataModel lDMFormateurs;
	@SuppressWarnings("rawtypes")
	private DataModel lDMFormateursOfFormation;

	public String doCreate() {
		formationEJB.create(formation);
		listFormations = formationEJB.findAll();
		return "messageFormationCreee";
	}

	@SuppressWarnings("rawtypes")
	public String doDelete() {
		Formation formation = (Formation) lDMFormations.getRowData();
		formationEJB.delete(formation);
		lDMFormations = new ListDataModel();
		lDMFormations.setWrappedData(formationEJB.findAll());
		return "listFormations";
	}

	@SuppressWarnings("rawtypes")
	public String doUpdate() {
		formationEJB.update(formation);
		lDMFormations = new ListDataModel();
		lDMFormations.setWrappedData(formationEJB.findAll());
		return "messageFormationUpdate";
	}

	public String doFindAll() {
		listFormations = formationEJB.findAll();
		return "listFormations";
	}

	public String doNew() {
		return "createFormation";
	}

	public String doAddFormateurToFormation() {
		formateur = (Formateur) lDMFormateurs.getRowData();
		pKFormateurFormation.setFormation(formation);
		pKFormateurFormation.setFormateur(formateur);
		formateurFormation.setId(pKFormateurFormation);
		formateurFormationEJB.addFormateurToFormation(formateurFormation);
		return "listSessions";
	}

	public String doListFormateursOfFormation() {
		formation = (Formation) lDMFormations.getRowData();
		getlDMFormateursOfFormation();
		return "listFormateursOfFormation";
	}

	public String doSelectUpdate() {
		formation = (Formation) lDMFormations.getRowData();
		return "updateFormation";
	}

	public String doSelectAddFormateur() {
		formation = (Formation) lDMFormations.getRowData();
		return "listFormateursToAdd";
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation Formation) {
		this.formation = Formation;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	public List<Formation> getListFormations() {
		return listFormations;
	}

	public void setListFormations(List<Formation> listFormations) {
		this.listFormations = listFormations;
	}

	public List<SelectItem> getSelectFormation() {
		List<Formation> formations = formationEJB.findAll();
		selectFormation = new ArrayList<SelectItem>();
		for (Formation formation : formations) {
			selectFormation.add(new SelectItem(formation.getId(), formation
					.getNom()));
		}
		return selectFormation;
	}

	public void setSelectFormation(List<SelectItem> selectFormation) {
		this.selectFormation = selectFormation;
	}

	public FormationEJB getFormationEJB() {
		return formationEJB;
	}

	public void setFormationEJB(FormationEJB FormationEJB) {
		this.formationEJB = FormationEJB;
	}

	public FormateurEJB getFormateurEJB() {
		return formateurEJB;
	}

	public void setFormateurEJB(FormateurEJB formateurEJB) {
		this.formateurEJB = formateurEJB;
	}

	public FormateurFormationEJB getFormateurFormationEJB() {
		return formateurFormationEJB;
	}

	public void setFormateurFormationEJB(
			FormateurFormationEJB formateurFormationEJB) {
		this.formateurFormationEJB = formateurFormationEJB;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getlDMFormations() {
		if (lDMFormations == null) {
			lDMFormations = new ListDataModel();
			lDMFormations.setWrappedData(formationEJB.findAll());
		}
		return lDMFormations;
	}

	@SuppressWarnings("rawtypes")
	public void setFormations(DataModel lDMFormations) {
		this.lDMFormations = lDMFormations;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getlDMFormateurs() {
		if (lDMFormateurs == null) {
			lDMFormateurs = new ListDataModel();
			lDMFormateurs.setWrappedData(formateurEJB.findAll());
		}
		return lDMFormateurs;
	}

	@SuppressWarnings("rawtypes")
	public void setlDMFormateurs(DataModel lDMFormateurs) {
		this.lDMFormateurs = lDMFormateurs;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getlDMFormateursOfFormation() {
		if (lDMFormateursOfFormation == null) {
			lDMFormateursOfFormation = new ListDataModel();
			lDMFormateursOfFormation.setWrappedData(formateurFormationEJB
					.findAllFormateursOfFormation(formation));
		}
		return lDMFormateursOfFormation;
	}

	@SuppressWarnings("rawtypes")
	public void setlDMFormateursOfFormation(DataModel lDMFormateursOfFormation) {
		this.lDMFormateursOfFormation = lDMFormateursOfFormation;
	}

}
