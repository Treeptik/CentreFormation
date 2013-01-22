package fr.treeptik.controller;

import javax.faces.convert.ConverterException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public final class ConversionHelper {
	@PersistenceContext(unitName = "formation")
	protected EntityManager em;
    private ConversionHelper() {
    }
/*
    public static <T> T getAsObject(Class<T> returnType, String value) {

        if (returnType== null) {

            throw new NullPointerException("Trying to getAsObject with a null return type.");
        }

        if (value == null) {

            throw new NullPointerException("Trying to getAsObject with a null value.");
        }

        int id = (Integer) null;

        try {

            id = Integer.parseInt(value);

        } catch (NumberFormatException e) {

            throw new ConverterException("Trying to getAsObject with a wrong id format.");
        }
        /*
        try {

            Context initialContext = new InitialContext();
            EntityService entityService = (EntityService) initialContext.lookup("java:global/myapp/EntityService");

            T result = (T) entityService.find(returnType, id);

            return result;

        } catch (NamingException e) {

            throw new ConverterException("EntityService not found.");
        }
    }

    public static String getAsString(Object value) {

        if (value instanceof PersistentEntity) {

            PersistentEntity result = (PersistentEntity) value;

            return String.valueOf(result.getId());
        }

        return null;
    }*/
}
    

