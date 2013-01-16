package fr.treeptik.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import fr.treeptik.model.Evaluation;
import fr.treeptik.model.Note;
import fr.treeptik.model.PKEvaluationQuestion;
import fr.treeptik.model.Question;
import fr.treeptik.service.NoteEJB;

@ManagedBean
@RequestScoped
public class NoteController {

	private Note note = new Note();
	private Evaluation evaluation = new Evaluation();
	private Question question = new Question();
	private PKEvaluationQuestion pkEvaluationQuestion = new PKEvaluationQuestion();
	@EJB
	private NoteEJB noteEJB;

	private List<Question> listQuestions = new ArrayList<Question>();
	private List<Note> listNotes = new ArrayList<Note>();
	@SuppressWarnings("rawtypes")
	private DataModel notes;
	private DataModel questions;

	public String doCreate() {
		pkEvaluationQuestion.setEvaluation(evaluation);
		pkEvaluationQuestion.setQuestion(question);
		note.setId(pkEvaluationQuestion);
		noteEJB.create(note);
		listNotes = noteEJB.findAll();
		return "messageNoteCreee";
	}

	@SuppressWarnings("rawtypes")
	public String doDelete() {
		Note note = (Note) notes.getRowData();
		noteEJB.delete(note);
		notes = new ListDataModel();
		notes.setWrappedData(noteEJB.findAll());
		return "listNotes";
	}

	public String doSelectUpdate() {
		note = (Note) notes.getRowData();
		return "updateNote";
	}

	@SuppressWarnings("rawtypes")
	public String doUpdate() {
		noteEJB.update(note);
		notes = new ListDataModel();
		notes.setWrappedData(noteEJB.findAll());
		return "messageNoteUpdate";
	}

	public String doFindAll() {
		listNotes = noteEJB.findAll();
		return "listNotes";
	}

	public String doNew() {
		return "createNote";
	}

	public NoteEJB getNoteEJB() {
		return noteEJB;
	}

	public void setNoteEJB(NoteEJB noteEJB) {
		this.noteEJB = noteEJB;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	public List<Note> getListNotes() {
		return listNotes;
	}

	public void setListNotes(List<Note> listNotes) {
		this.listNotes = listNotes;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getNotes() {
		if (notes == null) {
			notes = new ListDataModel();
			notes.setWrappedData(noteEJB.findAll());
		}
		return notes;
	}

	@SuppressWarnings("rawtypes")
	public void setNotes(DataModel notes) {
		this.notes = notes;
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

	public PKEvaluationQuestion getPkEvaluationQuestion() {
		return pkEvaluationQuestion;
	}

	public void setPkEvaluationQuestion(
			PKEvaluationQuestion pkEvaluationQuestion) {
		this.pkEvaluationQuestion = pkEvaluationQuestion;
	}

	public List<Question> getListQuestions() {
		return listQuestions;
	}

	public void setListQuestions(List<Question> listQuestions) {
		this.listQuestions = listQuestions;
	}

	public DataModel getQuestions() {
		if (questions == null) {
			questions = new ListDataModel();
			questions.setWrappedData(noteEJB.findAll());
		}
		return questions;
	}

	public void setQuestions(DataModel questions) {
		this.questions = questions;
	}
}
