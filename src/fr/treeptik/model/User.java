package fr.treeptik.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="User.findUserByEmail", query="select u from User u where u.email = :email")
public class User {

public static final String FIND_BY_EMAIL = "User.findUserByEmail";

 @Id
 @GeneratedValue(strategy=GenerationType.AUTO)
 private int id;
 private String nom;
 private String prenom;
 @Column(unique = true)
 private String email;
 private String password;
 private String role;
 
 public User() {
	}

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

 public String getEmail() {
  return email;
 }

 public void setEmail(String email) {
  this.email = email;
 }

 public String getPassword() {
  return password;
 }

 public void setPassword(String password) {
  this.password = password;
 }

 public String getNom() {
  return nom;
 }

 public void setNom(String nom) {
  this.nom = nom;
 }

 public String getPrenom() {
	return prenom;
}

public void setPrenom(String prenom) {
	this.prenom = prenom;
}

public String getRole() {
  return role;
 }

 public void setRole(String role) {
  this.role = role;
 }

 @Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result + id;
	result = prime * result + ((nom == null) ? 0 : nom.hashCode());
	result = prime * result + ((password == null) ? 0 : password.hashCode());
	result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
	result = prime * result + ((role == null) ? 0 : role.hashCode());
	return result;
}

 @Override
public boolean equals(Object obj) {
	if (this == obj) {
		return true;
	}
	if (obj == null) {
		return false;
	}
	if (!(obj instanceof User)) {
		return false;
	}
	User other = (User) obj;
	if (email == null) {
		if (other.email != null) {
			return false;
		}
	} else if (!email.equals(other.email)) {
		return false;
	}
	if (id != other.id) {
		return false;
	}
	if (nom == null) {
		if (other.nom != null) {
			return false;
		}
	} else if (!nom.equals(other.nom)) {
		return false;
	}
	if (password == null) {
		if (other.password != null) {
			return false;
		}
	} else if (!password.equals(other.password)) {
		return false;
	}
	if (prenom == null) {
		if (other.prenom != null) {
			return false;
		}
	} else if (!prenom.equals(other.prenom)) {
		return false;
	}
	if (role == null) {
		if (other.role != null) {
			return false;
		}
	} else if (!role.equals(other.role)) {
		return false;
	}
	return true;
}
}