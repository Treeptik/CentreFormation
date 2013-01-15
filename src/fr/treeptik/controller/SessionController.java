package fr.treeptik.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import org.primefaces.event.ToggleEvent;

import fr.treeptik.model.Formateur;
import fr.treeptik.model.Formation;
import fr.treeptik.model.Session;
import fr.treeptik.model.Stagiaire;
import fr.treeptik.model.User;
import fr.treeptik.service.FormateurEJB;
import fr.treeptik.service.SessionEJB;
import fr.treeptik.service.StagiaireEJB;
import fr.treeptik.service.UserEJB;

@ManagedBean
public class SessionController {
	@EJB
	private UserEJB userEJB;
	@EJB
	private SessionEJB sessionEJB;
	@EJB
	private FormateurEJB formateurEJB;
	@EJB
	private StagiaireEJB stagiaireEJB;

	private User user = new User();
	private Session session = new Session();
	private Formation formation = new Formation();
	private Formateur formateur = new Formateur();
	private Stagiaire stagiaire = new Stagiaire();
	private List<Session> listSessions = new ArrayList<Session>();

	private List<SelectItem> selectStagiaire;
	@SuppressWarnings("rawtypes")
	private DataModel sessions;

	// private List<SelectItem> selectSession;

	public String doCreate() {

		ArrayList<Formation> listFormations = new ArrayList<Formation>();
		listFormations.add(formation);
		session.setListFormations(listFormations);

		List<Formateur> listFormateurs = new ArrayList<Formateur>();
		listFormateurs.add(formateur);
		session.setListFormateurs(listFormateurs);

		ArrayList<Stagiaire> listStagiaires = new ArrayList<Stagiaire>();
		listStagiaires.add(stagiaire);
		session.setListStagiaires(listStagiaires);

		sessionEJB.create(session);
		return "messageSessionCreee";
	}

	@SuppressWarnings("rawtypes")
	public String doDelete() {
		Session session = (Session) sessions.getRowData();
		sessionEJB.delete(session);
		sessions = new ListDataModel();
		sessions.setWrappedData(sessionEJB.findAll());
		return "listSessions";
	}

	public String doSelectUpdate() {
		session = (Session) sessions.getRowData();
		return "updateSession";
	}

	public String doSelectAddStagiaire() {
		session = (Session) sessions.getRowData();
		return "addStagiaire";
	}

	public List<SelectItem> getSelectStagiaire() {
		if (selectStagiaire == null) {
			List<Stagiaire> listStagiaires = new ArrayList<Stagiaire>();
			listStagiaires = stagiaireEJB.findAll();
			selectStagiaire = new ArrayList<SelectItem>();
			for (Stagiaire stagiaire : listStagiaires) {
				selectStagiaire.add(new SelectItem(stagiaire.getId(), stagiaire
						.getNom() + " " + stagiaire.getPrenom()));
			}
		}
		return selectStagiaire;
	}

	public void setSelectStagiaire(List<SelectItem> selectStagiaire) {
		this.selectStagiaire = selectStagiaire;
	}

	@SuppressWarnings("rawtypes")
	public String doUpdate() {

		ArrayList<Formation> listFormations = new ArrayList<Formation>();
		listFormations.add(formation);
		session.setListFormations(listFormations);

		ArrayList<Formateur> listFormateurs = new ArrayList<Formateur>();

		formateurEJB.findById(formateur.getId());
		listFormateurs.add(formateur);
		session.setListFormateurs(listFormateurs);

		ArrayList<Stagiaire> listStagiaires = new ArrayList<Stagiaire>();
		listStagiaires.add(stagiaire);
		session.setListStagiaires(listStagiaires);

		sessionEJB.update(session);
		sessions = new ListDataModel();
		sessions.setWrappedData(sessionEJB.findAll());
		return "messageSessionUpdate";
	}

	/*
	 * public void qui(){ stagiaire = stagiaireEJB.findById(stagiaire.getId());
	 * System.out.println("Stagiaire:"+stagiaire.getNom());
	 * System.out.println("hashcode: "+session);
	 * System.out.println("ID : "+session.getId());
	 * System.out.println("ID : "+session.getNom()); }
	 */

	public String doAddStagiaire() {
		System.out.println("Test1");
		System.out.println(formation);
		ArrayList<Formation> listFormations = new ArrayList<Formation>();
		listFormations.add(formation);
		session.setListFormations(listFormations);
		System.out.println("Test2");
		System.out.println(formateur);
		List<Formateur> listFormateurs = new ArrayList<Formateur>();
		listFormateurs.add(formateur);
		session.setListFormateurs(listFormateurs);

		System.out.println("Test1");
		System.out.println(user.getId());
		stagiaire = (Stagiaire) stagiaireEJB.findById(stagiaire.getId());
		System.out.println("Test2");
		System.out.println(stagiaire);
		List<Stagiaire> listStagiaires = new ArrayList<Stagiaire>();
		/*
		 * if (session.getListStagiaires()== null){
		 * 
		 * listStagiaires.add(stagiaire);
		 * session.setListStagiaires(listStagiaires); } else { listStagiaires =
		 * session.getListStagiaires();
		 */
		listStagiaires.add(stagiaire);
		session.setListStagiaires(listStagiaires);
		// }
		sessionEJB.update(session);
		return "messageStagiaireAjoute";
	}

	public String doFindAll() {
		listSessions = sessionEJB.findAll();
		return "listSessions";
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

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public List<Session> getListSessions() {
		return listSessions;
	}

	public void setListSessions(List<Session> listSessions) {
		this.listSessions = listSessions;
	}

	public SessionEJB getSessionEJB() {
		return sessionEJB;
	}

	public void setSessionEJB(SessionEJB sessionEJB) {
		this.sessionEJB = sessionEJB;
	}

	public FormateurEJB getFormateurEJB() {
		return formateurEJB;
	}

	public void setFormateurEJB(FormateurEJB formateurEJB) {
		this.formateurEJB = formateurEJB;
	}

	public StagiaireEJB getStagiaireEJB() {
		return stagiaireEJB;
	}

	public void setStagiaireEJB(StagiaireEJB stagiaireEJB) {
		this.stagiaireEJB = stagiaireEJB;
	}

	@SuppressWarnings("rawtypes")
	public void setSessions(DataModel sessions) {
		this.sessions = sessions;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getSessions() {
		if (sessions == null) {
			sessions = new ListDataModel();
			sessions.setWrappedData(sessionEJB.findAll());
		}
		return sessions;
	}

	// *********TEST PRIMEFACES**************

	public void onRowToggle(ToggleEvent event) {
		session = (Session) sessions.getRowData();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"ligne :" + event.getVisibility(), "Session:"
						+ ((Session) event.getData()).getNom());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
