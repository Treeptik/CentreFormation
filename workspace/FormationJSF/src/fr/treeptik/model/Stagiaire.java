package fr.treeptik.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
		@NamedQuery(name = "findALLStagiaires", query = "select s from Stagiaire s "),
		@NamedQuery(name = "findStagiairesInSession", query = " select s from Session session join session.stagiaires s where session.id=?1 ")})
public class Stagiaire implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false, length = 20)
	private String nom;
	private String prenom;
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	@Column(length = 50)
	private String adresse;
	private String codePostal;
	private String ville;
	private String tel;
	private String mail;
	private String sexe;
	private String diplome;
	private String domaine;
	
	
	@OneToMany(mappedBy = "stagiaire", cascade = CascadeType.PERSIST)
	private List<Evaluation> evaluations = new ArrayList<Evaluation>();

	@ManyToOne()
	private AdministrateurTreeptik administrateurTreeptik;

	public Stagiaire() {
		super();
	}

	public Stagiaire(String nom, String prenom, Date dateNaissance,
			String adresse, String codePostal, String ville, String tel, String mail,
			String sexe, String diplome, String domaine) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.tel = tel;
		this.mail = mail;
		this.sexe = sexe;
		this.diplome = diplome;
		this.domaine = domaine;


	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

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
	
	public AdministrateurTreeptik getadministrateurTreeptik() {
		return administrateurTreeptik;
	}

	public void setadministrateurTreeptik(
			AdministrateurTreeptik administrateurTreeptik) {
		this.administrateurTreeptik = administrateurTreeptik;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public AdministrateurTreeptik getAdministrateurTreeptik() {
		return administrateurTreeptik;
	}

	public void setAdministrateurTreeptik(
			AdministrateurTreeptik administrateurTreeptik) {
		this.administrateurTreeptik = administrateurTreeptik;
	}

}