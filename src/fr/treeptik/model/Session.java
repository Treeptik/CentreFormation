package fr.treeptik.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//@NamedQuery(name="findFormationsOfSession",
//query="select ft from Formation ft join " +
//" ft.listSessions sess where sess.id= :id")
@SuppressWarnings("serial")
@Entity
public class Session implements Serializable {

	// public static final String FIND_FORMATIONS_OF_SESSION =
	// "Session.findFormationsOfSession";
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String nom;
	@Temporal(TemporalType.DATE)
	private Date dateDebutStage;
	@Temporal(TemporalType.DATE)
	private Date dateFinStage;
	private String Descriptif;

	public Session() {
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

	public Date getDateDebutStage() {
		return dateDebutStage;
	}

	public void setDateDebutStage(Date dateDebutStage) {
		this.dateDebutStage = dateDebutStage;
	}

	public Date getDateFinStage() {
		return dateFinStage;
	}

	public void setDateFinStage(Date dateFinStage) {
		this.dateFinStage = dateFinStage;
	}

	public String getDescriptif() {
		return Descriptif;
	}

	public void setDescriptif(String descriptif) {
		Descriptif = descriptif;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((Descriptif == null) ? 0 : Descriptif.hashCode());
		result = prime * result
				+ ((dateDebutStage == null) ? 0 : dateDebutStage.hashCode());
		result = prime * result
				+ ((dateFinStage == null) ? 0 : dateFinStage.hashCode());
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
		if (!(obj instanceof Session)) {
			return false;
		}
		Session other = (Session) obj;
		if (Descriptif == null) {
			if (other.Descriptif != null) {
				return false;
			}
		} else if (!Descriptif.equals(other.Descriptif)) {
			return false;
		}
		if (dateDebutStage == null) {
			if (other.dateDebutStage != null) {
				return false;
			}
		} else if (!dateDebutStage.equals(other.dateDebutStage)) {
			return false;
		}
		if (dateFinStage == null) {
			if (other.dateFinStage != null) {
				return false;
			}
		} else if (!dateFinStage.equals(other.dateFinStage)) {
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
