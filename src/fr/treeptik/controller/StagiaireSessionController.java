package fr.treeptik.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import fr.treeptik.model.StagiaireSession;
import fr.treeptik.service.StagiaireSessionEJB;

@ManagedBean
@RequestScoped
public class StagiaireSessionController {
	
	@EJB
	private StagiaireSessionEJB stagiaireSessionEJB;
	private StagiaireSession stagiaireSession = new StagiaireSession();
	private List<StagiaireSession> listStagiaireSessions = new ArrayList<StagiaireSession>();
	@SuppressWarnings("rawtypes")
	private DataModel stagiaireSessions;
	
	public String doCreate() {
		stagiaireSessionEJB.create(stagiaireSession);
		listStagiaireSessions = stagiaireSessionEJB.findAll();
		return "messageStagiaireSessionCreee";
	}

	@SuppressWarnings("rawtypes")
	public String doDelete() {
		StagiaireSession stagiaireSession = (StagiaireSession) stagiaireSessions.getRowData();
		stagiaireSessionEJB.delete(stagiaireSession);
		stagiaireSessions = new ListDataModel();
		stagiaireSessions.setWrappedData(stagiaireSessionEJB.findAll());
		return "listStagiaireSessions";
	}

	public String doSelectUpdate() {
		stagiaireSession = (StagiaireSession) stagiaireSessions.getRowData();
		return "updateStagiaireSession";
	}

	@SuppressWarnings("rawtypes")
	public String doUpdate() {
		stagiaireSessionEJB.update(stagiaireSession);
		stagiaireSessions = new ListDataModel();
		stagiaireSessions.setWrappedData(stagiaireSessionEJB.findAll());
		return "messageStagiaireSessionUpdate";
	}
	
	public String doFindAll() {
		listStagiaireSessions = stagiaireSessionEJB.findAll();
		return "listStagiaireSessions";
	}


	public String doNew() {
		return "createStagiaireSession";
	}

	public StagiaireSessionEJB getStagiaireSessionEJB() {
		return stagiaireSessionEJB;
	}

	public void setStagiaireSessionEJB(StagiaireSessionEJB stagiaireSessionEJB) {
		this.stagiaireSessionEJB = stagiaireSessionEJB;
	}

	public StagiaireSession getStagiaireSession() {
		return stagiaireSession;
	}

	public void setStagiaireSession(StagiaireSession stagiaireSession) {
		this.stagiaireSession = stagiaireSession;
	}

	public List<StagiaireSession> getListStagiaireSessions() {
		return listStagiaireSessions;
	}

	public void setListStagiaireSessions(List<StagiaireSession> listStagiaireSessions) {
		this.listStagiaireSessions = listStagiaireSessions;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getStagiaireSessions() {
		if (stagiaireSessions == null) {
			stagiaireSessions = new ListDataModel();
			stagiaireSessions.setWrappedData(stagiaireSessionEJB.findAll());
		}
		return stagiaireSessions;
	}

	@SuppressWarnings("rawtypes")
	public void setStagiaireSessions(DataModel stagiaireSessions) {
		this.stagiaireSessions = stagiaireSessions;
	}
}
