package fr.treeptik.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import fr.treeptik.model.Evaluation;
import fr.treeptik.model.Formation;
import fr.treeptik.model.Stagiaire;
import fr.treeptik.service.FormationEJB;
import fr.treeptik.service.StagiaireEJB;

@ManagedBean
@SessionScoped
public class StagiaireController {

	@EJB
	private StagiaireEJB stagiaireEJB;
	@EJB
	private FormationEJB formationEJB;
	@EJB
	private SendTextMessage gestionmail;

	private Formation formation = new Formation();
	private Evaluation evaluation = new Evaluation();
	private Stagiaire stagiaire = new Stagiaire();

	private List<Stagiaire> listStagiaires = new ArrayList<Stagiaire>();
	private List<SelectItem> selectFormation;
	@SuppressWarnings("rawtypes")
	private DataModel stagiaires;

	public String doCreate() {
		stagiaireEJB.create(stagiaire);
		gestionmail.mailCreationUser(stagiaire);
		listStagiaires = stagiaireEJB.findAll();
		getStagiaires();
		return "messageStagiaireCree";
	}

	@SuppressWarnings("rawtypes")
	public String doDelete() {
		Stagiaire stagiaire = (Stagiaire) stagiaires.getRowData();
		stagiaireEJB.delete(stagiaire);
		stagiaires = new ListDataModel();
		stagiaires.setWrappedData(stagiaireEJB.findAll());
		return "listStagiaires";
	}

	public String doSelectUpdate() {
		stagiaire = (Stagiaire) stagiaires.getRowData();
		return "updateStagiaire";
	}

	public void qui() {
		System.out.println(getRequest().getUserPrincipal().toString());
		System.out.println("ça marche?");
		
		
		System.out.println(stagiaire.getNom());
	}

	@SuppressWarnings("rawtypes")
	public String doUpdate() {
		stagiaireEJB.update(stagiaire);
		stagiaires = new ListDataModel();
		stagiaires.setWrappedData(stagiaireEJB.findAll());
		return "messageStagiaireUpdate";
	}

	public String doAddStagiaire() {
		// stagiaire.setFormation(new Formation());
		// stagiaire.getFormation().setId(formation.getId());
		stagiaireEJB.create(stagiaire);
		return "message1";
	}

	public String doFindAll() {
		listStagiaires = stagiaireEJB.findAll();
		return "listStagiaires";
	}

	public String findStagiaireInSession() {
		return "listDeStagiaireParSessionID";
	}

	public String doFindById() {
		stagiaire = stagiaireEJB.findById(stagiaire.getId());
		return "stagiaireDonnées";
	}

	public String doFindByIdToUpdate() {
		stagiaire = stagiaireEJB.findById(stagiaire.getId());
		return "updateDonnéesStagiaire";
	}

	public String doRecherche() {
		return "recherche";
	}

	public StagiaireEJB getStagiareEJB() {
		return stagiaireEJB;
	}

	public void setStagiareEJB(StagiaireEJB stagiareEJB) {
		this.stagiaireEJB = stagiareEJB;
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public List<Stagiaire> getListStagiaires() {
		listStagiaires = stagiaireEJB.findAll();
		return listStagiaires;
	}

	public void setListStagiaires(List<Stagiaire> listStagiaires) {
		this.listStagiaires = listStagiaires;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	// public List<SelectItem> getSelectFormation() {
	// selectFormation = new ArrayList<SelectItem>();
	// List<Formation> allFormations = formationEJB.findAllFormations();
	// for (Formation formation : allFormations) {
	//
	// selectFormation.add(new SelectItem(formation.getId(),
	// formation.getNom()));
	// }
	//
	// return selectFormation;
	// }

	// public void setSelectFormation(List<SelectItem> selectFormation) {
	// this.selectFormation = selectFormation;
	// }

	public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getStagiaires() {
		if (stagiaires == null) {
			stagiaires = new ListDataModel();
			stagiaires.setWrappedData(stagiaireEJB.findAll());
		}
		return stagiaires;
	}

	@SuppressWarnings("rawtypes")
	public void setStagiaires(DataModel stagiaires) {
		this.stagiaires = stagiaires;
	}

	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

}
