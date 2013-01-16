package fr.treeptik.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.StagiaireSessionDAO;
import fr.treeptik.model.PKStagiaireSession;
import fr.treeptik.model.StagiaireSession;

@Stateless
public class StagiaireSessionEJB {
	
@EJB
private StagiaireSessionDAO stagiaireSessionDAO;

public void create(StagiaireSession stagiaireSession){
	stagiaireSessionDAO.create(stagiaireSession);
}

public List<StagiaireSession> findAll(){
	return stagiaireSessionDAO.findAll();
}

public void delete(StagiaireSession stagiaireSession){
	stagiaireSessionDAO.delete(stagiaireSession);
}

public void update(StagiaireSession stagiaireSession) {
	stagiaireSessionDAO.update(stagiaireSession);
}

public StagiaireSession findById(PKStagiaireSession id) {
	return stagiaireSessionDAO.findById(id);
}
}