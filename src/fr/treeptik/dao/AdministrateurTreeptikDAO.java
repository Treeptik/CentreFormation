package fr.treeptik.dao;

import javax.ejb.Stateless;

import fr.treeptik.model.AdministrateurTreeptik;

@Stateless
public class AdministrateurTreeptikDAO extends
		GenericDAO<AdministrateurTreeptik> {

	public AdministrateurTreeptikDAO() {
		super(AdministrateurTreeptik.class);
	}
}