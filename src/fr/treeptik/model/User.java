package fr.treeptik.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="User.findUserByEmail", query="select u from User u where u.email = :email")
public class User {

public static final String FIND_BY_EMAIL = "User.findUserByEmail";

 @Id
 @GeneratedValue(strategy=GenerationType.AUTO)
 private int id;
 @Column(unique = true)
 private String nom;
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

 public String getRole() {
  return role;
 }

 public void setRole(String role) {
  this.role = role;
 }

 @Override
 public int hashCode() {
  return getId();
 }

 @Override
 public boolean equals(Object obj) {
  if(obj instanceof User){
   User user = (User) obj;
   return user.getEmail().equals(getEmail());
  }

  return false;
 }
}