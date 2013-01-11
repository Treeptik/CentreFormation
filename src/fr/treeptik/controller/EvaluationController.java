package fr.treeptik.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import fr.treeptik.model.Evaluation;
import fr.treeptik.model.Session;
import fr.treeptik.model.Stagiaire;
import fr.treeptik.service.EvaluationEJB;
import fr.treeptik.service.SessionEJB;
import fr.treeptik.service.StagiaireEJB;

@ManagedBean
@SessionScoped
public class EvaluationController {

	private Evaluation evaluation = new Evaluation();	
	@EJB
	private EvaluationEJB evaluationEJB;
	@EJB
	private SessionEJB sessionEJB;
	@EJB
	private StagiaireEJB stagiaireEJB;
	private List<Evaluation> listEvaluations = new ArrayList<Evaluation>();
	private List<SelectItem> selectSession;
	private List<SelectItem> selectStagiaire;

	private Stagiaire stagiaire = new Stagiaire();
	private Session session = new Session();

	// private String idStagiaire;

	@SuppressWarnings("rawtypes")
	private DataModel evaluations;

	@EJB
	private SendTextMessage gestionmail;

	public String doCreate() {
		evaluationEJB.create(evaluation);
		gestionmail.mailRecapEvaluation(evaluation);
		return "messageEvaluationEffectue";
	}

	@SuppressWarnings("rawtypes")
	public String doDelete() {
		Evaluation evaluation = (Evaluation) evaluations.getRowData();
		evaluationEJB.delete(evaluation);
		evaluations = new ListDataModel();
		evaluations.setWrappedData(evaluationEJB.findAll());
		return "listEvaluations";
	}

	public String doSelectUpdate() {
		evaluation = (Evaluation) evaluations.getRowData();
		return "updateEvaluation";
	}

	@SuppressWarnings("rawtypes")
	public String doUpdate() {
		evaluationEJB.update(evaluation);
		evaluations = new ListDataModel();
		evaluations.setWrappedData(evaluationEJB.findAll());
		return "messageEvaluationUpdate";
	}

	/**
	 * dans le processus de l'évaluation on choisit sa session puis son nom donc
	 * chooseSession() renvoie à la page du choix de son nom "selectStagiaire"
	 * 
	 * @return "selectStagiaire"
	 */
	public String chooseSession() {
		session = sessionEJB.findById(session.getId());
		evaluation.setSession(session);
		return "selectStagiaire";
	}

	public String chooseStagiaire() {
		stagiaire = stagiaireEJB.findById(stagiaire.getId());
		evaluation.setStagiaire(stagiaire);
		return "doEvaluation";
	}

	public String doNew() {
		evaluation = new Evaluation();
		return "selectSession";
	}

	public EvaluationEJB getEvaluationEJB() {
		return evaluationEJB;
	}

	public void setEvaluationEJB(EvaluationEJB evaluationEJB) {
		this.evaluationEJB = evaluationEJB;
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

	public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	public List<Evaluation> getListEvaluation() {
		return listEvaluations;
	}

	public void setListEvaluation(List<Evaluation> listEvaluations) {
		this.listEvaluations = listEvaluations;
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

	public List<SelectItem> getSelectSession() {
		if (selectSession == null) {
			selectSession = new ArrayList<SelectItem>();
			List<Session> allSessions = sessionEJB.findAll();
			for (Session session : allSessions) {
				selectSession.add(new SelectItem(session.getId(), session
						.getNom()));
			}
		}
		return selectSession;
	}

	public void setSelectSession(List<SelectItem> selectSession) {
		this.selectSession = selectSession;
	}

	public List<SelectItem> getSelectStagiaire() {
		if (selectStagiaire == null) {
			selectStagiaire = new ArrayList<SelectItem>();
			List<Stagiaire> allNames = stagiaireEJB.findAll();
			for (Stagiaire stagiaire : allNames) {
				selectStagiaire.add(new SelectItem(stagiaire.getId(), stagiaire
						.getNom()));
			}
		}
		return selectStagiaire;
	}

	public void setSelectStagiaire(List<SelectItem> selectStagiaire) {
		this.selectStagiaire = selectStagiaire;
	}

	public List<Evaluation> getListEvaluations() {
		return listEvaluations;
	}

	public void setListEvaluations(List<Evaluation> listEvaluations) {
		this.listEvaluations = listEvaluations;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getEvaluations() {
		if (evaluations == null) {
			evaluations = new ListDataModel();
			evaluations.setWrappedData(evaluationEJB.findAll());
		}
		return evaluations;
	}

	@SuppressWarnings("rawtypes")
	public void setEvaluations(DataModel evaluations) {
		this.evaluations = evaluations;
	}

	// public String getIdStagiaire() {
	// return idStagiaire;
	// }
	//
	// public void setIdStagiaire(String idStagiaire) {
	// this.idStagiaire = idStagiaire;
	// }

}
