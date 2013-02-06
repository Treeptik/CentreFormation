package fr.treeptik.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import fr.treeptik.model.Formateur;
import fr.treeptik.service.FormateurEJB;

@SuppressWarnings("serial")
@ManagedBean
@RequestScoped
@FacesConverter(value = "formateurConverter", forClass = Formateur.class)
public class FormateurConverter implements Converter, Serializable {
	@EJB
	private FormateurEJB formateurEJB;

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component,
			String value) {
		Integer id = Integer.valueOf(value);
		Formateur formateur = (Formateur) formateurEJB.findById(id);
		return formateur;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		int id = ((Formateur) o).getId();
		return String.valueOf(id);
	}
}
