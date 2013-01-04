package fr.treeptik.dao;

import javax.ejb.Stateless;

import fr.treeptik.model.Formateur;

@Stateless
public class FormateurDAO extends GenericDAO<Formateur> {

	public FormateurDAO() {
		super(Formateur.class);
	}
}
