package fr.treeptik.dao;

import javax.ejb.Stateless;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import fr.treeptik.model.Formateur;

@Stateless
public class FormateurDAO extends GenericDAO<Formateur> {

	public FormateurDAO() {
		super(Formateur.class);
	}
/*	
	public Converter getConverter() {
	    return new Converter() {
	      @Override
	      public Object getAsObject(
	          FacesContext fc,
	          UIComponent uic,
	          String string) {
	        return (string == null)
	          ? null
	          : em.find(Formateur.class,
	                    Integer.parseInt(string));
	      }
	      @Override
	      public String getAsString(
	          FacesContext fc,
	          UIComponent uic,
	          Object o) {
	        return (o == null)
	          ? null
	          : Long.toString(((Formateur) o).getId());
	      }
	    };
	  }
	  */
}
