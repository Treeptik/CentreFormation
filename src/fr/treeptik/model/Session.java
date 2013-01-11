package fr.treeptik.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Session implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nom;
	@Temporal(TemporalType.DATE)
	private Date dateDebutStage;
	@Temporal(TemporalType.DATE)
	private Date dateFinStage;
	@ManyToMany
	private List<Formateur> formateurs;
	@ManyToMany
	private List<Formation> formations;
	@ManyToMany
	private List<Stagiaire> stagiaires;
	@OneToMany(mappedBy = "session", cascade = CascadeType.PERSIST)
	private List<Evaluation> evaluations = new ArrayList<Evaluation>();
	
	public Session() {
	}

	public Session(Date dateDebuStage, Date dateFinStage) {
		super();
		this.dateDebutStage = dateDebuStage;
		this.dateFinStage = dateFinStage;
	}

	public Date getDateDebutStage() {
		return dateDebutStage;
	}

	public void setDateDebutStage(Date dateDebuStage) {
		this.dateDebutStage = dateDebuStage;
	}

	public Date getDateFinStage() {
		return dateFinStage;
	}

	public void setDateFinStage(Date dateFinStage) {
		this.dateFinStage = dateFinStage;
	}

	public List<Formateur> getFormateurs() {
		return formateurs;
	}

	public void setFormateurs(List<Formateur> formateurs) {
		this.formateurs = formateurs;
	}

	public List<Formation> getFormations() {
		return formations;
	}

	public void setFormations(List<Formation> formations) {
		this.formations = formations;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Stagiaire> getStagiaires() {
		return stagiaires;
	}

	public void setStagiaires(List<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Evaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}

}
