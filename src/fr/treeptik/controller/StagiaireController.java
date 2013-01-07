package fr.treeptik.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import fr.treeptik.model.Evaluation;
import fr.treeptik.model.Formation;
import fr.treeptik.model.Stagiaire;
import fr.treeptik.service.FormationEJB;
import fr.treeptik.service.StagiaireEJB;

@ManagedBean
@SessionScoped
public class StagiaireController implements Serializable {
	
	@EJB
	private StagiaireEJB stagiaireEJB;
	
	@EJB
	private FormationEJB formationEJB;
	
	private Formation formation = new Formation();
	private Evaluation evaluation = new Evaluation();
	private Stagiaire stagiaire = new Stagiaire();
	
	private List<Stagiaire> listStagiaires = new ArrayList<Stagiaire>();
	private List<SelectItem> selectFormation;
	private List<SelectItem> selectNom = new ArrayList<SelectItem>();
	
	@SuppressWarnings("rawtypes")
	private DataModel stagiaires;

	
	
  
	public String doSelectUpdate() {
		stagiaire = (Stagiaire) stagiaires.getRowData();
		return "updateStagiaire";
	}
	
	public String doUpdate() {
		stagiaireEJB.update(stagiaire);
		getStagiaires();
		return "messagestagiaireUpdate";
	}
	
	public String doDelete() {
		Stagiaire stagiaire = (Stagiaire) stagiaires.getRowData();
		stagiaireEJB.delete(stagiaire);
		getStagiaires();
		return "listStagiaires";
	}
		
	public String doCreate() {
		stagiaireEJB.create(stagiaire);	
		listStagiaires = stagiaireEJB.findAll();
		getStagiaires();
		return "listStagiaires";
	}
	
	public String doAddStagiaire(){
//		stagiaire.setFormation(new Formation());
//		stagiaire.getFormation().setId(formation.getId());
		stagiaireEJB.create(stagiaire);
		return "messageStagiaireCree";
	}
		
	public String doFindAll() {
		listStagiaires = stagiaireEJB.findAll();
		return "listStagiaires";
	}
	
	public String findStagiaireInSession(){
		return"listDeStagiaireParSessionID";
	}
	
	public String doFindById(){
	    stagiaire = stagiaireEJB.findById(stagiaire.getId());
		return"stagiaireDonnées";
	}
	
	public String doFindByIdToUpdate(){
	    stagiaire = stagiaireEJB.findById(stagiaire.getId());
		return"updateDonnéesStagiaire";
	}
	
	public String doRecherche(){
		return"recherche";
	}
	
	public StagiaireEJB getStagiareEJB() {
		return stagiaireEJB;
	}

	public void setStagiareEJB(StagiaireEJB stagiareEJB) {
		this.stagiaireEJB = stagiareEJB;
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}


	public List<Stagiaire> getListStagiaires() {
		listStagiaires = stagiaireEJB.findAll();
		return listStagiaires;
	}

	public void setListStagiaires(List<Stagiaire> listStagiaires) {
		this.listStagiaires = listStagiaires;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

//	public List<SelectItem> getSelectFormation() {
//		selectFormation = new ArrayList<SelectItem>();
//		List<Formation> allFormations = formationEJB.findAllFormations();
//		for (Formation formation : allFormations) {
//			
//			selectFormation.add(new SelectItem(formation.getId(), formation.getNom()));
//		}
//		
//		return selectFormation;
//	}

//	public void setSelectFormation(List<SelectItem> selectFormation) {
//		this.selectFormation = selectFormation;
//	}

	public List<SelectItem> getSelectNom() {
		selectNom = new ArrayList<SelectItem>();
		List<Stagiaire> allNames = stagiaireEJB.findAll();
		for(Stagiaire stagiaire : allNames){
			selectNom.add(new SelectItem(stagiaire.getId(), stagiaire.getNom()));
		}
		
		return selectNom;
	}

	public void setSelectNom(List<SelectItem> selectNom) {
	this.selectNom = selectNom;
	}

	public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getStagiaires() {
		if (stagiaires == null) {
			stagiaires = new ListDataModel();
			stagiaires.setWrappedData(stagiaireEJB.findAll());
		}
		return stagiaires;
	}

	@SuppressWarnings("rawtypes")
	public void setStagiaires(DataModel stagiaires) {
		this.stagiaires = stagiaires;
	}
	
	
	
	
	
	
	
	
	
	//Test Primefaces
		private final static List<String> VALID_COLUMN_KEYS = Arrays.asList("id", "nom", "prenom", "dateNaissance", 
		"adresse", "codePostal", "ville", "tel", "mail", "sexe", "diplome", "domaine"); 
	    private String columnTemplate = "id nom prenom ville";
	    private List<ColumnModel> columns = new ArrayList<ColumnModel>();
		private List<Stagiaire> filteredStagiaires;  
	    //private List<Car> cars;  
	    private Stagiaire selectedStagiaire;  
	    private Stagiaire[] selectedStagiaires;  
	    @SuppressWarnings("serial")
		static public class ColumnModel implements Serializable {  
	    	  
	        private String header;  
	        private String property;  
	  
	        public ColumnModel(String header, String property) {  
	            this.header = header;  
	            this.property = property;  
	        }  
	  
	        public String getHeader() {  
	            return header;  
	        }  
	  
	        public String getProperty() {  
	            return property;  
	        }  
	    }  
	      
	    public String getColumnTemplate() {  
	        return columnTemplate;  
	    }  
	    public void setColumnTemplate(String columnTemplate) {  
	        this.columnTemplate = columnTemplate;  
	    }  
	      
	    public void updateColumns() {  
	        //reset table state  
	        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form:listStagiaires");  
	        table.setValueExpression("sortBy", null);  
	          
	        //update columns  
	        createDynamicColumns();  
	    }  
	      
	    public void createDynamicColumns() {  
	        String[] columnKeys = columnTemplate.split(" ");  
	        columns.clear();        
	          
	        for(String columnKey : columnKeys) {  
	            String key = columnKey.trim();  
	              
	            if(VALID_COLUMN_KEYS.contains(key)) {  
	                columns.add(new ColumnModel(columnKey.toUpperCase(), columnKey));  
	            }  
	        }  
	    }

		public List<ColumnModel> getColumns() {
			return columns;
		}

		public void setColumns(List<ColumnModel> columns) {
			this.columns = columns;
		}

		public List<Stagiaire> getFilteredStagiaires() {
			return filteredStagiaires;
		}

		public void setFilteredStagiaires(List<Stagiaire> filteredStagiaires) {
			this.filteredStagiaires = filteredStagiaires;
		}

		public Stagiaire getSelectedStagiaire() {
			return selectedStagiaire;
		}

		public void setSelectedStagiaire(Stagiaire selectedStagiaire) {
			this.selectedStagiaire = selectedStagiaire;
		}

		public Stagiaire[] getSelectedStagiaires() {
			return selectedStagiaires;
		}

		public void setSelectedStagiaires(Stagiaire[] selectedStagiaires) {
			this.selectedStagiaires = selectedStagiaires;
		}

		public static List<String> getValidColumnKeys() {
			return VALID_COLUMN_KEYS;
		}  
}
