package fr.treeptik.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Evaluation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(length = 20)
	private int acceuil;
	private int salle;
	private int materiel;
	private int satisfait;
	private int objectif;
	private int programme;
	private int contenu;
	private int duree;
	private int rythme;
	private String sujetApprofondir;
	private String sujetSupprimer;
	private int technique;
	private int pedagogie;
	private int capacite;
	private int disponibilite;
	private int evaluationGlobale;
	private String autreStage;
	@Column(length = 200)
	private String projet;
	private String commentaire;
	
	@ManyToOne()
	private Stagiaire stagiaire;
	
	@ManyToOne()
	private Session session;

	public Evaluation() {
		super();
	}

	public Evaluation(int acceuil, int salle, int materiel, int satisfait,
			int objectif, int programme, int contenu, int duree, int rythme,
			String sujetApprofondir, String sujetSupprimer, int technique,
			int pedagogie, int capacite, int disponibilite,
			int evaluationGlobale, String autreStage, String projet,
			String commentaire) {
		super();
		this.acceuil = acceuil;
		this.salle = salle;
		this.materiel = materiel;
		this.satisfait = satisfait;
		this.objectif = objectif;
		this.programme = programme;
		this.contenu = contenu;
		this.duree = duree;
		this.rythme = rythme;
		this.sujetApprofondir = sujetApprofondir;
		this.sujetSupprimer = sujetSupprimer;
		this.technique = technique;
		this.pedagogie = pedagogie;
		this.capacite = capacite;
		this.disponibilite = disponibilite;
		this.evaluationGlobale = evaluationGlobale;
		this.autreStage = autreStage;
		this.projet = projet;
		this.commentaire = commentaire;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAcceuil() {
		return acceuil;
	}

	public void setAcceuil(int acceuil) {
		this.acceuil = acceuil;
	}

	public int getSalle() {
		return salle;
	}

	public void setSalle(int salle) {
		this.salle = salle;
	}

	public int getMateriel() {
		return materiel;
	}

	public void setMateriel(int materiel) {
		this.materiel = materiel;
	}

	public int getSatisfait() {
		return satisfait;
	}

	public void setSatisfait(int satisfait) {
		this.satisfait = satisfait;
	}

	public int getObjectif() {
		return objectif;
	}

	public void setObjectif(int objectif) {
		this.objectif = objectif;
	}

	public int getProgramme() {
		return programme;
	}

	public void setProgramme(int programme) {
		this.programme = programme;
	}

	public int getContenu() {
		return contenu;
	}

	public void setContenu(int contenu) {
		this.contenu = contenu;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public int getRythme() {
		return rythme;
	}

	public void setRythme(int rythme) {
		this.rythme = rythme;
	}

	public String getSujetApprofondir() {
		return sujetApprofondir;
	}

	public void setSujetApprofondir(String sujetApprofondir) {
		this.sujetApprofondir = sujetApprofondir;
	}

	public String getSujetSupprimer() {
		return sujetSupprimer;
	}

	public void setSujetSupprimer(String sujetSupprimer) {
		this.sujetSupprimer = sujetSupprimer;
	}

	public int getTechnique() {
		return technique;
	}

	public void setTechnique(int technique) {
		this.technique = technique;
	}

	public int getPedagogie() {
		return pedagogie;
	}

	public void setPedagogie(int pedagogie) {
		this.pedagogie = pedagogie;
	}

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	public int getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(int disponibilite) {
		this.disponibilite = disponibilite;
	}

	public int getEvaluationGlobale() {
		return evaluationGlobale;
	}

	public void setEvaluationGlobale(int evaluationGlobale) {
		this.evaluationGlobale = evaluationGlobale;
	}

	public String getAutreStage() {
		return autreStage;
	}

	public void setAutreStage(String autreStage) {
		this.autreStage = autreStage;
	}

	public String getProjet() {
		return projet;
	}

	public void setProjet(String projet) {
		this.projet = projet;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
