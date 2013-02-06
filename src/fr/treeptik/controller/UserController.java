package fr.treeptik.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;

import fr.treeptik.model.User;
import fr.treeptik.service.UserEJB;

@SessionScoped
@ManagedBean
public class UserController {

	// *********ENTITE******************************************************
	private User user;

	// *********EJB*********************************************************
	@EJB
	private UserEJB userEJB;
	@EJB
	private SendTextMessage gestionmail;

	// **********LISTES*****************************************************
	private List<User> listUsers = new ArrayList<User>();

	// **********DATAMODEL**************************************************
	@SuppressWarnings("rawtypes")
	private DataModel users;

	public String generatePassword() {
		return userEJB.generatePassword();
	}

	public String doCreate() {
		user.setPassword(userEJB.generatePassword());
		userEJB.create(user);
		listUsers = userEJB.findAll();
		getUsers();
		return "messageUserCree";
	}

	@SuppressWarnings("rawtypes")
	public String doDelete() {
		User user = (User) users.getRowData();
		userEJB.delete(user);
		users = new ListDataModel();
		users.setWrappedData(userEJB.findAll());
		return "listUsers";
	}

	public String doSelectUpdate() {
		user = (User) users.getRowData();
		return "updateUser";
	}

	@SuppressWarnings("rawtypes")
	public String doUpdate() {
		userEJB.update(user);
		users = new ListDataModel();
		users.setWrappedData(userEJB.findAll());
		return "messageUserUpdate";
	}

	public String qui() {
		System.out.println(getRequest().getUserPrincipal().toString());
		return getRequest().getUserPrincipal().getName();
	}

	public boolean isUserAdmin() {

		return getRequest().isUserInRole("ADMIN");
	}

	public String logOut() {
		getRequest().getSession().invalidate();
		return "logout";
	}

	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<User> listUsers) {
		this.listUsers = listUsers;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getUsers() {
		if (users == null) {
			users = new ListDataModel();
			users.setWrappedData(userEJB.findAll());
		}
		return users;
	}

	@SuppressWarnings("rawtypes")
	public void setUsers(DataModel users) {
		this.users = users;
	}

	public UserEJB getUserEJB() {
		return userEJB;
	}

	public void setUserEJB(UserEJB userEJB) {
		this.userEJB = userEJB;
	}
}