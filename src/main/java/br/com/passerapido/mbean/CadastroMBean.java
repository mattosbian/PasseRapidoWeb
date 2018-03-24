package br.com.passerapido.mbean;

import java.io.Serializable;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.passerapido.dominio.Cadastro;
import br.com.passerapido.exception.CadastroException;
import br.com.passerapido.util.JsfUtil;

@ManagedBean
@ViewScoped
public class CadastroMBean implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cadastro cadastro;

	public Cadastro getCadastro() {
		return cadastro;
	}

	public void setCadastro(Cadastro cadastro) {
		this.cadastro = cadastro;
	}
	
	public void salvar() {
		
		try {
			if (cadastro.isNovo()) {
				cadastro.novoCadastro();
			}else {
				cadastro.salvar();
			}
			JsfUtil.addMensagem("Cadastro realizado com sucesso");
		} catch (CadastroException e) {

			JsfUtil.addMensagemDeErro(e.getMessage());
		}
	}
	
}
