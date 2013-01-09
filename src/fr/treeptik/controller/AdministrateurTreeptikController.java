package fr.treeptik.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import fr.treeptik.model.AdministrateurTreeptik;
import fr.treeptik.model.Formateur;
import fr.treeptik.model.Stagiaire;
import fr.treeptik.service.AdministrateurTreeptikEJB;
import fr.treeptik.service.FormateurEJB;
import fr.treeptik.service.StagiaireEJB;

@ManagedBean
@RequestScoped
public class AdministrateurTreeptikController {
	

	@EJB
	private AdministrateurTreeptikEJB administrateurTreeptikEJB;
	@EJB
	private StagiaireEJB stagiaireEJB;
	@EJB
	private FormateurEJB formateurEJB ;
	
	
	private AdministrateurTreeptik administrateurTreeptik = new AdministrateurTreeptik();
	private Stagiaire stagiaire = new Stagiaire();
	private Formateur formateur = new Formateur();
	private List<AdministrateurTreeptik> listAdministrateurTreeptiks = new ArrayList<AdministrateurTreeptik>();
	@SuppressWarnings("rawtypes")
	private DataModel administrateurTreeptiks;

	public String doCreate(){
		administrateurTreeptikEJB.create(administrateurTreeptik);
//		stagiaire.setadministrateurTreeptik(administrateurTreeptik);
//		stagiaireEJB.createUnStagiaire(stagiaire);
//		formateur.setAdministrateurTreeptik(administrateurTreeptik);
//		formateurEJB.createUnFormateur(formateur);
		listAdministrateurTreeptiks = administrateurTreeptikEJB.findAll();
		return"messageAdministrateurCree";
	}
	
	@SuppressWarnings("rawtypes")
	public String doDelete() {
		AdministrateurTreeptik administrateurTreeptik = (AdministrateurTreeptik) administrateurTreeptiks.getRowData();
		administrateurTreeptikEJB.delete(administrateurTreeptik);
		administrateurTreeptiks = new ListDataModel();
		administrateurTreeptiks.setWrappedData(administrateurTreeptikEJB.findAll());
		return "listAdministrateurs";
	}
	
	public String doSelectUpdate() {
		administrateurTreeptik = (AdministrateurTreeptik) administrateurTreeptiks.getRowData();
		return "updateAdministrateur";
	}
	
	@SuppressWarnings("rawtypes")
	public String doUpdate() {
		administrateurTreeptikEJB.update(administrateurTreeptik);
		administrateurTreeptiks = new ListDataModel();
		administrateurTreeptiks.setWrappedData(administrateurTreeptikEJB.findAll());
		return "messageAdministrateurUpdate";
	}
	
	public String doFindAll(){
		listAdministrateurTreeptiks = administrateurTreeptikEJB.findAll();
		return"listAdministrateurs";
	}
	
	public String doNew(){
		return"createAdministrateur";
	}
	
	public AdministrateurTreeptikEJB getAdministrateurTreeptikEJB() {
		return administrateurTreeptikEJB;
	}

	public void setAdministrateurTreeptikEJB(
			AdministrateurTreeptikEJB administrateurTreeptikEJB) {
		this.administrateurTreeptikEJB = administrateurTreeptikEJB;
	}

	public StagiaireEJB getStagiaireEJB() {
		return stagiaireEJB;
	}

	public void setStagiaireEJB(StagiaireEJB stagiaireEJB) {
		this.stagiaireEJB = stagiaireEJB;
	}

	public FormateurEJB getFormateurEJB() {
		return formateurEJB;
	}

	public void setFormateurEJB(FormateurEJB formateurEJB) {
		this.formateurEJB = formateurEJB;
	}

	public AdministrateurTreeptik getAdministrateurTreeptik() {
		return administrateurTreeptik;
	}

	public void setAdministrateurTreeptik(
			AdministrateurTreeptik administrateurTreeptik) {
		this.administrateurTreeptik = administrateurTreeptik;
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	public List<AdministrateurTreeptik> getListAdministrateurTreeptiks() {
		return listAdministrateurTreeptiks;
	}

	public void setListAdministrateurTreeptiks(
			List<AdministrateurTreeptik> listAdministrateurTreeptiks) {
		this.listAdministrateurTreeptiks = listAdministrateurTreeptiks;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getAdministrateurTreeptiks() {
		if (administrateurTreeptiks == null) {
			administrateurTreeptiks = new ListDataModel();
			administrateurTreeptiks.setWrappedData(administrateurTreeptikEJB.findAll());
		}
		return administrateurTreeptiks;
	}

	@SuppressWarnings("rawtypes")
	public void setAdministrateurTreeptiks(DataModel administrateurTreeptiks) {
		this.administrateurTreeptiks = administrateurTreeptiks;
	}
}
