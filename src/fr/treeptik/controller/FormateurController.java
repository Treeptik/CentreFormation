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
import fr.treeptik.service.FormateurEJB;

@ManagedBean
@RequestScoped
public class FormateurController {

	@EJB
	private FormateurEJB formateurEJB;
	// @EJB
	// private FormationEJB formationEJB;
	//
	// private Formation formation = new Formation();
	private Formateur formateur = new Formateur();
	private List<Formateur> listFormateurs = new ArrayList<Formateur>();
	private List<SelectItem> selectFormateur;
	@SuppressWarnings("rawtypes")
	private DataModel formateurs;

	public String doCreate() {
		formateurEJB.create(formateur);
		// formation.setFormateurs(getListFormateurs().add(formateur));
		// formationEJB.createUneFormation(formation);
		// listFormateurs = formateurEJB.findAllFormateurs();
		return "messageFormateurCree";
	}
		
	@SuppressWarnings("rawtypes")
	public String doDelete() {
		Formateur formateur = (Formateur) formateurs.getRowData();
		formateurEJB.delete(formateur);
		formateurs = new ListDataModel();
		formateurs.setWrappedData(formateurEJB.findAll());
		return "listFormateurs";
	}

	public String doSelectUpdate() {
		formateur = (Formateur) formateurs.getRowData();
		return "updateFormateur";
	}

	@SuppressWarnings("rawtypes")
	public String doUpdate() {
		formateurEJB.update(formateur);
		formateurs = new ListDataModel();
		formateurs.setWrappedData(formateurEJB.findAll());
		return "messageFormateurUpdate";
	}

	public String doFindAll() {
		listFormateurs = formateurEJB.findAll();
		getFormateurs();
		return "listFormateurs";
	}

	public String doNew() {
		return "createFormateur";
	}

	public FormateurEJB getFormateurEJB() {
		return formateurEJB;
	}

	public void setFormateurEJB(FormateurEJB formateurEJB) {
		this.formateurEJB = formateurEJB;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	public List<Formateur> getListFormateurs() {
		return listFormateurs;
	}

	public void setListFormateurs(List<Formateur> listFormateurs) {
		this.listFormateurs = listFormateurs;
	}

	public List<SelectItem> getSelectFormateur() {

		listFormateurs = formateurEJB.findAll();
		selectFormateur = new ArrayList<SelectItem>();
		for (Formateur formateur : listFormateurs) {
			selectFormateur.add(new SelectItem(formateur.getId(), formateur
					.getNom() + " " + formateur.getPrenom()));
		}

		return selectFormateur;
	}

	public void setSelectFormateur(List<SelectItem> selectFormateur) {
		this.selectFormateur = selectFormateur;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getFormateurs() {

		if (formateurs == null) {
			formateurs = new ListDataModel();
			formateurs.setWrappedData(formateurEJB.findAll());
		}

		return formateurs;
	}

	@SuppressWarnings("rawtypes")
	public void setFormateurs(DataModel formateurs) {
		this.formateurs = formateurs;
	}

}
