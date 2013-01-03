package fr.treeptik.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.AdministrateurTreeptikDAO;
import fr.treeptik.model.AdministrateurTreeptik;

@Stateless
public class AdministrateurTreeptikEJB {
	
	@EJB
	private AdministrateurTreeptikDAO administrateurTreeptikDAO;
	
	public AdministrateurTreeptik createAdministrateurTreeptik(AdministrateurTreeptik administrateurTreeptik){
		return administrateurTreeptikDAO.createAdministrateurTreeptik(administrateurTreeptik);
	}
	
	public List<AdministrateurTreeptik> findAllAdministrateurTreeptik(){
		return administrateurTreeptikDAO.findAllAdministrateurs();
	}
}
