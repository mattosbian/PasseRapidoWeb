package br.com.passerapido.mbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.passerapido.dominio.Cadastro;
import br.com.passerapido.dominio.Cliente;
import br.com.passerapido.entity.TbEstadoCivil;
import br.com.passerapido.entity.TbGenero;
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
	private Cliente cliente;

	private List<TbGenero> tbGeneros;
	private List<TbEstadoCivil> tbEstadosCivis;

	public CadastroMBean() {
		this.cadastro = new Cadastro();
		
		this.cliente = this.cadastro.getCliente();
		this.tbGeneros = this.cadastro.getTbGeneros();
		this.tbEstadosCivis = this.cadastro.getTbEstadosCivis();
	}
	
	public Cadastro getCadastro() {
		return cadastro;
	}

	public void setCadastro(Cadastro cadastro) {
		this.cadastro = cadastro;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public List<TbGenero> getTbGeneros() {
		return tbGeneros;
	}
	
	public List<TbEstadoCivil> getTbEstadosCivis() {
		return tbEstadosCivis;
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
