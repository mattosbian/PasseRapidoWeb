package br.com.passerapido.mbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
//@RequestScoped
@ViewScoped
public class TesteMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String campo1;

	private String combo1;

	public String getCampo1() {
		return campo1;
	}
	
	public void setCampo1(String campo1) {
		this.campo1 = campo1;
	}
	
	public void salva() {
		System.out.println(campo1);
		//return "teste?faces-redirect=true";
	}

	public String getCombo1() {
		return combo1;
	}

	public void setCombo1(String combo1) {
		this.combo1 = combo1;
	}


}
