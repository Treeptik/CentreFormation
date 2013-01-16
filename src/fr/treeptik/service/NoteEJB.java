package fr.treeptik.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaQuery;

import fr.treeptik.dao.NoteDAO;
import fr.treeptik.model.Note;
import fr.treeptik.model.PKEvaluationQuestion;

@Stateless
public class NoteEJB {
	
@EJB
private NoteDAO noteDAO;

public void create(Note note){
	noteDAO.create(note);
}

public List<Note> findAll(){
	return noteDAO.findAll();
}

public void delete(Note note){
	noteDAO.delete(note);
}

public void update(Note note) {
	noteDAO.update(note);
}

public Note findById(PKEvaluationQuestion id) {
	return noteDAO.findById(id);
}
}