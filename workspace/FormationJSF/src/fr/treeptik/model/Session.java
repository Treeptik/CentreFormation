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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
public class Session implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String nom;
	
	@Temporal(TemporalType.DATE)
	private Date dateDebutStage;
	@Temporal(TemporalType.DATE)
	private Date dateFinStage;
	
    @OneToMany(cascade =CascadeType.PERSIST)
    private List<Formateur> formateurs = new ArrayList<Formateur>();
    
    @OneToMany(cascade=CascadeType.PERSIST)
    private List<Formation> formations = new ArrayList<Formation>();
	
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Stagiaire> stagiaires = new ArrayList<Stagiaire>();
    
    public Session() {
		super();
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
    
    
    
    
    

}
