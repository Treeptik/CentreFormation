package fr.treeptik.dao;

import javax.ejb.Stateless;

import fr.treeptik.model.Formation;

@Stateless
public class FormationDAO extends GenericDAO<Formation> {

	public FormationDAO() {
		super(Formation.class);
	}
}
