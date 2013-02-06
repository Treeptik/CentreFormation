package fr.treeptik.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@SuppressWarnings("serial")
@Entity
@NamedQueries({ @NamedQuery(name = "findAllAdministrateurs", query = "select a from Administrateur a") })
public class Administrateur extends User implements Serializable {

	public Administrateur() {
		super();
	}

}
