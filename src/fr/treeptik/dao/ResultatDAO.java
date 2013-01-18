package fr.treeptik.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import fr.treeptik.model.Evaluation;
import fr.treeptik.model.PKResultat;
import fr.treeptik.model.Question;
import fr.treeptik.model.Resultat;
import fr.treeptik.model.Session;
import fr.treeptik.model.Stagiaire;

@Stateless
public class ResultatDAO extends GenericDAO<Resultat> {
	
	public ResultatDAO() {
		super(Resultat.class);
	}

	public Resultat findById(PKResultat id) {
		return em.find(Resultat.class,id);
	}
	
	public List<Resultat> findAllResultatsOfEval(Evaluation evaluation) {
		Query query = em.createNamedQuery("findAllResultatsOfEval");
		query.setParameter("evaluation", evaluation);

		List<Resultat> listResultatsOfEval = query.getResultList();
		return listResultatsOfEval;
	}
	
	public void addEvalToSession(Resultat resultat) {
		em.persist(resultat);
	}

	@SuppressWarnings("unchecked")
	public List<Resultat> findAllResultatsOfSession(Session session) {
		Query query = em.createNamedQuery("findAllResultatsOfSession");
		query.setParameter("session", session);

		List<Resultat> listResultatsOfSession = query.getResultList();
		return listResultatsOfSession;
	}
	
	public List<Resultat> findAllResultatsOfStagiaire(Stagiaire stagiaire) {
		Query query = em.createNamedQuery("findAllResultatsOfStagiaire");
		query.setParameter("stagiaire", stagiaire);

		List<Resultat> listResultatsOfStagiaire = query.getResultList();
		return listResultatsOfStagiaire;
	}
	
	public List<Question> findAllQuestionsOfSession(Session session) {
	Query query = em.createNamedQuery("findAllQuestionsOfSession");
	query.setParameter("session", session);

	List<Question> listQuestionsOfSession = query.getResultList();
	return listQuestionsOfSession;
	
	}
	
	public void addQuestionToEval(Resultat resultat) {
	em.persist(resultat);
	}
	
@SuppressWarnings("unchecked")
public List<Question> findAllQuestionsOfEval(Evaluation evaluation) {
	Query query = em.createNamedQuery("findAllQuestionsOfEval");
	query.setParameter("evaluation", evaluation);

	List<Question> listQuestionsOfEval = query.getResultList();
	return listQuestionsOfEval;
}

}
