package br.com.passerapido.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.passerapido.dominio.TipoTransacao;

@FacesConverter(value="TipoTransacaoToString")
public class TipoTransacaoToString implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String stringValue) {
		
		TipoTransacao tipoTransacao = (TipoTransacao) component.getAttributes().get(stringValue);
		if (tipoTransacao == null) {
			tipoTransacao = new TipoTransacao();
		}
		return tipoTransacao;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component, Object objectValue) {

		if (objectValue instanceof TipoTransacao) {
	        TipoTransacao tipoTransacao = (TipoTransacao) objectValue;
	        String chave = String.valueOf(tipoTransacao.getCdTipoTransacao()); 
	        component.getAttributes().put(chave, tipoTransacao);
	        return chave;
		}
		return null;
	}

}
