package fr.treeptik.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import org.primefaces.event.ToggleEvent;

import fr.treeptik.model.Evaluation;
import fr.treeptik.model.Formation;
import fr.treeptik.model.FormationSession;
import fr.treeptik.model.PKFormationSession;
import fr.treeptik.model.PKQuestionnaire;
import fr.treeptik.model.PKQuestionnaireSession;
import fr.treeptik.model.PKStagiaireSession;
import fr.treeptik.model.Questionnaire;
import fr.treeptik.model.QuestionnaireSession;
import fr.treeptik.model.Session;
import fr.treeptik.model.Stagiaire;
import fr.treeptik.model.StagiaireSession;
import fr.treeptik.service.EvaluationEJB;
import fr.treeptik.service.FormationEJB;
import fr.treeptik.service.FormationSessionEJB;
import fr.treeptik.service.QuestionnaireEJB;
import fr.treeptik.service.QuestionnaireSessionEJB;
import fr.treeptik.service.SessionEJB;
import fr.treeptik.service.StagiaireEJB;
import fr.treeptik.service.StagiaireSessionEJB;

@ManagedBean
public class SessionController {

	// *********ENTITE******************************************************
	private Session session = new Session();
	private Formation formation = new Formation();
	private Stagiaire stagiaire = new Stagiaire();
	private Evaluation evaluation = new Evaluation();
	private Questionnaire questionnaire = new Questionnaire();
	private StagiaireSession stagiaireSession = new StagiaireSession();
	private FormationSession formationSession = new FormationSession();
	private QuestionnaireSession questionnaireSession = new QuestionnaireSession();
	private PKFormationSession pKFormationSession = new PKFormationSession();
	private PKStagiaireSession pKStagiaireSession = new PKStagiaireSession();
	private PKQuestionnaire pKQuestionnaire = new PKQuestionnaire();
	private PKQuestionnaireSession pKQuestionnaireSession = new PKQuestionnaireSession();

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
	private QuestionnaireSessionEJB questionnaireSessionEJB;

	// **********LISTES*****************************************************
	private List<Session> listSessions = new ArrayList<Session>();
	private List<Stagiaire> listStagiaires = new ArrayList<Stagiaire>();
	private List<QuestionnaireSession> listTest;
	private List<SelectItem> selectStagiaire;
	// private List<SelectItem> selectSession;

	// **********DATAMODEL**************************************************
	@SuppressWarnings("rawtypes")
	private DataModel lDMSessions;
	@SuppressWarnings("rawtypes")
	private DataModel lDMStagiaires;
	@SuppressWarnings("rawtypes")
	private DataModel lDMFormations;
	@SuppressWarnings("rawtypes")
	private DataModel lDMEvaluations;
	@SuppressWarnings("rawtypes")
	private DataModel lDMStagiairesOfSession;
	@SuppressWarnings("rawtypes")
	private DataModel lDMFormationsOfSession;
	@SuppressWarnings("rawtypes")
	private DataModel lDMEvaluationsOfSession;

	public String doSelectlinkToEval() {
		session = (Session) lDMSessions.getRowData();
		return "listEvalsToLink";
	}

	public String doLinkSessionToEval() {
		List<QuestionnaireSession> listTest = new ArrayList<QuestionnaireSession>();
		listTest = linkSessionToEval();
		for (QuestionnaireSession questionnaireSession : listTest) {
			QuestionnaireSession questsess = new QuestionnaireSession();
			questsess = questionnaireSession;
			questionnaireSessionEJB.create(questsess);
		}
		return "listSessions";
	}

	public List<QuestionnaireSession> linkSessionToEval() {
		evaluation = (Evaluation) lDMEvaluations.getRowData();
		List<Questionnaire> listTempQuestionnaire = new ArrayList<Questionnaire>();
		List<QuestionnaireSession> listTempQuestionaireSession = new ArrayList<QuestionnaireSession>();
		listTempQuestionnaire = questionnaireEJB
				.findQuestionnaireByEval(evaluation);
		for (Questionnaire questionnaire : listTempQuestionnaire) {
			Questionnaire tempQuestionnaire = new Questionnaire();
			QuestionnaireSession tempQuestionRSession = new QuestionnaireSession();
			PKQuestionnaireSession tempPkQuestionnaireSession = new PKQuestionnaireSession();
			
			tempQuestionnaire = questionnaire;
			tempPkQuestionnaireSession.setQuestionnaire(tempQuestionnaire);
			tempPkQuestionnaireSession.setSession(session);
			tempQuestionRSession.setId(tempPkQuestionnaireSession);
			listTempQuestionaireSession.add(tempQuestionRSession);
		}
		return listTempQuestionaireSession;
	}

	public String doCreate() {
		sessionEJB.create(session);
		return "messageSessionCreee";
	}

	public String doListStagiaireOfSession() {
		session = (Session) lDMSessions.getRowData();
		getlDMStagiairesOfSession();
		return "listStagiairesOfSession";
	}

	public String doListFormationOfSession() {
		session = (Session) lDMSessions.getRowData();
		getlDMFormationsOfSession();
		return "listFormationsOfSession";
	}

	public String doListEvaluationsOfSession() {
		session = (Session) lDMSessions.getRowData();
		getlDMEvaluationsOfSession();
		return "listEvaluationsOfSession";
	}
	
	@SuppressWarnings("rawtypes")
	public DataModel getlDMStagiairesOfSession() {
		if (lDMStagiairesOfSession == null) {
			lDMStagiairesOfSession = new ListDataModel();
			lDMStagiairesOfSession.setWrappedData(stagiaireSessionEJB
					.findAllStagiairesOfSession(session));
		}
		return lDMStagiairesOfSession;
	}

	@SuppressWarnings("rawtypes")
	public void setlDMStagiairesOfSession(DataModel lDMStagiairesOfSession) {
		this.lDMStagiairesOfSession = lDMStagiairesOfSession;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getlDMStagiaires() {
		if (lDMStagiaires == null) {
			lDMStagiaires = new ListDataModel();
			lDMStagiaires.setWrappedData(stagiaireEJB.findAll());
		}
		return lDMStagiaires;
	}

	@SuppressWarnings("rawtypes")
	public void setlDMStagiaires(DataModel lDMStagiaires) {
		this.lDMStagiaires = lDMStagiaires;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getlDMEvaluations() {
		if (lDMEvaluations == null) {
			lDMEvaluations = new ListDataModel();
			lDMEvaluations.setWrappedData(evaluationEJB.findAll());
		}
		return lDMEvaluations;
	}

	@SuppressWarnings("rawtypes")
	public void setlDMEvaluations(DataModel lDMevaluations) {
		this.lDMEvaluations = lDMevaluations;
	}

	public FormationSession getFormationSession() {
		return formationSession;
	}

	public void setFormationSession(FormationSession formationSession) {
		this.formationSession = formationSession;
	}

	public FormationSessionEJB getFormationSessionEJB() {
		return formationSessionEJB;
	}

	public void setFormationSessionEJB(FormationSessionEJB formationSessionEJB) {
		this.formationSessionEJB = formationSessionEJB;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getlDMFormations() {
		if (lDMFormations == null) {
			lDMFormations = new ListDataModel();
			lDMFormations.setWrappedData(formationEJB.findAll());
		}
		return lDMFormations;
	}

	@SuppressWarnings("rawtypes")
	public void setlDMFormations(DataModel lDMFormations) {
		this.lDMFormations = lDMFormations;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getlDMFormationsOfSession() {
		if (lDMFormationsOfSession == null) {
			lDMFormationsOfSession = new ListDataModel();
			lDMFormationsOfSession.setWrappedData(formationSessionEJB
					.findAllFormationsOfSession(session));
		}
		return lDMFormationsOfSession;
	}

	public void setlDMFormationsOfSession(DataModel<?> lDMFormationsOfSession) {
		this.lDMFormationsOfSession = lDMFormationsOfSession;
	}

	public DataModel getlDMEvaluationsOfSession() {
		if (lDMEvaluationsOfSession == null) {
			lDMEvaluationsOfSession = new ListDataModel();
			lDMEvaluationsOfSession.setWrappedData(questionnaireSessionEJB.findAllEvalsOfSession(session));
		}
		return lDMEvaluationsOfSession;
	}

	public void setlDMEvaluationsOfSession(DataModel lDMEvaluationsOfSession) {
		this.lDMEvaluationsOfSession = lDMEvaluationsOfSession;
	}

	@SuppressWarnings("rawtypes")
	public String doDelete() {
		Session session = (Session) lDMSessions.getRowData();
		sessionEJB.delete(session);
		lDMSessions = new ListDataModel();
		lDMSessions.setWrappedData(sessionEJB.findAll());
		return "listSessions";
	}

	public String doSelectUpdate() {
		session = (Session) lDMSessions.getRowData();
		return "updateSession";
	}

	public String doSelectAddStagiaire() {
		session = (Session) lDMSessions.getRowData();
		return "listStagiairesToAdd";
	}

	public String doSelectAddFormation() {
		session = (Session) lDMSessions.getRowData();
		return "listFormationsToAdd";
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

	@SuppressWarnings("rawtypes")
	public String doUpdate() {
		sessionEJB.update(session);
		lDMSessions = new ListDataModel();
		lDMSessions.setWrappedData(sessionEJB.findAll());
		return "messageSessionUpdate";
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

	public String doFindAll() {
		listSessions = sessionEJB.findAll();
		return "listSessions";
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public List<Session> getListSessions() {
		return listSessions;
	}

	public void setListSessions(List<Session> listSessions) {
		this.listSessions = listSessions;
	}

	public List<Stagiaire> getListStagiaires() {
		return listStagiaires;
	}

	public void setListStagiaires(List<Stagiaire> listStagiaires) {
		this.listStagiaires = listStagiaires;
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

	@SuppressWarnings("rawtypes")
	public void setlDMSessions(DataModel lDMSessions) {
		this.lDMSessions = lDMSessions;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getlDMSessions() {
		if (lDMSessions == null) {
			lDMSessions = new ListDataModel();
			lDMSessions.setWrappedData(sessionEJB.findAll());
		}
		return lDMSessions;
	}

	public StagiaireSessionEJB getStagiaireSessionEJB() {
		return stagiaireSessionEJB;
	}

	public void setStagiaireSessionEJB(StagiaireSessionEJB stagiaireSessionEJB) {
		this.stagiaireSessionEJB = stagiaireSessionEJB;
	}

	public StagiaireSession getStagiaireSession() {
		return stagiaireSession;
	}

	public void setStagiaireSession(StagiaireSession stagiaireSession) {
		this.stagiaireSession = stagiaireSession;
	}

	// *********TEST PRIMEFACES**************

	public void onRowToggle(ToggleEvent event) {
		session = (Session) lDMSessions.getRowData();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"ligne :" + event.getVisibility(), "Session:"
						+ ((Session) event.getData()).getNom());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
