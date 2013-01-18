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

import fr.treeptik.model.Evaluation;
import fr.treeptik.model.Formateur;
import fr.treeptik.model.Formation;
import fr.treeptik.model.FormationSession;
import fr.treeptik.model.PKFormateurFormation;
import fr.treeptik.model.PKFormationSession;
import fr.treeptik.model.PKQuestionnaire;
import fr.treeptik.model.PKResultat;
import fr.treeptik.model.PKStagiaireSession;
import fr.treeptik.model.Question;
import fr.treeptik.model.Questionnaire;
import fr.treeptik.model.QuestionnaireSession;
import fr.treeptik.model.Resultat;
import fr.treeptik.model.Session;
import fr.treeptik.model.Stagiaire;
import fr.treeptik.model.StagiaireSession;
import fr.treeptik.service.EvaluationEJB;
import fr.treeptik.service.FormateurEJB;
import fr.treeptik.service.FormateurFormationEJB;
import fr.treeptik.service.FormationEJB;
import fr.treeptik.service.FormationSessionEJB;
import fr.treeptik.service.QuestionEJB;
import fr.treeptik.service.QuestionnaireEJB;
import fr.treeptik.service.QuestionnaireSessionEJB;
import fr.treeptik.service.ResultatEJB;
import fr.treeptik.service.SessionEJB;
import fr.treeptik.service.StagiaireEJB;
import fr.treeptik.service.StagiaireSessionEJB;

@ManagedBean
@SessionScoped
public class EvaluationController {

	// *********ENTITE******************************************************
	private Evaluation evaluation = new Evaluation();
	private Question question = new Question();
	private Resultat resultat = new Resultat();
	private Session session = new Session();
	private Formateur formateur = new Formateur();
	private Formation formation = new Formation();
	private Questionnaire questionnaire = new Questionnaire();
	private QuestionnaireSession questionnaireSession = new QuestionnaireSession();
	private Stagiaire stagiaire;
	private StagiaireSession stagiaireSession = new StagiaireSession();
	private FormationSession formationSession = new FormationSession();
	private PKQuestionnaire pkQuestionnaire = new PKQuestionnaire();
	private PKStagiaireSession pKStagiaireSession = new PKStagiaireSession();
	private PKFormationSession pKFormationSession = new PKFormationSession();
	private PKFormateurFormation pKFormateurFormation = new PKFormateurFormation();
	private PKResultat pKResultat = new PKResultat();

	// *********EJB*********************************************************
	@EJB
	private EvaluationEJB evaluationEJB;
	@EJB
	private SessionEJB sessionEJB;
	@EJB
	private ResultatEJB resultatEJB;
	@EJB
	private QuestionEJB questionEJB;
	@EJB
	private StagiaireEJB stagiaireEJB;
	@EJB
	private FormationEJB formationEJB;
	@EJB
	private FormateurEJB formateurEJB;
	@EJB
	private QuestionnaireEJB questionnaireEJB;
	@EJB
	private StagiaireSessionEJB stagiaireSessionEJB;
	@EJB
	private FormationSessionEJB formationSessionEJB;
	@EJB
	private FormateurFormationEJB formateurFormationEJB;
	@EJB
	private QuestionnaireSessionEJB questionnaireSessionEJB;

	@EJB
	private SendTextMessage gestionmail;

	// **********LISTES*****************************************************
	private List<Evaluation> listEvaluations = new ArrayList<Evaluation>();
	private List<SelectItem> selectSessionsOfStagiaire;
	private List<SelectItem> selectFormationsOfSession;
	private List<SelectItem> selectFormateursOfFormation;
	private List<Resultat> listResultatsOfSession;

	// **********DATAMODEL**************************************************
	@SuppressWarnings("rawtypes")
	private DataModel lDMEvaluations;
	@SuppressWarnings("rawtypes")
	private DataModel lDMQuestions;
	@SuppressWarnings("rawtypes")
	private DataModel lDMQuestionsOfEval;
	@SuppressWarnings("rawtypes")
	private DataModel lDMResultatsOfSession;
	@SuppressWarnings("rawtypes")
	private DataModel lDMResultats;
	@SuppressWarnings("rawtypes")
	private DataModel lDMSessions;

	public String doSelectAddQuestion() {
		evaluation = (Evaluation) lDMEvaluations.getRowData();
		return "listQuestionsToAdd";
	}

	public String doAddQuestionToEval() {
		try {
			question = (Question) lDMQuestions.getRowData();
			pkQuestionnaire.setEvaluation(evaluation);
			pkQuestionnaire.setQuestion(question);
			questionnaire.setId(pkQuestionnaire);
			questionnaireEJB.addQuestionToEval(questionnaire);
			return "listEvaluations";
		} catch (Exception e) {
			return "messageQuestionDejaAjoutee";
		}

	}

	/*
	 * public String doFillEval() { try { evaluationEJB.create(evaluation);
	 * gestionmail.mailRecapEvaluation(evaluation); return
	 * "messageEvaluationEffectue"; } catch (Exception e) { return
	 * "messageEvaluationDejaEffectuee"; } }
	 */
	public String doNew() {
		evaluation = new Evaluation();
		getSelectSessionsOfStagiaire();
		return "selectSession";
	}

	public String identifier() {
		stagiaire = stagiaireEJB.findStagiaireByEmail(getRequest()
				.getUserPrincipal().toString());
		return "acceuilStagiaire";
	}

	public String chooseSession() {
		List<Resultat> listTempRes = new ArrayList<Resultat>();
		listTempRes = createResultat();

		for (Resultat resultat : listTempRes) {
			Resultat resultattemp = new Resultat();
			resultattemp = resultat;
			resultattemp.setNote(0);
			resultatEJB.create(resultattemp);
		}
		getSelectFormationsOfSession();
		getListResultatsOfSession();
		getlDMResultatsOfSession();
		return "selectFormation";
	}

	private List<Resultat> createResultat() {

		session = sessionEJB.findById(session.getId());
		pKStagiaireSession.setSession(session);
		pKStagiaireSession.setStagiaire(stagiaire);
		stagiaireSession = stagiaireSessionEJB.findById(pKStagiaireSession);

		List<Questionnaire> listTempQuestionnaire = new ArrayList<Questionnaire>();
		List<Resultat> listTempResultat = new ArrayList<Resultat>();
		listTempQuestionnaire = questionnaireSessionEJB
				.findAllQuestionnairesOfSession(session);

		for (Questionnaire questionnaire : listTempQuestionnaire) {
			Questionnaire tempQuestionnaire = new Questionnaire();
			Resultat tempResultat = new Resultat();
			PKResultat tempPKresultat = new PKResultat();
			tempQuestionnaire = questionnaire;
			tempPKresultat.setStagiaireSession(stagiaireSession);
			tempPKresultat.setQuestionnaire(tempQuestionnaire);
			tempResultat.setId(tempPKresultat);
			listTempResultat.add(tempResultat);
		}
		return listTempResultat;
	}

	public String chooseFormation() {
		formation = formationEJB.findById(formation.getId());
		getSelectFormateursOfFormation();
		return "selectFormateur";
	}

	public String chooseFormateur() {
		formateur = formateurEJB.findById(formateur.getId());
		return "doEvaluation2";
	}

	@SuppressWarnings("rawtypes")
	public DataModel getlDMResultatsOfSession() {
		if (lDMResultatsOfSession == null) {

			System.out.println("session1 " + session.getId());
			lDMResultatsOfSession = new ListDataModel();
			lDMResultatsOfSession.setWrappedData(resultatEJB
					.findAllResultatsOfSession(session));
		}
		return lDMQuestionsOfEval;
	}

	@SuppressWarnings("rawtypes")
	public void setlDMResultatsOfSession(DataModel lDMResultatsOfSession) {
		this.lDMResultatsOfSession = lDMResultatsOfSession;
	}

	public List<Resultat> getListResultatsOfSession() {
		listResultatsOfSession = resultatEJB.findAllResultatsOfSession(session);
		return listResultatsOfSession;
	}

	public void setListResultatsOfSession(List<Resultat> listResultatsOfSession) {
		this.listResultatsOfSession = listResultatsOfSession;
	}

	public String doListQuestionOfEval() {
		evaluation = (Evaluation) lDMEvaluations.getRowData();
		getlDMQuestionsOfEval();
		return "listQuestionsOfEval";
	}

	@SuppressWarnings("rawtypes")
	public DataModel getlDMQuestionsOfEval() {
		if (lDMQuestionsOfEval == null) {
			lDMQuestionsOfEval = new ListDataModel();
			lDMQuestionsOfEval.setWrappedData(questionnaireEJB
					.findAllQuestionsOfEval(evaluation));
		}
		return lDMQuestionsOfEval;
	}

	@SuppressWarnings("rawtypes")
	public void setlDMQuestionsOfEval(DataModel lDMQuestionsOfEval) {
		this.lDMQuestionsOfEval = lDMQuestionsOfEval;
	}

	public String doFillEval() {

		do {
			Resultat tempres = new Resultat();
			tempres = resultat;
			System.out.println("tempres"+tempres.toString());
			List<Resultat> templistresultat = new ArrayList<Resultat>();
			templistresultat.add(tempres);
			System.out.println("listsize :"+templistresultat.size());
		} while (resultat != null);
/*		
		try {
			evaluationEJB.create(evaluation);
			gestionmail.mailRecapEvaluation(evaluation);
			return "messageEvaluationEffectue";
		} catch (Exception e) {
			return "messageEvaluationDejaEffectue";
		}
*/	
		return "messageEvaluationDejaEffectue";
	}

	@SuppressWarnings("rawtypes")
	public String doCreate() {
		evaluationEJB.create(evaluation);
		lDMEvaluations = new ListDataModel();
		lDMEvaluations.setWrappedData(evaluationEJB.findAll());
		return "messageEvaluationCreee";

	}

	@SuppressWarnings("rawtypes")
	public String doDelete() {
		Evaluation evaluation = (Evaluation) lDMEvaluations.getRowData();
		evaluationEJB.delete(evaluation);
		lDMEvaluations = new ListDataModel();
		lDMEvaluations.setWrappedData(evaluationEJB.findAll());
		return "listEvaluations";
	}

	public String doSelectUpdate() {
		evaluation = (Evaluation) lDMEvaluations.getRowData();
		return "updateEvaluation";
	}

	@SuppressWarnings("rawtypes")
	public String doUpdate() {
		evaluationEJB.update(evaluation);
		lDMEvaluations = new ListDataModel();
		lDMEvaluations.setWrappedData(evaluationEJB.findAll());
		return "messageEvaluationUpdate";
	}

	// **********Méthodes pour remplir les Items des SelectOneMenu************
	public List<SelectItem> getSelectSessionsOfStagiaire() {
		if (selectSessionsOfStagiaire == null) {
			selectSessionsOfStagiaire = new ArrayList<SelectItem>();
			List<Session> allSessions = stagiaireSessionEJB
					.findAllSessionsOfStagiaire(stagiaire);
			for (Session session : allSessions) {
				selectSessionsOfStagiaire.add(new SelectItem(session.getId(),
						session.getNom()));
			}
		}
		return selectSessionsOfStagiaire;
	}

	public void setSelectSessionsOfStagiaire(
			List<SelectItem> selectSessionsOfStagiaire) {
		this.selectSessionsOfStagiaire = selectSessionsOfStagiaire;
	}

	public List<SelectItem> getSelectFormationsOfSession() {
		if (selectFormationsOfSession == null) {
			selectFormationsOfSession = new ArrayList<SelectItem>();
			List<Formation> allFormations = formationSessionEJB
					.findAllFormationsOfSession(session);
			for (Formation formation : allFormations) {
				selectFormationsOfSession.add(new SelectItem(formation.getId(),
						formation.getNom()));
			}
		}
		return selectFormationsOfSession;
	}

	public void setSelectFormationsOfSession(List<SelectItem> selectFormation) {
		this.selectFormationsOfSession = selectFormation;
	}

	public List<SelectItem> getSelectFormateursOfFormation() {
		if (selectFormateursOfFormation == null) {
			selectFormateursOfFormation = new ArrayList<SelectItem>();
			List<Formateur> allFormateurs = formateurFormationEJB
					.findAllFormateursOfFormation(formation);
			for (Formateur formateur : allFormateurs) {
				selectFormateursOfFormation.add(new SelectItem(formateur
						.getId(), formateur.getNom()));
			}
		}
		return selectFormateursOfFormation;
	}

	public void setSelectFormateursOfFormation(List<SelectItem> selectFormateur) {
		this.selectFormateursOfFormation = selectFormateur;
	}

	// *********ENTITE******************************************************
	public void setQuestion(Question question) {
		this.question = question;
	}

	public Resultat getResultat() {
		return resultat;
	}

	public void setResultat(Resultat resultat) {
		this.resultat = resultat;
	}

	public PKQuestionnaire getPkEvaluationQuestion() {
		return pkQuestionnaire;
	}

	public void setPkEvaluationQuestion(PKQuestionnaire pkEvaluationQuestion) {
		this.pkQuestionnaire = pkEvaluationQuestion;
	}

	public ResultatEJB getResultatEJB() {
		return resultatEJB;
	}

	public void setResultatEJB(ResultatEJB resultatEJB) {
		this.resultatEJB = resultatEJB;
	}

	public SendTextMessage getGestionmail() {
		return gestionmail;
	}

	public void setGestionmail(SendTextMessage gestionmail) {
		this.gestionmail = gestionmail;
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

	public Question getQuestion() {
		return question;
	}

	public StagiaireSession getStagiaireSession() {
		return stagiaireSession;
	}

	public void setStagiaireSession(StagiaireSession stagiaireSession) {
		this.stagiaireSession = stagiaireSession;
	}

	// ***************EJB**********************
	public EvaluationEJB getEvaluationEJB() {
		return evaluationEJB;
	}

	public void setEvaluationEJB(EvaluationEJB evaluationEJB) {
		this.evaluationEJB = evaluationEJB;
	}

	public QuestionnaireSession getQuestionnaireSession() {
		return questionnaireSession;
	}

	public void setQuestionnaireSession(
			QuestionnaireSession questionnaireSession) {
		this.questionnaireSession = questionnaireSession;
	}

	public QuestionnaireSessionEJB getQuestionnaireSessionEJB() {
		return questionnaireSessionEJB;
	}

	public void setQuestionnaireSessionEJB(
			QuestionnaireSessionEJB questionnaireSessionEJB) {
		this.questionnaireSessionEJB = questionnaireSessionEJB;
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

	public QuestionEJB getQuestionEJB() {
		return questionEJB;
	}

	public void setQuestionEJB(QuestionEJB questionEJB) {
		this.questionEJB = questionEJB;
	}

	// *************LIST ET DATAMODEL***********
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
	public DataModel getlDMQuestions() {
		if (lDMQuestions == null) {
			lDMQuestions = new ListDataModel();
			lDMQuestions.setWrappedData(questionEJB.findAll());
		}
		return lDMQuestions;
	}

	@SuppressWarnings("rawtypes")
	public void setlDMQuestions(DataModel lDMQuestions) {
		this.lDMQuestions = lDMQuestions;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getlDMSessions() {
		if (lDMSessions == null) {
			lDMSessions = new ListDataModel();
			lDMSessions.setWrappedData(sessionEJB.findAll());
		}
		return lDMSessions;
	}

	@SuppressWarnings("rawtypes")
	public void setlDMSessions(DataModel lDMSessions) {
		this.lDMSessions = lDMSessions;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getlDMResultats() {
		if (lDMResultats == null) {
			lDMResultats = new ListDataModel();
			lDMResultats.setWrappedData(resultatEJB.findAll());
		}
		return lDMResultats;
	}

	@SuppressWarnings("rawtypes")
	public void setlDMResultats(DataModel lDMResultats) {
		this.lDMResultats = lDMResultats;
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public FormationSession getFormationSession() {
		return formationSession;
	}

	public void setFormationSession(FormationSession formationSession) {
		this.formationSession = formationSession;
	}

	public PKQuestionnaire getPkQuestionnaire() {
		return pkQuestionnaire;
	}

	public void setPkQuestionnaire(PKQuestionnaire pkQuestionnaire) {
		this.pkQuestionnaire = pkQuestionnaire;
	}

	public PKStagiaireSession getpKStagiaireSession() {
		return pKStagiaireSession;
	}

	public void setpKStagiaireSession(PKStagiaireSession pKStagiaireSession) {
		this.pKStagiaireSession = pKStagiaireSession;
	}

	public PKFormationSession getpKFormationSession() {
		return pKFormationSession;
	}

	public void setpKFormationSession(PKFormationSession pKFormationSession) {
		this.pKFormationSession = pKFormationSession;
	}

	public PKFormateurFormation getpKFormateurFormation() {
		return pKFormateurFormation;
	}

	public void setpKFormateurFormation(
			PKFormateurFormation pKFormateurFormation) {
		this.pKFormateurFormation = pKFormateurFormation;
	}

	public PKResultat getpKResultat() {
		return pKResultat;
	}

	public void setpKResultat(PKResultat pKResultat) {
		this.pKResultat = pKResultat;
	}

	public QuestionnaireEJB getQuestionnaireEJB() {
		return questionnaireEJB;
	}

	public void setQuestionnaireEJB(QuestionnaireEJB questionnaireEJB) {
		this.questionnaireEJB = questionnaireEJB;
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

	public FormateurFormationEJB getFormateurFormationEJB() {
		return formateurFormationEJB;
	}

	public void setFormateurFormationEJB(
			FormateurFormationEJB formateurFormationEJB) {
		this.formateurFormationEJB = formateurFormationEJB;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getlDMEvaluations() {
		if (lDMEvaluations == null) {
			lDMEvaluations = new ListDataModel();
			lDMEvaluations.setWrappedData(evaluationEJB.findAll());
		}
		return lDMEvaluations;
	}

	public void setlDMEvaluations(DataModel lDMEvaluations) {
		this.lDMEvaluations = lDMEvaluations;
	}

	// ************UTILE AVEC JPA???????*********
	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

}
