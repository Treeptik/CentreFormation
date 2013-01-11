package fr.treeptik.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
		@NamedQuery(name="Stagiaire.findStagiaireByEmail", query="select u from User u where u.email = :email"),
		@NamedQuery(name = "findAllStagiaires", query = "select s from Stagiaire s "),
		@NamedQuery(name = "findStagiairesInSession", query = " select s from Session session join session.stagiaires s where session.id=?1 ")})
public class Stagiaire extends User implements Serializable {

	public static final String FIND_BY_EMAIL = "Stagiaire.findStagiaireByEmail";
	private static final long serialVersionUID = 1L;
	
	
//	private String nom;
	private String prenom;
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	@Column(length = 50)
	private String adresse;
	private String codePostal;
	private String ville;
	private String tel;
//	private String email;
	private String sexe;
	private String diplome;
	private String domaine;
//	private String password;
//	private String role= "USER";
	
	@OneToMany(mappedBy = "stagiaire", cascade = CascadeType.PERSIST)
	private List<Evaluation> evaluations = new ArrayList<Evaluation>();

	@ManyToOne()
	private Administrateur administrateur;
	
	@ManyToMany(mappedBy = "stagiaires")
	private List<Session> sessions;

	public Stagiaire() {
		super();
	}
	/*
	public Stagiaire(String nom, String prenom, Date dateNaissance,
			String adresse, String codePostal, String ville, String tel, String email,
			String sexe, String diplome, String domaine) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.tel = tel;
		this.email = email;
		this.sexe = sexe;
		this.diplome = diplome;
		this.domaine = domaine;


	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
*/
	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
/*
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
*/
	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getDiplome() {
		return diplome;
	}

	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}

	public String getDomaine() {
		return domaine;
	}

	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Evaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}
	
	public Administrateur getadministrateur() {
		return administrateur;
	}

	public void setadministrateur(
			Administrateur administrateur) {
		this.administrateur = administrateur;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Administrateur getAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(
			Administrateur administrateur) {
		this.administrateur = administrateur;
	}
/*
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
*/
	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

}