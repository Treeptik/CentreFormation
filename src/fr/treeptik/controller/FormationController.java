package fr.treeptik.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import fr.treeptik.model.Evaluation;
import fr.treeptik.model.EvaluationFormation;
import fr.treeptik.model.Formateur;
import fr.treeptik.model.FormateurFormation;
import fr.treeptik.model.Formation;
import fr.treeptik.model.PKEvaluationFormation;
import fr.treeptik.model.PKFormateurFormation;
import fr.treeptik.service.EvaluationEJB;
import fr.treeptik.service.EvaluationFormationEJB;
import fr.treeptik.service.FormateurEJB;
import fr.treeptik.service.FormateurFormationEJB;
import fr.treeptik.service.FormationEJB;

@ManagedBean
@SessionScoped
public class FormationController {

	// *********ENTITE******************************************************
	private Formation formation = new Formation();
	private Formation selectedFormation;
	private Formateur formateur = new Formateur();
	private Evaluation evaluation = new Evaluation();
	private FormateurFormation formateurFormation = new FormateurFormation();
	private EvaluationFormation evaluationFormation = new EvaluationFormation();
	private PKFormateurFormation pKFormateurFormation = new PKFormateurFormation();
	private PKEvaluationFormation pKEvaluationFormation = new PKEvaluationFormation();

	// *********EJB*********************************************************
	@EJB
	private FormationEJB formationEJB;
	@EJB
	private FormateurEJB formateurEJB;
	@EJB
	private EvaluationEJB evaluationEJB;
	@EJB
	private EvaluationFormationEJB evaluationFormationEJB;
	@EJB
	private FormateurFormationEJB formateurFormationEJB;

	// **********LISTES*****************************************************
	private List<Formation> listFormations = new ArrayList<Formation>();
	private List<SelectItem> selectFormation;

	// **********DATAMODEL**************************************************

	private DataModel<Formation> lDMFormations;
	private DataModel<Formateur> lDMFormateurs;
	private DataModel<Evaluation> lDMEvaluations;
	private DataModel<Formateur> lDMFormateursOfFormation;
	private DataModel<Evaluation> lDMEvaluationsOfFormation;

	

	public String doRemoveFormateurFromFormation() {
		formateur = (Formateur) lDMFormateursOfFormation.getRowData();
		formateurFormation = formateurFormationEJB.findByFormateurAndFormation(
				formateur, formation);
		formateurFormationEJB.delete(formateurFormation);
		return "listFormations";
		
	}

	public List<Formateur> doFindAllFormateursOfFormation() {
		List<Formateur> listFormateursOfFormation = new ArrayList<Formateur>();
		listFormateursOfFormation = formateurFormationEJB
				.findAllFormateursOfFormation(formation);
		return listFormateursOfFormation;
	}

	public DataModel<Formateur> getlDMFormateursOfFormation() {
			lDMFormateursOfFormation = new ListDataModel<Formateur>();
			lDMFormateursOfFormation
					.setWrappedData(doFindAllFormateursOfFormation());
		return lDMFormateursOfFormation;
	}

	public void setlDMFormateursOfFormation(
			DataModel<Formateur> lDMFormateursOfFormation) {
		this.lDMFormateursOfFormation = lDMFormateursOfFormation;
	}

	public Formation getSelectedFormation() {
		return selectedFormation;
	}

	public void setSelected(Formation selectedFormation) {
		this.selectedFormation = selectedFormation;
	}

	public String selectFormation() {
		this.selectedFormation = (Formation) lDMFormations.getRowData();
		return "";
	}

	public String doSelectLinkToFormation() {
		formation = (Formation) lDMFormations.getRowData();
		return "listEvalsToLink";
	}

	public String doLinkToFormation() {
		try {
			evaluation = (Evaluation) lDMEvaluations.getRowData();
			pKEvaluationFormation.setEvaluation(evaluation);
			pKEvaluationFormation.setFormation(formation);
			evaluationFormation.setId(pKEvaluationFormation);
			evaluationFormationEJB.addEvalToFormation(evaluationFormation);
			return "listFormations";
		} catch (Exception e) {
			return "messageEvaluationDejaAjoutee";
		}

	}

	public String doAddFormateurToFormation() {
		formateur = (Formateur) lDMFormateurs.getRowData();
		pKFormateurFormation.setFormation(formation);
		pKFormateurFormation.setFormateur(formateur);
		formateurFormation.setId(pKFormateurFormation);
		formateurFormationEJB.addFormateurToFormation(formateurFormation);
		return "listFormations";
	}

	public String doListFormateursOfFormation() {
		formation = (Formation) lDMFormations.getRowData();
		return "listFormateursOfFormation";
	}
	
	public String doListEvaluationsOfFormation() {
		formation = (Formation) lDMFormations.getRowData();
		return "listEvaluationsOfFormation";
	}

	public String doSelectAddFormateur() {
		formation = (Formation) lDMFormations.getRowData();
		return "listFormateursToAdd";
	}

	public String doSelectUpdate() {
		formation = (Formation) lDMFormations.getRowData();
		return "updateFormation";
	}

	public String doCreate() {

		formationEJB.create(formation);
		listFormations = formationEJB.findAll();
		return "messageFormationCreee";
	}

	public String doDelete() {
		Formation formation = (Formation) lDMFormations.getRowData();
		formationEJB.delete(formation);
		return "listFormations";
	}

	public String doUpdate() {
		formationEJB.update(formation);
		return "messageFormationUpdate";
	}

	public String doFindAll() {
		listFormations = formationEJB.findAll();
		return "listFormations";
	}

	public String doNew() {
		formation = new Formation();
		return "../Formation/createFormation.jsf";
	}

	public List<Formation> getListFormations() {
		return listFormations;
	}

	public void setListFormations(List<Formation> listFormations) {
		this.listFormations = listFormations;
	}

	public DataModel<Evaluation> getlDMEvaluationsOfFormation() {
		lDMEvaluationsOfFormation = new ListDataModel<Evaluation>();
		lDMEvaluationsOfFormation.setWrappedData(evaluationFormationEJB
				.findAllEvalsOfFormation(formation));
		return lDMEvaluationsOfFormation;
	}

	public void setlDMEvaluationsOfFormation(
			DataModel<Evaluation> lDMEvaluationsOfFormation) {
		this.lDMEvaluationsOfFormation = lDMEvaluationsOfFormation;
	}

	public DataModel<Evaluation> getlDMEvaluations() {
		lDMEvaluations = new ListDataModel<Evaluation>();
		lDMEvaluations.setWrappedData(evaluationEJB.findAll());
		return lDMEvaluations;
	}

	public void setlDMEvaluations(DataModel<Evaluation> lDMevaluations) {
		this.lDMEvaluations = lDMevaluations;
	}

	public DataModel<Formateur> getlDMFormateurs() {
		lDMFormateurs = new ListDataModel<Formateur>();
		lDMFormateurs.setWrappedData(formateurEJB.findAll());
		return lDMFormateurs;
	}

	public void setlDMFormateurs(DataModel<Formateur> lDMFormateurs) {
		this.lDMFormateurs = lDMFormateurs;
	}

	public void setlDMFormations(DataModel<Formation> lDMFormations) {
		this.lDMFormations = lDMFormations;
	}

	public DataModel<Formation> getlDMFormations() {
		lDMFormations = new ListDataModel<Formation>();
		lDMFormations.setWrappedData(formationEJB.findAll());
		return lDMFormations;
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

	public FormateurFormationEJB getFormateurFormationEJB() {
		return formateurFormationEJB;
	}

	public void setFormateurFormationEJB(
			FormateurFormationEJB formateurFormationEJB) {
		this.formateurFormationEJB = formateurFormationEJB;
	}

	public EvaluationFormationEJB getEvaluationFormationEJB() {
		return evaluationFormationEJB;
	}

	public void setEvaluationFormationEJB(
			EvaluationFormationEJB evaluationFormationEJB) {
		this.evaluationFormationEJB = evaluationFormationEJB;
	}

	public EvaluationEJB getEvaluationEJB() {
		return evaluationEJB;
	}

	public void setEvaluationEJB(EvaluationEJB evaluationEJB) {
		this.evaluationEJB = evaluationEJB;
	}

	public FormateurEJB getFormateurEJB() {
		return formateurEJB;
	}

	public void setFormateurEJB(FormateurEJB formateurEJB) {
		this.formateurEJB = formateurEJB;
	}

	public FormationEJB getFormationEJB() {
		return formationEJB;
	}

	public void setFormationEJB(FormationEJB FormationEJB) {
		this.formationEJB = FormationEJB;
	}

	public PKEvaluationFormation getpKEvaluationFormation() {
		return pKEvaluationFormation;
	}

	public void setpKEvaluationFormation(
			PKEvaluationFormation pKEvaluationFormation) {
		this.pKEvaluationFormation = pKEvaluationFormation;
	}

	public PKFormateurFormation getpKFormateurFormation() {
		return pKFormateurFormation;
	}

	public void setpKFormateurFormation(
			PKFormateurFormation pKFormateurFormation) {
		this.pKFormateurFormation = pKFormateurFormation;
	}

	public EvaluationFormation getEvaluationFormation() {
		return evaluationFormation;
	}

	public void setEvaluationFormation(EvaluationFormation evaluationFormation) {
		this.evaluationFormation = evaluationFormation;
	}

	public FormateurFormation getFormateurFormation() {
		return formateurFormation;
	}

	public void setFormateurFormation(FormateurFormation formateurFormation) {
		this.formateurFormation = formateurFormation;
	}

	public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation Formation) {
		this.formation = Formation;
	}
}
