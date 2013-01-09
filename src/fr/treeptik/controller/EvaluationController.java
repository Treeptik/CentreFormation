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

@ManagedBean
@SessionScoped
public class EvaluationController {

	@EJB
	private EvaluationEJB evaluationEJB;

	@EJB
	private SessionEJB sessionEJB;

	private Evaluation evaluation = new Evaluation();

	private List<Evaluation> listEvaluations = new ArrayList<Evaluation>();
	private List<SelectItem> selectSession = new ArrayList<SelectItem>();

	private Stagiaire stagiaire = new Stagiaire();
	private Session session = new Session();

	private String idStagiaire;
	
	@SuppressWarnings("rawtypes")
	private DataModel evaluations;
	
	@EJB
	private SendTextMessage gestionmail;

	public String doCreate() {
		System.out.println("Controller stagiaire : " + stagiaire);
		evaluation.setStagiaire(stagiaire);
		evaluation.setSession(session);
		evaluationEJB.create(evaluation);
		gestionmail.mailRecapEvaluation(evaluation);
		return "messageEvaluationCreee";
	}

	@SuppressWarnings("rawtypes")
	public String doDelete() {
		Evaluation evaluation = (Evaluation) evaluations
				.getRowData();
		evaluationEJB.delete(evaluation);
		evaluations = new ListDataModel();
		evaluations.setWrappedData(evaluationEJB
				.findAll());
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

	public String doNew() {
		return "doEvaluation";
	}

	public EvaluationEJB getEvaluationEJB() {
		return evaluationEJB;
	}

	public void setEvaluationEJB(EvaluationEJB evaluationEJB) {
		this.evaluationEJB = evaluationEJB;
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

	public String getIdStagiaire() {
		return idStagiaire;
	}

	public void setIdStagiaire(String idStagiaire) {
		this.idStagiaire = idStagiaire;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public List<SelectItem> getSelectSession() {
		List<Session> allSessions = sessionEJB.findAll();
		for (Session session : allSessions) {
			selectSession
					.add(new SelectItem(session.getId(), session.getNom()));
		}
		return selectSession;
	}

	public void setSelectSession(List<SelectItem> selectSession) {
		this.selectSession = selectSession;
	}

	public List<Evaluation> getListEvaluations() {
		return listEvaluations;
	}

	public void setListEvaluations(List<Evaluation> listEvaluations) {
		this.listEvaluations = listEvaluations;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getEvaluations() {
		return evaluations;
	}

	@SuppressWarnings("rawtypes")
	public void setEvaluations(DataModel evaluations) {
		this.evaluations = evaluations;
	}

}
