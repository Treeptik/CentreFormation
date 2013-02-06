package fr.treeptik.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.primefaces.event.ToggleEvent;

import fr.treeptik.model.Evaluation;
import fr.treeptik.model.PKQuestionnaire;
import fr.treeptik.model.Question;
import fr.treeptik.model.Resultat;
import fr.treeptik.model.Session;
import fr.treeptik.model.Stagiaire;
import fr.treeptik.service.EvaluationEJB;
import fr.treeptik.service.QuestionEJB;
import fr.treeptik.service.ResultatEJB;
import fr.treeptik.service.StagiaireEJB;
import fr.treeptik.service.StagiaireSessionEJB;

@ManagedBean
@RequestScoped
public class ResultatController {

	// *********ENTITE******************************************************
	private Resultat resultat = new Resultat();
	private Evaluation evaluation = new Evaluation();
	private Question question = new Question();
	private PKQuestionnaire pkEvaluationQuestion = new PKQuestionnaire();
	private Stagiaire stagiaire;
	private Session session;
	
	// *********EJB*********************************************************	
	@EJB
	private ResultatEJB resultatEJB;
	@EJB
	private EvaluationEJB evaluationEJB;
	@EJB
	private QuestionEJB questionEJB;
	@EJB
	private StagiaireEJB stagiaireEJB;
	@EJB
	private StagiaireSessionEJB stagiaireSessionEJB;

	// **********LISTES*****************************************************
	private List<Question> listQuestions;
	private List<Resultat> listResultats;
	private List<Resultat> listResultatsOfStagiaire;
	private List<Resultat> listResultatsOfStagiaireForSession;
	private List<Session> listSessionsOfStagiaire;	
	
	// **********DATAMODEL**************************************************
	@SuppressWarnings("rawtypes")
	private DataModel lDMresultats;
	@SuppressWarnings("rawtypes")
	private DataModel lDMStagiaires;
	@SuppressWarnings("rawtypes")
	private DataModel lDMSessionsOfStagiaire;

	public void onRowToggleStagiaire(ToggleEvent event) {
		stagiaire = (Stagiaire) lDMStagiaires.getRowData();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"ligne :" + event.getVisibility(), "Stagiaire:"
						+ ((Stagiaire) event.getData()).getNom());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void onRowToggleSession(ToggleEvent event) {
		session = (Session) lDMSessionsOfStagiaire.getRowData();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"ligne :" + event.getVisibility(), "Session:"
						+ ((Session) event.getData()).getNom());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public List<Resultat> getListResultatsOfStagiaire() {
		listResultatsOfStagiaire = resultatEJB.findAllResultatsOfStagiaire(stagiaire);
		return listResultatsOfStagiaire;
	}

	public void setListResultatsOfStagiaire(List<Resultat> listResultatsOfStagiaire) {
		this.listResultatsOfStagiaire = listResultatsOfStagiaire;
	}
	
	public List<Session> getListSessionsOfStagiaire() {
		listSessionsOfStagiaire = stagiaireSessionEJB.findAllSessionsOfStagiaire(stagiaire);
		return listSessionsOfStagiaire;
	}

	public void setListSessionsOfStagiaire(List<Session> listSessionsOfStagiaire) {
		this.listSessionsOfStagiaire = listSessionsOfStagiaire;
	}
	
	public List<Resultat> getListResultatsOfStagiaireForSession() {
		listResultatsOfStagiaireForSession = resultatEJB.findAllResultatsOfStagiaireForSession(stagiaire, session);
		return listResultatsOfStagiaireForSession;
	}

	public void setListResultatsOfStagiaireForSession(List<Resultat> listResultatsOfStagiaireForSession) {
		this.listResultatsOfStagiaireForSession = listResultatsOfStagiaireForSession;
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
	public DataModel getlDMSessionsOfStagiaire() {
		if (lDMSessionsOfStagiaire == null) {
			lDMSessionsOfStagiaire = new ListDataModel();
			lDMSessionsOfStagiaire.setWrappedData(stagiaireSessionEJB.findAllSessionsOfStagiaire(stagiaire));
		}
		return lDMSessionsOfStagiaire;
	}

	@SuppressWarnings("rawtypes")
	public void setlDMSessionsOfStagiaire(DataModel lDMSessionsOfStagiaire) {
		this.lDMSessionsOfStagiaire = lDMSessionsOfStagiaire;
	}
	
	
	public String doCreate() {
		pkEvaluationQuestion.setEvaluation(evaluation);
		pkEvaluationQuestion.setQuestion(question);
//		resultat.setId(pkEvaluationQuestion);
		resultatEJB.create(resultat);
		listResultats = resultatEJB.findAll();
		return "messageResultatCreee";
	}

	@SuppressWarnings("rawtypes")
	public String doDelete() {
		Resultat resultat = (Resultat) lDMresultats.getRowData();
		resultatEJB.delete(resultat);
		lDMresultats = new ListDataModel();
		lDMresultats.setWrappedData(resultatEJB.findAll());
		return "listResultats";
	}

	public String doSelectUpdate() {
		resultat = (Resultat) lDMresultats.getRowData();
		return "updateResultat";
	}

	@SuppressWarnings("rawtypes")
	public String doUpdate() {
		resultatEJB.update(resultat);
		lDMresultats = new ListDataModel();
		lDMresultats.setWrappedData(resultatEJB.findAll());
		return "messageNoteUpdate";
	}

	public String doNew() {
		return "createNote";
	}

	@SuppressWarnings("rawtypes")
	public DataModel getlDMResultats() {
		if (lDMresultats == null) {
			lDMresultats = new ListDataModel();
			lDMresultats.setWrappedData(resultatEJB.findAll());
		}
		return lDMresultats;
	}

	@SuppressWarnings("rawtypes")
	public void setlDMResultats(DataModel lDMResultats) {
		this.lDMresultats = lDMResultats;
	}
	
	public List<Question> getListQuestions() {
		return listQuestions;
	}

	public void setListQuestions(List<Question> listQuestions) {
		this.listQuestions = listQuestions;
	}
	
	public List<Resultat> getListResultats() {
		return listResultats;
	}

	public void setListResultats(List<Resultat> listResultats) {
		this.listResultats = listResultats;
	}

	public ResultatEJB getNoteEJB() {
		return resultatEJB;
	}

	public void setResultatEJB(ResultatEJB resultatEJB) {
		this.resultatEJB = resultatEJB;
	}
	
	public PKQuestionnaire getPkEvaluationQuestion() {
		return pkEvaluationQuestion;
	}

	public void setPkEvaluationQuestion(
			PKQuestionnaire pkEvaluationQuestion) {
		this.pkEvaluationQuestion = pkEvaluationQuestion;
	}
	public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
	public Resultat getResultat() {
		return resultat;
	}

	public void setResultat(Resultat resultat) {
		this.resultat = resultat;
	}
}
