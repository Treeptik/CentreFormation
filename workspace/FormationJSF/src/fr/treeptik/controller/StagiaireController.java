package fr.treeptik.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

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
	
	private Formation formation = new Formation();
	private Evaluation evaluation = new Evaluation();
	private Stagiaire stagiaire = new Stagiaire();
	
	private List<Stagiaire> listStagiaires = new ArrayList<Stagiaire>();
//	private List<SelectItem> selectFormation;
	private List<SelectItem> selectNom = new ArrayList<SelectItem>();
//	
	@SuppressWarnings("rawtypes")
	private DataModel stagiaires;

	public String doSelectUpdate() {
		stagiaire = (Stagiaire) stagiaires.getRowData();
		return "updateStagiaire";
	}
	
	public String doUpdate() {
		stagiaireEJB.update(stagiaire);
		getStagiaires();
		return "message8";
	}
	
	public String doDelete() {
		Stagiaire stagiaire = (Stagiaire) stagiaires.getRowData();
		stagiaireEJB.delete(stagiaire);
		getStagiaires();
		return "listStagiaires";
	}
	
	
	
//	public String doCreateUnStagiaire() {
//		stagiaire = stagiaireEJB.createUnStagiaire(stagiaire);	
//		listStagiaires = stagiaireEJB.findAllStagiaires();
//		getStagiaires();
//		return "listStagiaires";
//	}
	
	public String doAddStagiaire(){
//		stagiaire.setFormation(new Formation());
//		stagiaire.getFormation().setId(formation.getId());
		stagiaire = stagiaireEJB.createUnStagiaire(stagiaire);
		return"message1";
	}
		
	public String findAllStagiaires() {
		listStagiaires = stagiaireEJB.findAllStagiaires();
		return "listStagiaires";
	}
	
	public String findStagiaireInSession(){
		return"listDeStagiaireParSessionID";
	}
	
	public String doFindStagiaireById(){
	    stagiaire = stagiaireEJB.findStagiaireById(stagiaire.getId());
		return"stagiaireDonnées";
	}
	
	public String doFindStagiaireByIdToUpdate(){
	    stagiaire = stagiaireEJB.findStagiaireById(stagiaire.getId());
		return"updateDonnéesStagiaire";
	}
	
	public String doRecherche(){
		return"recherche";
	}
	
	public String doRemoveStagiaire(){
		stagiaireEJB.removeStagiaire(stagiaire.getId());
//		listStagiaires = stagiaireEJB.findAllStagiaires();
//		getStagiaires();
		return "message4";
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
		listStagiaires = stagiaireEJB.findAllStagiaires();
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

//	public List<SelectItem> getSelectFormation() {
//		selectFormation = new ArrayList<SelectItem>();
//		List<Formation> allFormations = formationEJB.findAllFormations();
//		for (Formation formation : allFormations) {
//			
//			selectFormation.add(new SelectItem(formation.getId(), formation.getNom()));
//		}
//		
//		return selectFormation;
//	}

//	public void setSelectFormation(List<SelectItem> selectFormation) {
//		this.selectFormation = selectFormation;
//	}

	public List<SelectItem> getSelectNom() {
		selectNom = new ArrayList<SelectItem>();
		List<Stagiaire> allNames = stagiaireEJB.findAllStagiaires();
		for(Stagiaire stagiaire : allNames){
			selectNom.add(new SelectItem(stagiaire.getId(), stagiaire.getNom()));
		}
		
		return selectNom;
	}

	public void setSelectNom(List<SelectItem> selectNom) {
	this.selectNom = selectNom;
	}

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
			stagiaires.setWrappedData(stagiaireEJB.findAllStagiaires());
		}
		return stagiaires;
	}

	@SuppressWarnings("rawtypes")
	public void setStagiaires(DataModel stagiaires) {
		this.stagiaires = stagiaires;
	}

}
