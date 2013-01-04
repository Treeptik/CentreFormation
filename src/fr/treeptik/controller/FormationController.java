package fr.treeptik.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import fr.treeptik.model.Formation;
import fr.treeptik.service.FormationEJB;

@ManagedBean
@RequestScoped
public class FormationController {
	@EJB
	private FormationEJB formationEJB;
	private Formation formation = new Formation();
	private List<Formation> listFormations = new ArrayList<Formation>();
	private List<SelectItem> selectFormation;

	public String doCreate() {
		formationEJB.create(formation);
		listFormations = formationEJB.findAll();
		return "message3";
	}

	public String doFindAll() {
		listFormations = formationEJB.findAll();
		return "listFormations";
	}

	@SuppressWarnings("rawtypes")
	private DataModel formations;

	public String doSelectUpdate() {
		formation = (Formation) formations.getRowData();
		return "updateFormation";
	}

	public String doUpdate() {
		formationEJB.update(formation);
		getFormations();
		return "message10";
	}

	public String doDelete() {
		Formation formation = (Formation) formations.getRowData();
		formationEJB.delete(formation);
		getFormations();
		return "listFormations";
	}

	public String doNew() {
		return "createFormation";
	}

	public FormationEJB getFormationEJB() {
		return formationEJB;
	}

	public void setFormationEJB(FormationEJB FormationEJB) {
		this.formationEJB = FormationEJB;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation Formation) {
		this.formation = Formation;
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

	@SuppressWarnings("rawtypes")
	public DataModel getFormations() {

		if (formations == null) {
			formations = new ListDataModel();
			formations.setWrappedData(formationEJB.findAll());
		}
		return formations;
	}

	@SuppressWarnings("rawtypes")
	public void setFormations(DataModel formations) {
		this.formations = formations;
	}

}
