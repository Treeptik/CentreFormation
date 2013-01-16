package fr.treeptik.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import fr.treeptik.model.Evaluation;
import fr.treeptik.model.Note;
import fr.treeptik.model.PKEvaluationQuestion;
import fr.treeptik.model.Question;

@Stateless
public class NoteDAO extends GenericDAO<Note> {
	
//	private PKEvaluationQuestion pkEvaluationQuestion = new PKEvaluationQuestion();
	private Evaluation evaluation = new Evaluation();
	
	public NoteDAO() {
		super(Note.class);
	}
	


@SuppressWarnings("unchecked")
public List<Question> findAllQuestionOfEval(int evaluationId) {
	Query query = em.createNamedQuery("findAllQuestionsOfEval");
	query.setParameter("evaluationId", evaluationId);

	List<Question> listQuestionsOfEval = query.getResultList();
	return listQuestionsOfEval;
}

	public Note findById(PKEvaluationQuestion id) {
		return em.find(Note.class,id);
	}
}
