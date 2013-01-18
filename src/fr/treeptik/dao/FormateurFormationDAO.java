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

		List<Formateur> findAllFormateursOfFormation = query.getResultList();
		return findAllFormateursOfFormation;
	}
}
