package br.com.passerapido.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.passerapido.dominio.Tag;

@FacesConverter(value="TagToString")
public class TagToString implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String stringValue) {
		
		Tag tag = (Tag) component.getAttributes().get(stringValue);
		if (tag == null) {
			tag = new Tag();
		}
		return tag;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component, Object objectValue) {

		if (objectValue instanceof Tag) {
	        Tag tag = (Tag) objectValue;
	        String chave = String.valueOf(tag.getCdTag()); 
	        component.getAttributes().put(chave, tag);
	        return chave;
		}
		return null;
	}

}
