package fr.treeptik.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import fr.treeptik.model.Question;
import fr.treeptik.service.QuestionEJB;

@ManagedBean
@RequestScoped
public class QuestionController {
	
	@EJB
	private QuestionEJB questionEJB;
	private Question question = new Question();
	private List<Question> listQuestions = new ArrayList<Question>();
	@SuppressWarnings("rawtypes")
	private DataModel questions;
	
	public String doCreate() {
		questionEJB.create(question);
		listQuestions = questionEJB.findAll();
		return "messageQuestionCreee";
	}

	@SuppressWarnings("rawtypes")
	public String doDelete() {
		Question question = (Question) questions.getRowData();
		questionEJB.delete(question);
		questions = new ListDataModel();
		questions.setWrappedData(questionEJB.findAll());
		return "listQuestions";
	}

	public String doSelectUpdate() {
		question = (Question) questions.getRowData();
		return "updateQuestion";
	}

	@SuppressWarnings("rawtypes")
	public String doUpdate() {
		questionEJB.update(question);
		questions = new ListDataModel();
		questions.setWrappedData(questionEJB.findAll());
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

	@SuppressWarnings("rawtypes")
	public DataModel getQuestions() {
		if (questions == null) {
			questions = new ListDataModel();
			questions.setWrappedData(questionEJB.findAll());
		}
		return questions;
	}

	@SuppressWarnings("rawtypes")
	public void setQuestions(DataModel questions) {
		this.questions = questions;
	}
}
