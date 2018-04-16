package br.com.passerapido.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.passerapido.dominio.Genero;

@FacesConverter(value="GeneroToString")
public class GeneroToString implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String stringValue) {
		
		Genero genero = (Genero) component.getAttributes().get(stringValue);
		if (genero == null) {
			genero = new Genero();
		}
		return genero;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component, Object objectValue) {

		if (objectValue instanceof Genero) {
	        Genero genero = (Genero) objectValue;
	        String chave = String.valueOf(genero.getCdGenero()); 
	        component.getAttributes().put(chave, genero);
	        return chave;
		}
		return null;
	}

}
