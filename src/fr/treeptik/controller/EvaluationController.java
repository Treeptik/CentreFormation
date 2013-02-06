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
import javax.persistence.OrderBy;
import javax.servlet.http.HttpServletRequest;

import fr.treeptik.model.Evaluation;
import fr.treeptik.model.EvaluationFormation;
import fr.treeptik.model.Formateur;
import fr.treeptik.model.FormateurFormation;
import fr.treeptik.model.Formation;
import fr.treeptik.model.FormationSession;
import fr.treeptik.model.PKFormateurFormation;
import fr.treeptik.model.PKFormationSession;
import fr.treeptik.model.PKQuestionnaire;
import fr.treeptik.model.PKResultat;
import fr.treeptik.model.PKStagiaireSession;
import fr.treeptik.model.Question;
import fr.treeptik.model.Questionnaire;
import fr.treeptik.model.Resultat;
import fr.treeptik.model.Session;
import fr.treeptik.model.Stagiaire;
import fr.treeptik.model.StagiaireSession;
import fr.treeptik.service.EvaluationEJB;
import fr.treeptik.service.EvaluationFormationEJB;
import fr.treeptik.service.FormateurEJB;
import fr.treeptik.service.FormateurFormationEJB;
import fr.treeptik.service.FormationEJB;
import fr.treeptik.service.FormationSessionEJB;
import fr.treeptik.service.QuestionEJB;
import fr.treeptik.service.QuestionnaireEJB;
import fr.treeptik.service.ResultatEJB;
import fr.treeptik.service.SessionEJB;
import fr.treeptik.service.StagiaireEJB;
import fr.treeptik.service.StagiaireSessionEJB;

@ManagedBean
@SessionScoped
public class EvaluationController {

	private int compteurFormation;

	// *********ENTITE******************************************************
	private Evaluation evaluation = new Evaluation();
	private Stagiaire stagiaire;
	private Question question = new Question();
	private Resultat resultat = new Resultat();
	private Session session = new Session();
	private Formateur formateur = new Formateur();
	private Formation formation = new Formation();
	private Questionnaire questionnaire = new Questionnaire();

	private EvaluationFormation evaluationFormation = new EvaluationFormation();
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
	private EvaluationFormationEJB evaluationFormationEJB;
	@EJB
	private SendTextMessage sendTextMessage;

	// **********LISTES*****************************************************
	private List<Evaluation> listEvaluations;
	private List<SelectItem> selectSessionsOfStagiaire;
	private List<SelectItem> selectFormationsOfSession;
	private List<SelectItem> selectFormateursOfFormation;
	private List<SelectItem> selectFormateursOfSession;

	private List<Formateur> listFormateursEvalues;
	private List<Formateur> listChoixDeFormateursDeLaSession = new ArrayList<>();
	private List<Formation> listFormationsOfSession;

	private List<Question> listQuestionsOfSession = new ArrayList<>();
	private List<Evaluation> listEvaluationsOfSession = new ArrayList<>();
	private List<Questionnaire> listQuestionnairesOfSession = new ArrayList<>();

	private List<Resultat> listResultatsOfStagiaireForSession;
	private List<Resultat> listResultatsOfEvaluation = new ArrayList<Resultat>();
	@OrderBy("id.questionnaire.id.question.ordreApparition ASC")
	private List<Resultat> listResultatsOfStagiaireForFormationOfSession;
	private List<Resultat> listResultatsTemporaire = new ArrayList<>();

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

	public String chooseFormateur() {

		for (Resultat resultat : createResultat()) {
			resultat.setNote(0);
			resultatEJB.create(resultat);
		}
		formation = listFormationsOfSession.get(0);
		getListResultatsOfStagiaireForFormationOfSession(); 
		return "doEvaluation2";
	}

	public String chooseSession() {
		getListFormationsOfSession(); // utile à la création des resultats dans chooseFormateur()
		return "selectFormateur";
	}

	private List<Resultat> createResultat() {
		List<Resultat> listResultatsOfStagiaireForSession = new ArrayList<>();
		stagiaireSession = stagiaireSessionEJB.findByStagiaireAndSession(stagiaire, session);
		listFormateursEvalues.add(formateurEJB
				.findFormateurByName("Evaluation"));
		
		for (Formation formation : listFormationsOfSession) {
			for (FormateurFormation formateurFormation : formateurFormationEJB
					.findAllFormateurFormationFromFormation(formation)) {
				if (listFormateursEvalues.contains(formateurFormation.getId()
						.getFormateur())) {
					for (Evaluation evaluation : evaluationFormationEJB
							.findAllEvalsOfFormation(formation)) {
						for (Questionnaire questionnaire : questionnaireEJB
								.findQuestionnaireByEval(evaluation)) {
							resultat = new Resultat();
							PKResultat tempPKresultat = new PKResultat();
							tempPKresultat
									.setFormateurFormation(formateurFormation);
							tempPKresultat
									.setStagiaireSession(stagiaireSession);
							tempPKresultat.setQuestionnaire(questionnaire);
							resultat.setId(tempPKresultat);
							listResultatsOfStagiaireForSession.add(resultat);
						}
					}
				}
			}

		}

		return listResultatsOfStagiaireForSession;
	}

	public String doFillEval() {

		if (compteurFormation < listFormationsOfSession.size() - 1) {
			for (Resultat resultat : listResultatsOfStagiaireForFormationOfSession) {
				listResultatsTemporaire.add(resultat);
			}
			compteurFormation++;
			formation = listFormationsOfSession.get(compteurFormation);
			getListResultatsOfStagiaireForFormationOfSession();
			return "doEvaluation2";
		} else {
			for (Resultat resultat : listResultatsOfStagiaireForFormationOfSession) {
				listResultatsTemporaire.add(resultat);
			}
			for (Resultat resultat : listResultatsTemporaire) {
				resultatEJB.update(resultat);
			}
			sendTextMessage.mailRecapEvaluation(listResultatsTemporaire,
					listFormateursEvalues, listFormationsOfSession);
			return "messageEvaluationEffectue";
		}
	}

	public List<Formateur> getListChoixDeFormateursDeLaSession() {
		for (Formation formation : listFormationsOfSession) {
			for (Formateur formateur : formateurFormationEJB
					.findAllFormateursOfFormation(formation)) {
				formateur = formateurEJB.findById(formateur.getId());
				if (!formateur.getNom().equals("Evaluation")) {
					listChoixDeFormateursDeLaSession.add(formateur);
				}
			}
		}
		return listChoixDeFormateursDeLaSession;
	}

	public void setListChoixDeFormateursDeLaSession(
			List<Formateur> listFormateursOfSession) {
		this.listChoixDeFormateursDeLaSession = listFormateursOfSession;
	}

	public List<Formation> getListFormationsOfSession() {
		listFormationsOfSession = formationSessionEJB
				.findAllFormationsOfSession(session);
		return listFormationsOfSession;
	}

	public void setListFormationsOfSession(
			List<Formation> listFormationsOfSession) {
		this.listFormationsOfSession = listFormationsOfSession;
	}

	public List<Resultat> getListResultatsOfStagiaireForSession() {
		listResultatsOfStagiaireForSession = resultatEJB.findAllResultatsOfStagiaireForSession(stagiaire, session);
		return listResultatsOfStagiaireForSession;
	}

	public void setListResultatsOfStagiaireForSession(List<Resultat> listResultatsOfStagiaireForSession) {
		this.listResultatsOfStagiaireForSession = listResultatsOfStagiaireForSession;
	}

	public List<Evaluation> getListEvaluationsOfSession() {
		for (Formation formation : formationSessionEJB
				.findAllFormationsOfSession(session)) {
			for (Evaluation evaluation : evaluationFormationEJB
					.findAllEvalsOfFormation(formation)) {
				evaluation = evaluationEJB.findById(evaluation.getId());
				listEvaluationsOfSession.add(evaluation);
			}
		}
		return listEvaluationsOfSession;
	}

	public void setListEvaluationsOfSession(
			List<Evaluation> listEvaluationsOfSession) {
		this.listEvaluationsOfSession = listEvaluationsOfSession;
	}

	public List<Resultat> getListResultatsOfEvaluation() {
		evaluation = evaluationEJB.findById(evaluation.getId());
		for (Resultat resultat : resultatEJB.findAllResultatsOfEval(evaluation)) {
			listResultatsOfEvaluation.add(resultat);
		}
		return listResultatsOfEvaluation;
	}

	public void setListResultatsOfEvaluation(
			List<Resultat> listResultatsOfEvaluation) {
		this.listResultatsOfEvaluation = listResultatsOfEvaluation;
	}

	public List<Resultat> getListResultatsOfStagiaireForFormationOfSession() {
		formation = formationEJB.findById(formation.getId());
		listResultatsOfStagiaireForFormationOfSession = resultatEJB
		 .findAllResultatsOfStagiaireForFormationOfSession(stagiaire, session, formation);
		return listResultatsOfStagiaireForFormationOfSession;
	}

	public void setListResultatsOfStagiaireForFormationOfSession(
			List<Resultat> listResultatsOfStagiaireForFormationOfSession) {
		this.listResultatsOfStagiaireForFormationOfSession = listResultatsOfStagiaireForFormationOfSession;
	}

	public List<Questionnaire> getListQuestionnairesOfSession() {

		for (Formation formation : formationSessionEJB
				.findAllFormationsOfSession(session)) {
			for (Evaluation evaluation : evaluationFormationEJB
					.findAllEvalsOfFormation(formation)) {
				for (Questionnaire questionnaire : questionnaireEJB
						.findQuestionnaireByEval(evaluation)) {
					listQuestionnairesOfSession.add(questionnaire);
				}
			}
		}
		return listQuestionnairesOfSession;
	}

	public void setListQuestionnairesOfSession(
			List<Questionnaire> listQuestionnairesOfSession) {
		this.listQuestionnairesOfSession = listQuestionnairesOfSession;
	}

	public String identifier() {
		stagiaire = stagiaireEJB.findStagiaireByEmail(getRequest()
				.getUserPrincipal().getName());
		return "selectSession";
	}

	public String doListQuestionOfEval() {
		evaluation = (Evaluation) lDMEvaluations.getRowData();
		getlDMQuestionsOfEval();
		return "listQuestionsOfEval";
	}

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

	public String doSelectRemoveQuestion() {
		evaluation = (Evaluation) lDMEvaluations.getRowData();
		return "listQuestionsOfEval";
	}

	public String doRemoveQuestionFromEval() {

		question = (Question) lDMQuestionsOfEval.getRowData();
		pkQuestionnaire.setEvaluation(evaluation);
		pkQuestionnaire.setQuestion(question);
		questionnaire = questionnaireEJB.findById(pkQuestionnaire);
		questionnaireEJB.delete(questionnaire);
		return "listEvaluations";
	}

	public List<Question> getListQuestionsOfSession() {
		session = sessionEJB.findById(session.getId());
		for (Formation formation : formationSessionEJB
				.findAllFormationsOfSession(session)) {
			for (Evaluation evaluation : evaluationFormationEJB
					.findAllEvalsOfFormation(formation)) {
				for (Question question : questionnaireEJB
						.findAllQuestionsOfEval(evaluation)) {
					listQuestionsOfSession.add(question);
				}
			}
		}
		return listQuestionsOfSession;
	}

	public void setListQuestionsOfSession(List<Question> listQuestionsOfSession) {
		this.listQuestionsOfSession = listQuestionsOfSession;
	}

	public List<Formateur> getListFormateursEvalues() {
		return listFormateursEvalues;
	}

	public void setListFormateursEvalues(List<Formateur> listFormateursEvalues) {
		this.listFormateursEvalues = listFormateursEvalues;
	}

	public List<Resultat> getListResultatsTemporaire() {
		return listResultatsTemporaire;
	}

	public void setListResultatsTemporaire(
			List<Resultat> listResultatsTemporaire) {
		this.listResultatsTemporaire = listResultatsTemporaire;
	}

	public Boolean doCheckAvecCommentaire(Question question) {
		return questionEJB.checkAvecCommentaire(question);
	}

	public Boolean doCheckQuestion4Choix(Question question) {
		return questionEJB.checkQuestion4Choix(question);
	}

	public Boolean doCheckQuestionFermee(Question question) {
		return questionEJB.checkQuestionFermee(question);
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

	public String doNew() {
		evaluation = new Evaluation();
		return "../Evaluation/createEvaluation.jsf";
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
			List<Formation> allFormationsOfSession = getListFormationsOfSession();
			for (Formation formation : allFormationsOfSession) {
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
		selectFormateursOfFormation = new ArrayList<SelectItem>();
		List<Formateur> allFormateurs = formateurFormationEJB
				.findAllFormateursOfFormation(formation);
		for (Formateur formateur : allFormateurs) {
			selectFormateursOfFormation.add(new SelectItem(formateur));
		}
		return selectFormateursOfFormation;
	}

	public void setSelectFormateursOfFormation(List<SelectItem> selectFormateur) {
		this.selectFormateursOfFormation = selectFormateur;
	}

	public List<SelectItem> getSelectFormateursOfSession() {
		selectFormateursOfSession = new ArrayList<SelectItem>();
		List<Formateur> allFormateursOfSession = getListChoixDeFormateursDeLaSession();
		for (Formateur formateur : allFormateursOfSession) {
			selectFormateursOfSession.add(new SelectItem(formateur));
		}
		return selectFormateursOfSession;
	}

	public void setSelectFormateursOfSession(
			List<SelectItem> selectFormateursOfSession) {
		this.selectFormateursOfSession = selectFormateursOfSession;
	}

	// *************LIST ET DATAMODEL***********

	@SuppressWarnings("rawtypes")
	public DataModel getlDMQuestions() {
		lDMQuestions = new ListDataModel();
		lDMQuestions.setWrappedData(questionEJB.findAll());
		return lDMQuestions;
	}

	@SuppressWarnings("rawtypes")
	public void setlDMQuestions(DataModel lDMQuestions) {
		this.lDMQuestions = lDMQuestions;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getlDMQuestionsOfEval() {
		lDMQuestionsOfEval = new ListDataModel();
		lDMQuestionsOfEval.setWrappedData(questionnaireEJB
				.findAllQuestionsOfEval(evaluation));
		return lDMQuestionsOfEval;
	}

	@SuppressWarnings("rawtypes")
	public void setlDMQuestionsOfEval(DataModel lDMQuestionsOfEval) {
		this.lDMQuestionsOfEval = lDMQuestionsOfEval;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getlDMResultatsOfSession() {
		if (lDMResultatsOfSession == null) {
			lDMResultatsOfSession = new ListDataModel();
			lDMResultatsOfSession.setWrappedData(resultatEJB
					.findAllResultatsOfSession(session));
		}
		return lDMResultatsOfSession;
	}

	@SuppressWarnings("rawtypes")
	public void setlDMResultatsOfSession(DataModel lDMResultatsOfSession) {
		this.lDMResultatsOfSession = lDMResultatsOfSession;
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

	@SuppressWarnings("rawtypes")
	public DataModel getlDMEvaluations() {
		if (lDMEvaluations == null) {
			lDMEvaluations = new ListDataModel();
			lDMEvaluations.setWrappedData(evaluationEJB.findAll());
		}
		return lDMEvaluations;
	}

	@SuppressWarnings("rawtypes")
	public void setlDMEvaluations(DataModel lDMEvaluations) {
		this.lDMEvaluations = lDMEvaluations;
	}

	public List<Evaluation> getListEvaluations() {
		return listEvaluations;
	}

	public void setListEvaluations(List<Evaluation> listEvaluations) {
		this.listEvaluations = listEvaluations;
	}

	// ***************EJB**********************
	public EvaluationEJB getEvaluationEJB() {
		return evaluationEJB;
	}

	public void setEvaluationEJB(EvaluationEJB evaluationEJB) {
		this.evaluationEJB = evaluationEJB;
	}

	public EvaluationFormation getEvaluationFormation() {
		return evaluationFormation;
	}

	public void setEvaluationFormation(EvaluationFormation evaluationFormation) {
		this.evaluationFormation = evaluationFormation;
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

	public EvaluationFormationEJB getEvaluationFormationEJB() {
		return evaluationFormationEJB;
	}

	public void setEvaluationFormationEJB(
			EvaluationFormationEJB evaluationFormationEJB) {
		this.evaluationFormationEJB = evaluationFormationEJB;
	}

	public ResultatEJB getResultatEJB() {
		return resultatEJB;
	}

	public void setResultatEJB(ResultatEJB resultatEJB) {
		this.resultatEJB = resultatEJB;
	}

	public QuestionEJB getQuestionEJB() {
		return questionEJB;
	}

	public void setQuestionEJB(QuestionEJB questionEJB) {
		this.questionEJB = questionEJB;
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

	public SendTextMessage getSendTextMessage() {
		return sendTextMessage;
	}

	public void setSendTextMessage(SendTextMessage sendTextMessage) {
		this.sendTextMessage = sendTextMessage;
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

	// ************UTILE AVEC JPA???????*********
	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}
}
