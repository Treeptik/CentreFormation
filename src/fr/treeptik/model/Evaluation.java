package fr.treeptik.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Evaluation implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	private StagiaireSession stagiaireSessionId;
	private String commentaireGenerale;
	@ManyToOne(fetch = FetchType.EAGER)
	private Formation formation;
	@ManyToOne(fetch = FetchType.EAGER)
	private Formateur formateur;
/*
	@Column(length = 20)
	private int acceuil;
	private int salle;
	private int materiel;
	private int qualFormation;
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
	private int noteFormateur;
	private String autreStage;
	@Column(length = 200)
	private String projet;
	private String commentaire;
	*/
	
	public Evaluation() {
	}      	
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StagiaireSession getStagiaireSessionId() {
		return stagiaireSessionId;
	}

	public void setStagiaireSessionId(StagiaireSession stagiaireSessionId) {
		this.stagiaireSessionId = stagiaireSessionId;
	}

	public String getCommentaireGenerale() {
		return commentaireGenerale;
	}
	public void setCommentaireGenerale(String commentaireGenerale) {
		this.commentaireGenerale = commentaireGenerale;
	}

	public Formation getFormation() {
		return formation;
	}
	public void setFormation(Formation formation) {
		this.formation = formation;
	}
	public Formateur getFormateur() {
		return formateur;
	}
	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((commentaireGenerale == null) ? 0 : commentaireGenerale
						.hashCode());
		result = prime * result
				+ ((formateur == null) ? 0 : formateur.hashCode());
		result = prime * result
				+ ((formation == null) ? 0 : formation.hashCode());
		result = prime * result + id;
		result = prime
				* result
				+ ((stagiaireSessionId == null) ? 0 : stagiaireSessionId
						.hashCode());
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
		if (!(obj instanceof Evaluation)) {
			return false;
		}
		Evaluation other = (Evaluation) obj;
		if (commentaireGenerale == null) {
			if (other.commentaireGenerale != null) {
				return false;
			}
		} else if (!commentaireGenerale.equals(other.commentaireGenerale)) {
			return false;
		}
		if (formateur == null) {
			if (other.formateur != null) {
				return false;
			}
		} else if (!formateur.equals(other.formateur)) {
			return false;
		}
		if (formation == null) {
			if (other.formation != null) {
				return false;
			}
		} else if (!formation.equals(other.formation)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (stagiaireSessionId == null) {
			if (other.stagiaireSessionId != null) {
				return false;
			}
		} else if (!stagiaireSessionId.equals(other.stagiaireSessionId)) {
			return false;
		}
		return true;
	}

/*
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

	public int getQualFormation() {
		return qualFormation;
	}

	public void setQualFormation(int qualFormation) {
		this.qualFormation = qualFormation;
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

	public int getNoteFormateur() {
		return noteFormateur;
	}

	public void setNoteFormateur(int noteFormateur) {
		this.noteFormateur = noteFormateur;
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
*/

}
