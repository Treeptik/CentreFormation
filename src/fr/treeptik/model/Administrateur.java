package fr.treeptik.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = "findAllAdministrateurs", query = "select a from Administrateur a") })
public class Administrateur extends User {

	public Administrateur() {
		super();
	}

}
