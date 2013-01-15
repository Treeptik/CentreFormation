package fr.treeptik.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import fr.treeptik.model.CompoKeysEval;
import fr.treeptik.model.Evaluation;
import fr.treeptik.model.Formateur;
import fr.treeptik.model.Formation;
import fr.treeptik.model.Session;
import fr.treeptik.model.Stagiaire;
import fr.treeptik.service.EvaluationEJB;
import fr.treeptik.service.FormateurEJB;
import fr.treeptik.service.FormationEJB;
import fr.treeptik.service.SessionEJB;
import fr.treeptik.service.StagiaireEJB;

@ManagedBean
@SessionScoped
public class EvaluationController {

	private Evaluation evaluation = new Evaluation();	

	@EJB
	private EvaluationEJB evaluationEJB;
	@EJB
	private SessionEJB sessionEJB;
	@EJB
	private StagiaireEJB stagiaireEJB;
	@EJB
	private FormationEJB formationEJB;
	@EJB
	private FormateurEJB formateurEJB;
	
	private List<Evaluation> listEvaluations = new ArrayList<Evaluation>();
	private List<SelectItem> selectSession;
	private List<SelectItem> selectFormation;
	private List<SelectItem> selectFormateur;
	private CompoKeysEval compoKeyEval = new CompoKeysEval();
	private Stagiaire stagiaire;
	private Session session = new Session();
	private Formateur formateur = new Formateur();
	private Formation formation = new Formation();

	// private String idStagiaire;

	@SuppressWarnings("rawtypes")
	private DataModel evaluations;

	@EJB
	private SendTextMessage gestionmail;

	public String doFillEval() {
		try{
		evaluationEJB.create(evaluation);
		gestionmail.mailRecapEvaluation(evaluation);
		return "messageEvaluationEffectue";
		}
		catch (Exception e){
		return "messageEvaluationDejaEffectue";
		}
	}

	@SuppressWarnings("rawtypes")
	public String doDelete() {
		Evaluation evaluation = (Evaluation) evaluations.getRowData();
		evaluationEJB.delete(evaluation);
		evaluations = new ListDataModel();
		evaluations.setWrappedData(evaluationEJB.findAll());
		return "listEvaluations";
	}

	public String doSelectUpdate() {
		evaluation = (Evaluation) evaluations.getRowData();
		return "updateEvaluation";
	}

	@SuppressWarnings("rawtypes")
	public String doUpdate() {
		evaluationEJB.update(evaluation);
		evaluations = new ListDataModel();
		evaluations.setWrappedData(evaluationEJB.findAll());
		return "messageEvaluationUpdate";
	}
	
	public String doNew() {
		evaluation = new Evaluation();
		return "selectSession";
	}
	
/**
 * Méthode pour choisir sa session
 * définit l'utilisateur authentifiée en tant que 
 * stagiaire effectuant l'évaluation
 * renvoie à la page doEvaluation
 */
	public String chooseSession() {
		System.out.println("sessioin Id avant :"+session.getId());
		session = sessionEJB.findById(session.getId());
		System.out.println("après"+session.getId());
		compoKeyEval.setSession(session);
		System.out.println("stagaire avant"+stagiaire);
		stagiaire = stagiaireEJB.findStagiaireByEmail(getRequest()
				.getUserPrincipal().toString());
		System.out.println("stagaire après : "+stagiaire.getId());
		compoKeyEval.setStagiaire(stagiaire);
		return "selectFormation";
	}
	
	public String chooseFormation() {
		formation = formationEJB.findById(formation.getId());
		compoKeyEval.setFormation(formation);
		evaluation.setId(compoKeyEval);
		return "selectFormateur";
	}
	
	public String chooseFormateur() {
		formateur = formateurEJB.findById(formateur.getId());
		compoKeyEval.setFormateur(formateur);
		evaluation.setId(compoKeyEval);
		return "doEvaluation";
	}
//**********Méthodes pour remplir les Items des SelectOneMenu************//
	public List<SelectItem> getSelectSession() {
		if (selectSession == null) {
			selectSession = new ArrayList<SelectItem>();
			List<Session> allSessions = sessionEJB.findAll();
			for (Session session : allSessions) {
				selectSession.add(new SelectItem(session.getId(), session
						.getNom()));
			}
		}
		return selectSession;
	}

	public void setSelectSession(List<SelectItem> selectSession) {
		this.selectSession = selectSession;
	}

	public List<SelectItem> getSelectFormation() {
		if (selectFormation == null) {
			selectFormation = new ArrayList<SelectItem>();
			List<Formation> allFormations = formationEJB.findAll();
			for (Formation formation : allFormations) {
				selectFormation.add(new SelectItem(formation.getId(), formation
						.getNom()));
			}
		}
		return selectFormation;
	}
	
	public void setSelectFormation(List<SelectItem> selectFormation) {
		this.selectFormation = selectFormation;
	}

	public List<SelectItem> getSelectFormateur() {
		if (selectFormateur == null) {
			selectFormateur = new ArrayList<SelectItem>();
			List<Formateur> allFormateurs = formateurEJB.findAll();
			for (Formateur formateur : allFormateurs) {
				selectFormateur.add(new SelectItem(formateur.getId(), formateur
						.getNom()));
			}
		}
		return selectFormateur;
	}
	
	public void setSelectFormateur(List<SelectItem> selectFormateur) {
		this.selectFormateur = selectFormateur;
	}
	
//***************ENTITE**************	
	public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}
	
	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
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

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

//***************EJB**********************
	public EvaluationEJB getEvaluationEJB() {
		return evaluationEJB;
	}

	public void setEvaluationEJB(EvaluationEJB evaluationEJB) {
		this.evaluationEJB = evaluationEJB;
	}

	public SessionEJB getSessionEJB() {
		return sessionEJB;
	}

	public void setSessionEJB(SessionEJB sessionEJB) {
		this.sessionEJB = sessionEJB;
	}

	public StagiaireEJB getStagiaireEJB() {
		return stagiaireEJB;
	}

	public void setStagiaireEJB(StagiaireEJB stagiaireEJB) {
		this.stagiaireEJB = stagiaireEJB;
	}

	public FormationEJB getFormationEJB() {
		return formationEJB;
	}

	public void setFormationEJB(FormationEJB formationEJB) {
		this.formationEJB = formationEJB;
	}

	public FormateurEJB getFormateurEJB() {
		return formateurEJB;
	}

	public void setFormateurEJB(FormateurEJB formateurEJB) {
		this.formateurEJB = formateurEJB;
	}
	
//*************LIST ET DATAMODEL***********
	public List<Evaluation> getListEvaluation() {
		return listEvaluations;
	}

	public void setListEvaluation(List<Evaluation> listEvaluations) {
		this.listEvaluations = listEvaluations;
	}

	
	public List<Evaluation> getListEvaluations() {
		return listEvaluations;
	}

	public void setListEvaluations(List<Evaluation> listEvaluations) {
		this.listEvaluations = listEvaluations;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getEvaluations() {
		if (evaluations == null) {
			evaluations = new ListDataModel();
			evaluations.setWrappedData(evaluationEJB.findAll());
		}
		return evaluations;
	}

	@SuppressWarnings("rawtypes")
	public void setEvaluations(DataModel evaluations) {
		this.evaluations = evaluations;
	}

///************UTILE AVEC JPA???????*********///
	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

}
