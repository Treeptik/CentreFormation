package fr.treeptik.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.UserDAO;
import fr.treeptik.model.Stagiaire;
import fr.treeptik.model.User;

@Stateless
public class UserEJB {
	@EJB
	private UserDAO userDAO;

	public void create(User user) {
		userDAO.create(user);
	}

	public List<User> findAll() {
		return userDAO.findAll();
	}

	public void delete(User user) {
		userDAO.delete(user);
	}

	public void update(User user) {
		userDAO.update(user);
	}
	
	public User findById(int id) {
		return userDAO.findById(id);
	}
	
	public User findUserByEmail(String email) {
		return userDAO.findUserByEmail(email);
	}
	
	public String generatePassword() {
		return userDAO.generatePassword();
	}
}