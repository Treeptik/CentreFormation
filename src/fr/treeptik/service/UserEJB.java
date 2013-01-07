package fr.treeptik.service;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import fr.treeptik.dao.UserDAO;
import fr.treeptik.model.User;

@Stateless
public class UserEJB {
	@EJB
	private UserDAO userDAO;

	public User findUserByEmail(String email) {
		return userDAO.findUserByEmail(email);
	}
}