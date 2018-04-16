package br.com.passerapido.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.passerapido.dominio.EstadoCivil;

@FacesConverter(value="EstadoCivilToString")
public class EstadoCivilToString implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String stringValue) {
		
		EstadoCivil estadoCivil = (EstadoCivil) component.getAttributes().get(stringValue);
		if (estadoCivil == null) {
			estadoCivil = new EstadoCivil();
		}
		return estadoCivil;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component, Object objectValue) {
		if (objectValue instanceof EstadoCivil) {
	        EstadoCivil estadoCivil = (EstadoCivil) objectValue;
	        String chave = String.valueOf(estadoCivil.getcdEstadoCivil()); 
	        component.getAttributes().put(chave, estadoCivil);
	        return chave;
		}
		return null;
	}

}
