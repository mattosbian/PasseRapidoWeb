package br.com.passerapido.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class JsfUtil {
	
	
	public static void addMensagem(String mensagem) {
		addMensagem(FacesMessage.SEVERITY_INFO,mensagem);
	}

	public static void addMensagemDeErro(String mensagem) {
		addMensagem(FacesMessage.SEVERITY_ERROR,mensagem);
		
	}

	private static void addMensagem(Severity severity, String mensagem) {
		FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage(severity, "", mensagem);
        fc.addMessage("", fm);
	}

}
