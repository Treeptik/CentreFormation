package fr.treeptik.dao;

import javax.ejb.Stateless;

import fr.treeptik.model.Administrateur;

@Stateless
public class AdministrateurDAO extends
		GenericDAO<Administrateur> {

	public AdministrateurDAO() {
		super(Administrateur.class);
	}
}