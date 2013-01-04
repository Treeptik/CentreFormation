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
	
	public void create(AdministrateurTreeptik administrateurTreeptik){
		administrateurTreeptikDAO.create(administrateurTreeptik);
	}
	
	public List<AdministrateurTreeptik> findAll(){
		return administrateurTreeptikDAO.findAll();
	}
	
	public void delete(AdministrateurTreeptik administrateurTreeptik){
		administrateurTreeptikDAO.delete(administrateurTreeptik);
	}
	
	public void update(AdministrateurTreeptik administrateurTreeptik) {
		administrateurTreeptikDAO.update(administrateurTreeptik);
	}
}