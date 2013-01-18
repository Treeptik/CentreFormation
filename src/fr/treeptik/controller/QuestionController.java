package fr.treeptik.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import fr.treeptik.model.Question;
import fr.treeptik.service.QuestionEJB;

@ManagedBean
@RequestScoped
public class QuestionController {
	
	// *********ENTITE******************************************************
	private Question question = new Question();
	
	// *********EJB*********************************************************
	@EJB
	private QuestionEJB questionEJB;

	// **********LISTES*****************************************************
	private List<Question> listQuestions;
	private List<SelectItem> selectQuestion;
	
	// **********DATAMODEL**************************************************
	@SuppressWarnings("rawtypes")
	private DataModel lDMQuestions;
	
	@SuppressWarnings("rawtypes")
	public String doCreate() {
		questionEJB.create(question);
		lDMQuestions = new ListDataModel();
		lDMQuestions.setWrappedData(questionEJB.findAll());
		return "messageQuestionCreee";
	}

	@SuppressWarnings("rawtypes")
	public String doDelete() {
		Question question = (Question) lDMQuestions.getRowData();
		questionEJB.delete(question);
		lDMQuestions = new ListDataModel();
		lDMQuestions.setWrappedData(questionEJB.findAll());
		return "listQuestions";
	}

	public String doSelectUpdate() {
		question = (Question) lDMQuestions.getRowData();
		return "updateQuestion";
	}

	@SuppressWarnings("rawtypes")
	public String doUpdate() {
		questionEJB.update(question);
		lDMQuestions = new ListDataModel();
		lDMQuestions.setWrappedData(questionEJB.findAll());
		return "messageQuestionUpdate";
	}
	
	public String doFindAll() {
		listQuestions = questionEJB.findAll();
		return "listQuestions";
	}


	public String doNew() {
		return "createQuestion";
	}

	public QuestionEJB getQuestionEJB() {
		return questionEJB;
	}

	public void setQuestionEJB(QuestionEJB questionEJB) {
		this.questionEJB = questionEJB;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<Question> getListQuestions() {
		return listQuestions;
	}

	public void setListQuestions(List<Question> listQuestions) {
		this.listQuestions = listQuestions;
	}
	
	public List<SelectItem> getSelectQuestion() {
		
		listQuestions = questionEJB.findAll();
		selectQuestion = new ArrayList<SelectItem>();
		for (Question question : listQuestions) {
			selectQuestion.add(new SelectItem(question.getId(), question
					.getLibelle()));
		}
		return selectQuestion;
	}

	public void setSelectQuestion(List<SelectItem> selectQuestion) {
		this.selectQuestion = selectQuestion;
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
}
