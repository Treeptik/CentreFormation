package fr.treeptik.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@SuppressWarnings("serial")
@Entity
@NamedQueries({
	@NamedQuery(name = "findByNom", query = "select ev from Evaluation ev where ev.nom= :nom ")
})
public class Evaluation implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String nom;
	private String descriptif;


	/*
	 * @Column(length = 20) private int acceuil; private int salle; private int
	 * materiel; private int qualFormation; private int objectif; private int
	 * programme; private int contenu; private int duree; private int rythme;
	 * private String sujetApprofondir; private String sujetSupprimer; private
	 * int technique; private int pedagogie; private int capacite; private int
	 * disponibilite; private int noteFormateur; private String autreStage;
	 * 
	 * @Column(length = 200) private String projet; private String commentaire;
	 */

	public Evaluation() {
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

	public String getDescriptif() {
		return descriptif;
	}

	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descriptif == null) ? 0 : descriptif.hashCode());
		result = prime * result + id;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
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
		if (descriptif == null) {
			if (other.descriptif != null) {
				return false;
			}
		} else if (!descriptif.equals(other.descriptif)) {
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
		return true;
	}
}
