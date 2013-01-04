package fr.treeptik.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import fr.treeptik.model.Formateur;
import fr.treeptik.model.Formation;
import fr.treeptik.model.Session;
import fr.treeptik.model.Stagiaire;
import fr.treeptik.service.SessionEJB;

@ManagedBean
public class SessionController {

	@EJB
	private SessionEJB sessionEJB;

	private Session session = new Session();
	private Formation formation = new Formation();
	private Formateur formateur = new Formateur();
	private Stagiaire stagiaire = new Stagiaire();

	private List<Session> ListSessions;

	@SuppressWarnings("rawtypes")
	private DataModel sessions;

	// private List<SelectItem> selectSession;

	public String doCreateSession() {

		ArrayList<Formation> formations = new ArrayList<Formation>();
		formations.add(formation);
		session.setFormations(formations);

		ArrayList<Formateur> formateurs = new ArrayList<Formateur>();
		formateurs.add(formateur);
		session.setFormateurs(formateurs);

		sessionEJB.create(getSession());
		return "message6";

	}

	public String doAddStagiaire() {
		sessionEJB.addStagiaire(session.getId(), stagiaire.getId());
		return "message5";

	}

	public String doFindAll() {
		ListSessions = sessionEJB.findAll();
		return "listSessions";
	}

	public String doSelectUpdate() {
		session = (Session) sessions.getRowData();
		return "updateSession";
	}

	public String doUpdate() {
		sessionEJB.update(session);
		getSessions();
		return "message11";
	}

	public String doDelete() {
		Session session = (Session) sessions.getRowData();
		sessionEJB.delete(session);
		getSessions();
		return "listFormateurs";
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
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

	// public List<SelectItem> getSelectSession() {
	// ListSessions = sessionEJB.findAllSession();
	// selectSession = new ArrayList<SelectItem>();
	// for (Session session : ListSessions){
	// selectSession.add(new SelectItem(session.getId(), session.getNom()));
	// }
	//
	// return selectSession;
	// }
	//
	// public void setSelectSession(List<SelectItem> selectSession) {
	// this.selectSession = selectSession;
	// }

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public List<Session> getListSessions() {
		return ListSessions;
	}

	public void setListSessions(List<Session> listSessions) {
		ListSessions = listSessions;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getSessions() {
		if (sessions == null) {
			sessions = new ListDataModel();
			sessions.setWrappedData(sessionEJB.findAll());
		}
		return sessions;
	}

	@SuppressWarnings("rawtypes")
	public void setSessions(DataModel sessions) {
		this.sessions = sessions;
	}

}
