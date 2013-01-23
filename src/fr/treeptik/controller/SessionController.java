package fr.treeptik.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import org.primefaces.event.ToggleEvent;

import fr.treeptik.model.Evaluation;
import fr.treeptik.model.EvaluationFormation;
import fr.treeptik.model.Formation;
import fr.treeptik.model.FormationSession;
import fr.treeptik.model.PKEvaluationFormation;
import fr.treeptik.model.PKFormationSession;
import fr.treeptik.model.PKQuestionnaire;
import fr.treeptik.model.PKStagiaireSession;
import fr.treeptik.model.Questionnaire;
import fr.treeptik.model.Session;
import fr.treeptik.model.Stagiaire;
import fr.treeptik.model.StagiaireSession;
import fr.treeptik.service.EvaluationEJB;
import fr.treeptik.service.EvaluationFormationEJB;
import fr.treeptik.service.FormationEJB;
import fr.treeptik.service.FormationSessionEJB;
import fr.treeptik.service.QuestionnaireEJB;
import fr.treeptik.service.SessionEJB;
import fr.treeptik.service.StagiaireEJB;
import fr.treeptik.service.StagiaireSessionEJB;

@ManagedBean
@SessionScoped
public class SessionController {

	// *********ENTITE******************************************************
	private Session session = new Session();
	private Formation formation = new Formation();
	private Stagiaire stagiaire = new Stagiaire();
	private Evaluation evaluation = new Evaluation();

	private Questionnaire questionnaire = new Questionnaire();
	private StagiaireSession stagiaireSession = new StagiaireSession();
	private FormationSession formationSession = new FormationSession();
	private EvaluationFormation evaluationFormation = new EvaluationFormation();

	private PKFormationSession pKFormationSession = new PKFormationSession();
	private PKStagiaireSession pKStagiaireSession = new PKStagiaireSession();
	private PKQuestionnaire pKQuestionnaire = new PKQuestionnaire();
	private PKEvaluationFormation pKEvaluationFormation = new PKEvaluationFormation();

	// *********EJB*********************************************************
	@EJB
	private SessionEJB sessionEJB;
	@EJB
	private StagiaireEJB stagiaireEJB;
	@EJB
	private FormationEJB formationEJB;
	@EJB
	private EvaluationEJB evaluationEJB;
	@EJB
	private QuestionnaireEJB questionnaireEJB;
	@EJB
	private StagiaireSessionEJB stagiaireSessionEJB;
	@EJB
	private FormationSessionEJB formationSessionEJB;
	@EJB
	private EvaluationFormationEJB evaluationFormationEJB;

	// **********LISTES*****************************************************
	private List<Session> listSessions = new ArrayList<Session>();
	private List<Stagiaire> listStagiaires = new ArrayList<Stagiaire>();
	private List<SelectItem> selectStagiaire;

	// **********DATAMODEL**************************************************

	private DataModel<Session> lDMSessions;
	private DataModel<Stagiaire> lDMStagiaires;
	private DataModel<Formation> lDMFormations;
	private DataModel<Evaluation> lDMEvaluations;
	private DataModel<Stagiaire> lDMStagiairesOfSession;
	private DataModel<StagiaireSession> lDMStagiaireSessionsOfSession;
	private DataModel<Formation> lDMFormationsOfSession;
	private DataModel<Evaluation> lDMEvaluationsOfSession;

	// *********TEST PRIMEFACES**************

	public void onRowToggle(ToggleEvent event) {
		session = (Session) lDMSessions.getRowData();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"ligne :" + event.getVisibility(), "Session:"
						+ ((Session) event.getData()).getNom());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String doListStagiairesOfSession() {
		session = (Session) lDMSessions.getRowData();
		return "listStagiairesOfSession";
	}

	public String doRemoveStagiaireFromSession() {

		stagiaire = (Stagiaire) lDMStagiairesOfSession.getRowData();
		stagiaireSession = stagiaireSessionEJB.findByStagiaireAndSession(
				stagiaire, session);
		stagiaireSessionEJB.delete(stagiaireSession);
		return "listSessions";
	}
	
	public String doRemoveFormationFromSession() {

		formation = (Formation) lDMFormationsOfSession.getRowData();
		formationSession = formationSessionEJB.findByFormationAndSession(
				formation, session);
		formationSessionEJB.delete(formationSession);
		return "listSessions";
	}

	public List<Stagiaire> doFindAllStagiairesOfSession() {
		List<Stagiaire> listStagiairesOfSession = new ArrayList<Stagiaire>();
		listStagiairesOfSession = stagiaireSessionEJB
				.findAllStagiairesOfSession(session);
		return listStagiairesOfSession;
	}
	

	public DataModel<Stagiaire> getlDMStagiairesOfSession() {
		lDMStagiairesOfSession = new ListDataModel<Stagiaire>();
		lDMStagiairesOfSession.setWrappedData(doFindAllStagiairesOfSession());
		return lDMStagiairesOfSession;
	}

	public void setlDMStagiairesOfSession(
			DataModel<Stagiaire> lDMStagiairesOfSession) {
		this.lDMStagiairesOfSession = lDMStagiairesOfSession;
	}

	public String doAddStagiaireToSession() {
		stagiaire = (Stagiaire) lDMStagiaires.getRowData();
		pKStagiaireSession.setSession(session);
		pKStagiaireSession.setStagiaire(stagiaire);
		stagiaireSession.setId(pKStagiaireSession);
		stagiaireSessionEJB.addStagiaireToSession(stagiaireSession);
		return "listSessions";
	}

	public String doAddFormationToSession() {
		formation = (Formation) lDMFormations.getRowData();
		pKFormationSession.setSession(session);
		pKFormationSession.setFormation(formation);
		formationSession.setId(pKFormationSession);
		formationSessionEJB.addFormationToSession(formationSession);
		return "listSessions";
	}

	public String doListFormationsOfSession() {
		session = (Session) lDMSessions.getRowData();
		return "listFormationsOfSession";
	}

	public String doListEvaluationsOfSession() {
		session = (Session) lDMSessions.getRowData();
		return "listEvaluationsOfSession";
	}

	public String doSelectLinkToEval() {
		session = (Session) lDMSessions.getRowData();
		return "listEvalsToLink";
	}

	public String doSelectAddStagiaire() {
		session = (Session) lDMSessions.getRowData();
		return "listStagiairesToAdd";
	}
	
	public String doSelectRemoveStagiaire() {
		session = (Session) lDMSessions.getRowData();
		return "listStagiairesOfSession";
	}
	
	public String doSelectRemoveFormation() {
		session = (Session) lDMSessions.getRowData();
		return "listFormationsOfSession";
	}


	public String doSelectAddFormation() {
		session = (Session) lDMSessions.getRowData();
		return "listFormationsToAdd";
	}

	public String doSelectUpdate() {
		session = (Session) lDMSessions.getRowData();
		return "updateSession";
	}

	public String doCreate() {
		sessionEJB.create(session);
		return "messageSessionCreee";
	}

	public String doDelete() {
		Session session = (Session) lDMSessions.getRowData();
		sessionEJB.delete(session);
		lDMSessions = new ListDataModel<Session>();
		lDMSessions.setWrappedData(sessionEJB.findAll());
		return "listSessions";
	}

	public String doUpdate() {
		sessionEJB.update(session);
		lDMSessions = new ListDataModel<Session>();
		lDMSessions.setWrappedData(sessionEJB.findAll());
		return "messageSessionUpdate";
	}

	public String doFindAll() {
		listSessions = sessionEJB.findAll();
		return "listSessions";
	}
	
	public String doNew() {
		session = new Session();
		return "../Session/createSession.jsf";
	}

	public DataModel<StagiaireSession> getlDMStagiaireSessionsOfSession() {
		if (lDMStagiaireSessionsOfSession == null) {
			lDMStagiaireSessionsOfSession = new ListDataModel<StagiaireSession>();
			lDMStagiaireSessionsOfSession.setWrappedData(formationSessionEJB
					.findAllFormationsOfSession(session));
		}
		return lDMStagiaireSessionsOfSession;
	}

	public void setlDMStagiaireSessionsOfSession(
			DataModel<StagiaireSession> lDMStagiaireSessionsOfSession) {
		this.lDMStagiaireSessionsOfSession = lDMStagiaireSessionsOfSession;
	}
	
	public DataModel<Formation> getlDMFormationsOfSession() {
			lDMFormationsOfSession = new ListDataModel<Formation>();
			lDMFormationsOfSession.setWrappedData(formationSessionEJB
					.findAllFormationsOfSession(session));

		return lDMFormationsOfSession;
	}

	public void setlDMFormationsOfSession(
			DataModel<Formation> lDMFormationsOfSession) {
		this.lDMFormationsOfSession = lDMFormationsOfSession;
	}

	public DataModel<Evaluation> getlDMEvaluationsOfSession() {
		if (lDMEvaluationsOfSession == null) {

			List<Formation> tempListFormations = new ArrayList<Formation>();
			List<Evaluation> tempListEvaluations = new ArrayList<Evaluation>();
			List<Evaluation> tempListEvaluation2 = new ArrayList<Evaluation>();

			tempListFormations = formationSessionEJB
					.findAllFormationsOfSession(session);
			for (Formation formation : tempListFormations) {
				tempListEvaluations = evaluationFormationEJB
						.findAllEvalsOfFormation(formation);
				for (Evaluation evaluation : tempListEvaluations) {
					tempListEvaluation2.add(evaluation);
				}
			}
			lDMEvaluationsOfSession = new ListDataModel<Evaluation>();
			lDMEvaluationsOfSession.setWrappedData(tempListEvaluation2);
		}
		return lDMEvaluationsOfSession;
	}

	public void setlDMEvaluationsOfSession(
			DataModel<Evaluation> lDMEvaluationsOfSession) {
		this.lDMEvaluationsOfSession = lDMEvaluationsOfSession;
	}

	public DataModel<Evaluation> getlDMEvaluations() {
		if (lDMEvaluations == null) {
			lDMEvaluations = new ListDataModel<Evaluation>();
			lDMEvaluations.setWrappedData(evaluationEJB.findAll());
		}
		return lDMEvaluations;
	}

	public void setlDMEvaluations(DataModel<Evaluation> lDMevaluations) {
		this.lDMEvaluations = lDMevaluations;
	}

	public DataModel<Formation> getlDMFormations() {
		if (lDMFormations == null) {
			lDMFormations = new ListDataModel<Formation>();
			lDMFormations.setWrappedData(formationEJB.findAll());
		}
		return lDMFormations;
	}

	public void setlDMFormations(DataModel<Formation> lDMFormations) {
		this.lDMFormations = lDMFormations;
	}

	public DataModel<Stagiaire> getlDMStagiaires() {
		if (lDMStagiaires == null) {
			lDMStagiaires = new ListDataModel<Stagiaire>();
			lDMStagiaires.setWrappedData(stagiaireEJB.findAll());
		}
		return lDMStagiaires;
	}

	public void setlDMStagiaires(DataModel<Stagiaire> lDMStagiaires) {
		this.lDMStagiaires = lDMStagiaires;
	}

	public DataModel<Session> getlDMSessions() {
			lDMSessions = new ListDataModel<Session>();
			lDMSessions.setWrappedData(sessionEJB.findAll());
			System.out.println("testsession");
		return lDMSessions;
	}

	public void setlDMSessions(DataModel<Session> lDMSessions) {
		this.lDMSessions = lDMSessions;
	}

	public List<SelectItem> getSelectStagiaire() {
		if (selectStagiaire == null) {
			List<Stagiaire> listStagiaires = new ArrayList<Stagiaire>();
			listStagiaires = stagiaireEJB.findAll();
			selectStagiaire = new ArrayList<SelectItem>();
			for (Stagiaire stagiaire : listStagiaires) {
				selectStagiaire.add(new SelectItem(stagiaire.getId(), stagiaire
						.getNom() + " " + stagiaire.getPrenom()));
			}
		}
		return selectStagiaire;
	}

	public void setSelectStagiaire(List<SelectItem> selectStagiaire) {
		this.selectStagiaire = selectStagiaire;
	}

	public List<Stagiaire> getListStagiaires() {
		return listStagiaires;
	}

	public void setListStagiaires(List<Stagiaire> listStagiaires) {
		this.listStagiaires = listStagiaires;
	}

	public List<Session> getListSessions() {
		return listSessions;
	}

	public void setListSessions(List<Session> listSessions) {
		this.listSessions = listSessions;
	}

	public EvaluationFormationEJB getQuestionnaireSessionEJB() {
		return evaluationFormationEJB;
	}

	public void setQuestionnaireSessionEJB(
			EvaluationFormationEJB evaluationFormationEJB) {
		this.evaluationFormationEJB = evaluationFormationEJB;
	}

	public QuestionnaireEJB getQuestionnaireEJB() {
		return questionnaireEJB;
	}

	public void setQuestionnaireEJB(QuestionnaireEJB questionnaireEJB) {
		this.questionnaireEJB = questionnaireEJB;
	}

	public EvaluationFormationEJB getEvaluationFormationEJB() {
		return evaluationFormationEJB;
	}

	public void setEvaluationFormationEJB(
			EvaluationFormationEJB evaluationFormationEJB) {
		this.evaluationFormationEJB = evaluationFormationEJB;
	}

	public StagiaireSessionEJB getStagiaireSessionEJB() {
		return stagiaireSessionEJB;
	}

	public void setStagiaireSessionEJB(StagiaireSessionEJB stagiaireSessionEJB) {
		this.stagiaireSessionEJB = stagiaireSessionEJB;
	}

	public FormationSessionEJB getFormationSessionEJB() {
		return formationSessionEJB;
	}

	public void setFormationSessionEJB(FormationSessionEJB formationSessionEJB) {
		this.formationSessionEJB = formationSessionEJB;
	}

	public StagiaireEJB getStagiaireEJB() {
		return stagiaireEJB;
	}

	public FormationEJB getFormationEJB() {
		return formationEJB;
	}

	public void setFormationEJB(FormationEJB formationEJB) {
		this.formationEJB = formationEJB;
	}

	public EvaluationEJB getEvaluationEJB() {
		return evaluationEJB;
	}

	public void setEvaluationEJB(EvaluationEJB evaluationEJB) {
		this.evaluationEJB = evaluationEJB;
	}

	public void setStagiaireEJB(StagiaireEJB stagiaireEJB) {
		this.stagiaireEJB = stagiaireEJB;
	}

	public SessionEJB getSessionEJB() {
		return sessionEJB;
	}

	public void setSessionEJB(SessionEJB sessionEJB) {
		this.sessionEJB = sessionEJB;
	}

	public PKEvaluationFormation getpKQuestionnaireSession() {
		return pKEvaluationFormation;
	}

	public void setpKQuestionnaireSession(
			PKEvaluationFormation pKEvaluationFormation) {
		this.pKEvaluationFormation = pKEvaluationFormation;
	}

	public PKFormationSession getpKFormationSession() {
		return pKFormationSession;
	}

	public void setpKFormationSession(PKFormationSession pKFormationSession) {
		this.pKFormationSession = pKFormationSession;
	}

	public PKEvaluationFormation getpKEvaluationFormation() {
		return pKEvaluationFormation;
	}

	public void setpKEvaluationFormation(
			PKEvaluationFormation pKEvaluationFormation) {
		this.pKEvaluationFormation = pKEvaluationFormation;
	}

	public PKStagiaireSession getpKStagiaireSession() {
		return pKStagiaireSession;
	}

	public void setpKStagiaireSession(PKStagiaireSession pKStagiaireSession) {
		this.pKStagiaireSession = pKStagiaireSession;
	}

	public PKQuestionnaire getpKQuestionnaire() {
		return pKQuestionnaire;
	}

	public void setpKQuestionnaire(PKQuestionnaire pKQuestionnaire) {
		this.pKQuestionnaire = pKQuestionnaire;
	}

	public EvaluationFormation getQuestionnaireSession() {
		return evaluationFormation;
	}

	public void setQuestionnaireSession(EvaluationFormation evaluationFormation) {
		this.evaluationFormation = evaluationFormation;
	}

	public FormationSession getFormationSession() {
		return formationSession;
	}

	public void setFormationSession(FormationSession formationSession) {
		this.formationSession = formationSession;
	}

	public EvaluationFormation getEvaluationFormation() {
		return evaluationFormation;
	}

	public void setEvaluationFormation(EvaluationFormation evaluationFormation) {
		this.evaluationFormation = evaluationFormation;
	}

	public StagiaireSession getStagiaireSession() {
		return stagiaireSession;
	}

	public void setStagiaireSession(StagiaireSession stagiaireSession) {
		this.stagiaireSession = stagiaireSession;
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

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

}
