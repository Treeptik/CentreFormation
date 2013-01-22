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
import fr.treeptik.model.EvaluationFormation;
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
	private SendTextMessage gestionmail;

	// **********LISTES*****************************************************
	private List<Evaluation> listEvaluations = new ArrayList<Evaluation>();
	private List<SelectItem> selectSessionsOfStagiaire;
	private List<SelectItem> selectFormationsOfSession;
	private List<SelectItem> selectFormateursOfFormation;
	private List<SelectItem> selectFormateursOfSession;
	private List<Resultat> listResultatsOfSession;
	private List<Formateur> listFormateursOfSession;
	private List<Formation> listFormationsOfSession;
	private List<Formateur> listFormateursOfEval;
	private List<Question> listQuestionsOfSession;

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

	public String doFillEval() {

		try {
			List<Formateur> tempListFormateurs = new ArrayList<Formateur>();
			List<Formation> tempListFormations = new ArrayList<Formation>();

			tempListFormations = listFormationsOfSession;
			for (Formateur formateur : listFormateursOfEval) {
				Formateur tempFormateur = formateurEJB.findById(formateur
						.getId());
				tempListFormateurs.add(tempFormateur);
				System.out.println("formateur:" + tempFormateur.getNom());
			}

			List<Resultat> listTempResultat = new ArrayList<Resultat>();
			listTempResultat = listResultatsOfSession;
			for (Resultat resultat : listTempResultat) {
				Resultat resultattemp = new Resultat();
				resultattemp = resultat;
				resultattemp.setListFormateursEvalues(listFormateursOfEval);
				resultattemp.setListFormationsEvaluees(listFormationsOfSession);
				resultatEJB.update(resultattemp);
			}
			gestionmail.mailRecapEvaluation(listResultatsOfSession,
					tempListFormateurs, listFormationsOfSession);

			return "messageEvaluationEffectue";
		} catch (Exception e) {
			return "messageEvaluationDejaEffectuee";
		}

	}

	public String identifier() {
		stagiaire = stagiaireEJB.findStagiaireByEmail(getRequest()
				.getUserPrincipal().toString());
		return "selectSession";
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
		return "selectFormateur";
	}

	private List<Questionnaire> doListQuestionnaireOfSession(Session session) {

		List<Formation> tempListFormations = new ArrayList<Formation>();
		List<Evaluation> tempListEvaluations = new ArrayList<Evaluation>();
		List<Questionnaire> tempListQuestionnaire = new ArrayList<Questionnaire>();
		List<Questionnaire> tempListQuestionnaire2 = new ArrayList<Questionnaire>();

		tempListFormations = formationSessionEJB
				.findAllFormationsOfSession(session);
		for (Formation formation : tempListFormations) {
			tempListEvaluations = evaluationFormationEJB
					.findAllEvalsOfFormation(formation);
			for (Evaluation evaluation : tempListEvaluations) {
				tempListQuestionnaire = questionnaireEJB
						.findQuestionnaireByEval(evaluation);
				for (Questionnaire questionnaire : tempListQuestionnaire) {
					tempListQuestionnaire2.add(questionnaire);
				}

			}
		}
		return tempListQuestionnaire2;
	}

	private List<Resultat> createResultat() {

		session = sessionEJB.findById(session.getId());
		pKStagiaireSession.setSession(session);
		pKStagiaireSession.setStagiaire(stagiaire);
		stagiaireSession = stagiaireSessionEJB.findById(pKStagiaireSession);

		List<Question> tempListQuestions = new ArrayList<Question>();
		List<Resultat> tempListResultats = new ArrayList<Resultat>();
		tempListQuestions = getListQuestionsOfSession();

		for (Question question : tempListQuestions) {
			Question tempQuestion = new Question();
			Resultat tempResultat = new Resultat();
			PKResultat tempPKresultat = new PKResultat();
			tempQuestion = question;
			tempPKresultat.setStagiaireSession(stagiaireSession);
			tempPKresultat.setQuestion(tempQuestion);
			tempResultat.setId(tempPKresultat);
			tempListResultats.add(tempResultat);
		}
		return tempListResultats;
	}

	public String chooseFormation() {
		formation = formationEJB.findById(formation.getId());
		return "selectFormateur";
	}

	public String chooseFormateur() {
		return "doEvaluation2";
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

	public List<Formation> getListFormationsOfSession() {
		listFormationsOfSession = formationSessionEJB
				.findAllFormationsOfSession(session);
		return listFormationsOfSession;
	}

	public void setListFormationsOfSession(
			List<Formation> listFormationsOfSession) {
		this.listFormationsOfSession = listFormationsOfSession;
	}

	public List<Formateur> getListFormateursOfSession() {
		session = sessionEJB.findById(session.getId());
		List<Formation> tempListFormations = new ArrayList<Formation>();
		List<Formateur> tempListFormateurs = new ArrayList<Formateur>();
		List<Formateur> tempListFormateurs2 = new ArrayList<Formateur>();
		tempListFormations = formationSessionEJB
				.findAllFormationsOfSession(session);
		for (Formation formation : tempListFormations) {
			tempListFormateurs = formateurFormationEJB
					.findAllFormateursOfFormation(formation);
			for (Formateur formateur : tempListFormateurs) {
				Formateur tempformateur = new Formateur();
				tempformateur = formateurEJB.findById(formateur.getId());
				System.out.println("formateur ID :" + formateur.getId());
				tempListFormateurs2.add(tempformateur);
			}

		}
		listFormateursOfSession = tempListFormateurs2;
		for (Formateur formateur : listFormateursOfSession) {
			System.out.println("formateur ID2 : " + formateur.getId());

		}

		return listFormateursOfSession;
	}

	public void setListFormateursOfSession(
			List<Formateur> listFormateursOfSession) {
		this.listFormateursOfSession = listFormateursOfSession;
	}

	public List<Formateur> getListFormateursOfEval() {
		return listFormateursOfEval;
	}

	public void setListFormateursOfEval(List<Formateur> listFormateursOfEval) {
		this.listFormateursOfEval = listFormateursOfEval;
	}

	public String doListQuestionOfEval() {
		evaluation = (Evaluation) lDMEvaluations.getRowData();
		getlDMQuestionsOfEval();
		return "listQuestionsOfEval";
	}

	public List<Question> getListQuestionsOfSession() {
		session = sessionEJB.findById(session.getId());
		List<Formation> tempListFormations = new ArrayList<Formation>();
		List<Evaluation> tempListEvaluations = new ArrayList<Evaluation>();
		List<Question> tempListQuestions = new ArrayList<Question>();
		tempListFormations = formationSessionEJB
				.findAllFormationsOfSession(session);

		tempListFormations = formationSessionEJB
				.findAllFormationsOfSession(session);
		for (Formation formation : tempListFormations) {
			tempListEvaluations = evaluationFormationEJB
					.findAllEvalsOfFormation(formation);
			for (Evaluation evaluation : tempListEvaluations) {
				tempListQuestions = questionnaireEJB
						.findAllQuestionsOfEval(evaluation);
				for (Question question : tempListQuestions) {
					listQuestionsOfSession.add(question);
				}
			}
		}

		return listQuestionsOfSession;
	}

	public void setListQuestionsOfSession(List<Question> listQuestionsOfSession) {
		this.listQuestionsOfSession = listQuestionsOfSession;
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
		List<Formateur> allFormateursOfSession = getListFormateursOfSession();
		for (Formateur formateur : allFormateursOfSession) {
			selectFormateursOfSession.add(new SelectItem(formateur));
		}
		return selectFormateursOfSession;
	}

	public void setSelectFormateursOfSession(
			List<SelectItem> selectFormateursOfSession) {
		this.selectFormateursOfSession = selectFormateursOfSession;
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

	public EvaluationFormation getQuestionnaireSession() {
		return evaluationFormation;
	}

	public void setQuestionnaireSession(EvaluationFormation evaluationFormation) {
		this.evaluationFormation = evaluationFormation;
	}

	public EvaluationFormationEJB getQuestionnaireSessionEJB() {
		return evaluationFormationEJB;
	}

	public void setQuestionnaireSessionEJB(
			EvaluationFormationEJB evaluationFormationEJB) {
		this.evaluationFormationEJB = evaluationFormationEJB;
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

	@SuppressWarnings("rawtypes")
	public void setlDMEvaluations(DataModel lDMEvaluations) {
		this.lDMEvaluations = lDMEvaluations;
	}

	// ************UTILE AVEC JPA???????*********
	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}
}
