package fr.treeptik.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Evaluation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private CompoKeysEval id;

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
/*	
	@ManyToOne(fetch = FetchType.EAGER)
	private Stagiaire stagiaire;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Session session;
*/
	public Evaluation() {
	}      	
	public CompoKeysEval getId() {
		return id;
	}

	public void setId(CompoKeysEval id) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + acceuil;
		result = prime * result
				+ ((autreStage == null) ? 0 : autreStage.hashCode());
		result = prime * result + capacite;
		result = prime * result
				+ ((commentaire == null) ? 0 : commentaire.hashCode());
		result = prime * result + contenu;
		result = prime * result + disponibilite;
		result = prime * result + duree;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + materiel;
		result = prime * result + noteFormateur;
		result = prime * result + objectif;
		result = prime * result + pedagogie;
		result = prime * result + programme;
		result = prime * result + ((projet == null) ? 0 : projet.hashCode());
		result = prime * result + qualFormation;
		result = prime * result + rythme;
		result = prime * result + salle;
		result = prime
				* result
				+ ((sujetApprofondir == null) ? 0 : sujetApprofondir.hashCode());
		result = prime * result
				+ ((sujetSupprimer == null) ? 0 : sujetSupprimer.hashCode());
		result = prime * result + technique;
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
		if (acceuil != other.acceuil) {
			return false;
		}
		if (autreStage == null) {
			if (other.autreStage != null) {
				return false;
			}
		} else if (!autreStage.equals(other.autreStage)) {
			return false;
		}
		if (capacite != other.capacite) {
			return false;
		}
		if (commentaire == null) {
			if (other.commentaire != null) {
				return false;
			}
		} else if (!commentaire.equals(other.commentaire)) {
			return false;
		}
		if (contenu != other.contenu) {
			return false;
		}
		if (disponibilite != other.disponibilite) {
			return false;
		}
		if (duree != other.duree) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (materiel != other.materiel) {
			return false;
		}
		if (noteFormateur != other.noteFormateur) {
			return false;
		}
		if (objectif != other.objectif) {
			return false;
		}
		if (pedagogie != other.pedagogie) {
			return false;
		}
		if (programme != other.programme) {
			return false;
		}
		if (projet == null) {
			if (other.projet != null) {
				return false;
			}
		} else if (!projet.equals(other.projet)) {
			return false;
		}
		if (qualFormation != other.qualFormation) {
			return false;
		}
		if (rythme != other.rythme) {
			return false;
		}
		if (salle != other.salle) {
			return false;
		}
		if (sujetApprofondir == null) {
			if (other.sujetApprofondir != null) {
				return false;
			}
		} else if (!sujetApprofondir.equals(other.sujetApprofondir)) {
			return false;
		}
		if (sujetSupprimer == null) {
			if (other.sujetSupprimer != null) {
				return false;
			}
		} else if (!sujetSupprimer.equals(other.sujetSupprimer)) {
			return false;
		}
		if (technique != other.technique) {
			return false;
		}
		return true;
	}
}
