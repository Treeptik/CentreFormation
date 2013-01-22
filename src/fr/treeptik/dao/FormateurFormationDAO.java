package fr.treeptik.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import fr.treeptik.model.Formateur;
import fr.treeptik.model.FormateurFormation;
import fr.treeptik.model.Formation;
import fr.treeptik.model.PKFormateurFormation;

@Stateless
public class FormateurFormationDAO extends GenericDAO<FormateurFormation> {

	public FormateurFormationDAO() {
		super(FormateurFormation.class);
	}

	public FormateurFormation findById(PKFormateurFormation id) {
		return em.find(FormateurFormation.class, id);
	}

	public void addFormateurToFormation(FormateurFormation formateurFormation) {
		em.persist(formateurFormation);
	}

	@SuppressWarnings("unchecked")
	public List<Formateur> findAllFormateursOfFormation(Formation formation) {
		Query query = em.createNamedQuery("findAllFormateursOfFormation");
		query.setParameter("formation", formation);

		List<Formateur> listFormateursOfFormation = query.getResultList();
		return listFormateursOfFormation;
	}
	

	public FormateurFormation findByFormateurAndFormation(Formateur formateur,Formation formation) {
		Query query = em.createNamedQuery("findByFormateurAndFormation");
		query.setParameter("formateur", formateur);
		query.setParameter("formation", formation);
		
		FormateurFormation formateurFormation = (FormateurFormation) query.getSingleResult();
		return formateurFormation;		
	}
	
	public void removeFormateurFromFormation(Formateur formateur,Formation formation) {
		Query query = em.createNamedQuery("removeFormateurFromFormation");
		query.setParameter("formateur", formateur);
		query.setParameter("formation", formation);
		query.executeUpdate();
	}
}
