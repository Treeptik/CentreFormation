package fr.treeptik.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

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
	private List<AdministrateurTreeptik> listAdministrateurTreeptik = new ArrayList<AdministrateurTreeptik>();

	public String doCreateUnAdministrateurTreeptik(){
		administrateurTreeptik = administrateurTreeptikEJB.createAdministrateurTreeptik(administrateurTreeptik);
//		stagiaire.setadministrateurTreeptik(administrateurTreeptik);
//		stagiaireEJB.createUnStagiaire(stagiaire);
//		formateur.setAdministrateurTreeptik(administrateurTreeptik);
//		formateurEJB.createUnFormateur(formateur);
		listAdministrateurTreeptik = administrateurTreeptikEJB.findAllAdministrateurTreeptik();
		return"listAdministrateur";
	}
	
	public String doFindAllAdministrateurTreeptik(){
		listAdministrateurTreeptik = administrateurTreeptikEJB.findAllAdministrateurTreeptik();
		return"listAdministrateur";
	}
	
	
	
	public String doNewAdministrateurTreeptik(){
		return"createAdministrateurTreeptik";
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

	public List<AdministrateurTreeptik> getListAdministrateurTreeptik() {
		return listAdministrateurTreeptik;
	}

	public void setListAdministrateurTreeptik(
			List<AdministrateurTreeptik> listAdministrateurTreeptik) {
		this.listAdministrateurTreeptik = listAdministrateurTreeptik;
	}
}
