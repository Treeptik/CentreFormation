package fr.treeptik.dao;

import javax.ejb.Stateless;
import javax.persistence.Query;

import fr.treeptik.model.Formateur;
import fr.treeptik.model.FormateurFormation;
import fr.treeptik.model.Formation;

@Stateless
public class FormateurDAO extends GenericDAO<Formateur> {

	public FormateurDAO() {
		super(Formateur.class);
	}
	
	public Formateur findFormateurByName(String nom) {
		Query query = em.createNamedQuery("findFormateurByName");
		query.setParameter("nom", nom);


		Formateur formateur = (Formateur) query
				.getSingleResult();
		return formateur;
	}
}
