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

	// *********ENTITE******************************************************

	private Formateur formateur = new Formateur();

	// *********EJB*********************************************************
	@EJB
	private FormateurEJB formateurEJB;

	// **********LISTES*****************************************************
	private List<Formateur> listFormateurs = new ArrayList<Formateur>();
	private List<SelectItem> selectFormateur;

	// **********DATAMODEL**************************************************
	@SuppressWarnings("rawtypes")
	private DataModel lDMFormateurs;

	public String doCreate() {
		formateurEJB.create(formateur);
		getlDMFormateurs();
		return "messageFormateurCree";
	}

	@SuppressWarnings("rawtypes")
	public String doDelete() {
		Formateur formateur = (Formateur) lDMFormateurs.getRowData();
		formateurEJB.delete(formateur);
		lDMFormateurs = new ListDataModel();
		lDMFormateurs.setWrappedData(formateurEJB.findAll());
		return "listFormateurs";
	}

	public String doSelectUpdate() {
		formateur = (Formateur) lDMFormateurs.getRowData();
		return "updateFormateur";
	}

	@SuppressWarnings("rawtypes")
	public String doUpdate() {
		formateurEJB.update(formateur);
		lDMFormateurs = new ListDataModel();
		lDMFormateurs.setWrappedData(formateurEJB.findAll());
		return "messageFormateurUpdate";
	}

	public String doFindAll() {
		listFormateurs = formateurEJB.findAll();
		getlDMFormateurs();
		return "listFormateurs";
	}

	public String doNew() {
		return "createFormateur";
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

	public List<Formateur> getListFormateurs() {
		return listFormateurs;
	}

	public void setListFormateurs(List<Formateur> listFormateurs) {
		this.listFormateurs = listFormateurs;
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
}
