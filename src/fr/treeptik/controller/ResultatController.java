package fr.treeptik.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import fr.treeptik.model.Evaluation;
import fr.treeptik.model.PKQuestionnaire;
import fr.treeptik.model.Question;
import fr.treeptik.model.Resultat;
import fr.treeptik.service.EvaluationEJB;
import fr.treeptik.service.QuestionEJB;
import fr.treeptik.service.ResultatEJB;

@ManagedBean
@RequestScoped
public class ResultatController {

	// *********ENTITE******************************************************
	private Resultat resultat = new Resultat();
	private Evaluation evaluation = new Evaluation();
	private Question question = new Question();
	private PKQuestionnaire pkEvaluationQuestion = new PKQuestionnaire();
	
	// *********EJB*********************************************************	
	@EJB
	private ResultatEJB resultatEJB;
	@EJB
	private EvaluationEJB evaluationEJB;
	@EJB
	private QuestionEJB questionEJB;

	// **********LISTES*****************************************************
	private List<Question> listQuestions;
	private List<Resultat> listResultats;

	
	// **********DATAMODEL**************************************************
	@SuppressWarnings("rawtypes")
	private DataModel lDMresultats;

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
