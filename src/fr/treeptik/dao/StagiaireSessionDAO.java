package fr.treeptik.dao;

import javax.ejb.Stateless;

import fr.treeptik.model.PKStagiaireSession;
import fr.treeptik.model.StagiaireSession;

@Stateless
public class StagiaireSessionDAO extends GenericDAO<StagiaireSession> {
	
	public StagiaireSessionDAO() {
		super(StagiaireSession.class);
	}
	
	public StagiaireSession findById(PKStagiaireSession id) {
		return em.find(StagiaireSession.class,id);
	}

}
