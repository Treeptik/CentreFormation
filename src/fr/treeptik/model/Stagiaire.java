package fr.treeptik.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
		@NamedQuery(name="Stagiaire.findStagiaireByEmail", query="select u from User u where u.email = :email"),
		@NamedQuery(name = "findAllStagiaires", query = "select s from Stagiaire s ")})
public class Stagiaire extends User implements Serializable {

	public static final String FIND_BY_EMAIL = "Stagiaire.findStagiaireByEmail";
	private static final long serialVersionUID = 1L;
	
	private String prenom;
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	private String tel;
	@Column(length = 50)
	private String adresse;
	private String codePostal;
	private String ville;
	private String sexe;
	private String diplome;
	private String domaine;
	@ManyToOne()
	private Administrateur administrateur;

	public Stagiaire() {
		super();
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
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

	public Administrateur getAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(
			Administrateur administrateur) {
		this.administrateur = administrateur;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((administrateur == null) ? 0 : administrateur.hashCode());
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result
				+ ((codePostal == null) ? 0 : codePostal.hashCode());
		result = prime * result
				+ ((dateNaissance == null) ? 0 : dateNaissance.hashCode());
		result = prime * result + ((diplome == null) ? 0 : diplome.hashCode());
		result = prime * result + ((domaine == null) ? 0 : domaine.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result + ((sexe == null) ? 0 : sexe.hashCode());
		result = prime * result + ((tel == null) ? 0 : tel.hashCode());
		result = prime * result + ((ville == null) ? 0 : ville.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Stagiaire)) {
			return false;
		}
		Stagiaire other = (Stagiaire) obj;
		if (administrateur == null) {
			if (other.administrateur != null) {
				return false;
			}
		} else if (!administrateur.equals(other.administrateur)) {
			return false;
		}
		if (adresse == null) {
			if (other.adresse != null) {
				return false;
			}
		} else if (!adresse.equals(other.adresse)) {
			return false;
		}
		if (codePostal == null) {
			if (other.codePostal != null) {
				return false;
			}
		} else if (!codePostal.equals(other.codePostal)) {
			return false;
		}
		if (dateNaissance == null) {
			if (other.dateNaissance != null) {
				return false;
			}
		} else if (!dateNaissance.equals(other.dateNaissance)) {
			return false;
		}
		if (diplome == null) {
			if (other.diplome != null) {
				return false;
			}
		} else if (!diplome.equals(other.diplome)) {
			return false;
		}
		if (domaine == null) {
			if (other.domaine != null) {
				return false;
			}
		} else if (!domaine.equals(other.domaine)) {
			return false;
		}
		if (prenom == null) {
			if (other.prenom != null) {
				return false;
			}
		} else if (!prenom.equals(other.prenom)) {
			return false;
		}
		if (sexe == null) {
			if (other.sexe != null) {
				return false;
			}
		} else if (!sexe.equals(other.sexe)) {
			return false;
		}
		if (tel == null) {
			if (other.tel != null) {
				return false;
			}
		} else if (!tel.equals(other.tel)) {
			return false;
		}
		if (ville == null) {
			if (other.ville != null) {
				return false;
			}
		} else if (!ville.equals(other.ville)) {
			return false;
		}
		return true;
	}
}