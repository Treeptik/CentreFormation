package fr.treeptik.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;

import fr.treeptik.model.Administrateur;
import fr.treeptik.model.Formateur;
import fr.treeptik.model.Stagiaire;
import fr.treeptik.service.AdministrateurEJB;
import fr.treeptik.service.FormateurEJB;
import fr.treeptik.service.StagiaireEJB;

@ManagedBean
@RequestScoped
public class AdministrateurController {
	

	@EJB
	private AdministrateurEJB administrateurEJB;
	@EJB
	private StagiaireEJB stagiaireEJB;
	@EJB
	private FormateurEJB formateurEJB ;
	
	
	private Administrateur administrateur = new Administrateur();
	private Stagiaire stagiaire = new Stagiaire();
	private Formateur formateur = new Formateur();
	private List<Administrateur> listAdministrateurs = new ArrayList<Administrateur>();
	@SuppressWarnings("rawtypes")
	private DataModel administrateurs;

	public String doCreate(){
		administrateurEJB.create(administrateur);
//		stagiaire.setadministrateurTreeptik(administrateur);
//		stagiaireEJB.createUnStagiaire(stagiaire);
//		formateur.setAdministrateurTreeptik(administrateur);
//		formateurEJB.createUnFormateur(formateur);
		listAdministrateurs = administrateurEJB.findAll();
		return"messageAdministrateurCree";
	}
	
	@SuppressWarnings("rawtypes")
	public String doDelete() {
		Administrateur administrateur = (Administrateur) administrateurs.getRowData();
		administrateurEJB.delete(administrateur);
		administrateurs = new ListDataModel();
		administrateurs.setWrappedData(administrateurEJB.findAll());
		return "listAdministrateurs";
	}
	
	public String doSelectUpdate() {
		administrateur = (Administrateur) administrateurs.getRowData();
		return "updateAdministrateur";
	}
	
	@SuppressWarnings("rawtypes")
	public String doUpdate() {
		administrateurEJB.update(administrateur);
		administrateurs = new ListDataModel();
		administrateurs.setWrappedData(administrateurEJB.findAll());
		return "messageAdministrateurUpdate";
	}
	
	public String doFindAll(){
		listAdministrateurs = administrateurEJB.findAll();
		return"listAdministrateurs";
	}
	
	public String doNew(){
		return"createAdministrateur";
	}
	
	public AdministrateurEJB getAdministrateurEJB() {
		return administrateurEJB;
	}

	public void setAdministrateurEJB(
			AdministrateurEJB administrateurEJB) {
		this.administrateurEJB = administrateurEJB;
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

	public Administrateur getAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(
			Administrateur administrateur) {
		this.administrateur = administrateur;
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

	public List<Administrateur> getListAdministrateurs() {
		return listAdministrateurs;
	}

	public void setListAdministrateurs(
			List<Administrateur> listAdministrateurs) {
		this.listAdministrateurs = listAdministrateurs;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getAdministrateurs() {
		if (administrateurs == null) {
			administrateurs = new ListDataModel();
			administrateurs.setWrappedData(administrateurEJB.findAll());
		}
		return administrateurs;
	}

	@SuppressWarnings("rawtypes")
	public void setAdministrateurs(DataModel administrateurs) {
		this.administrateurs = administrateurs;
	}
	
	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}
	
}
