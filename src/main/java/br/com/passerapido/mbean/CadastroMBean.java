package br.com.passerapido.mbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.passerapido.dominio.Cadastro;
import br.com.passerapido.dominio.Cliente;
import br.com.passerapido.dominio.ContaBancaria;
import br.com.passerapido.dominio.Endereco;
import br.com.passerapido.dominio.Login;
import br.com.passerapido.dominio.Tag;
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
	private Endereco endereco;
	private ContaBancaria conta;
	private List<Tag> tags;
	
	
	private Tag tag;
	private Tag tagSelecionada;

	
	
	public CadastroMBean() {
		FacesContext context = FacesContext.getCurrentInstance();
		Login login = (Login) context.getExternalContext().getSessionMap().get("cpfLogado");
		
		System.out.println("construct CadastroMBean cpf:" + login.getCpf());
		
		cadastro = new Cadastro(login);
		this.cliente = cadastro.getCliente();
		this.endereco = cadastro.getEndereco();
		this.conta = cadastro.getConta();
		this.tags = cadastro.getTags();
		
		this.tag = new Tag(); 
	}

	public Cadastro getCadastro() {
		return cadastro;
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public ContaBancaria getConta() {
		return conta;
	}
	
	public void setCadastro(Cadastro cadastro) {
		this.cadastro = cadastro;
	}
	
	public String sair() {
		if (cadastro.isNovo()) {
			Login.removeUsuarioLogado();
			return "login?faces-redirect=true";
		}
		return "welcome?faces-redirect=true";
	}

	public void salvar() {
		try {
			if (cadastro.isNovo()) {
				System.out.println("Novo Cadastro");
				cadastro.novoCadastro();
			}else {
				System.out.println("Atualiza Cadastro");
				cadastro.salvar();
			}
			JsfUtil.addMensagem("Cadastro realizado com sucesso");
		} catch (CadastroException e) {
			JsfUtil.addMensagemDeErro(e.getMessage());
		}
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Tag getTagSelecionada() {
		return tagSelecionada;
	}

	public void setTagSelecionada(Tag tagSelecionada) {
		this.tagSelecionada = tagSelecionada;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public void salvaTag() {
		if (this.tagSelecionada == null) {
			this.tags.add(this.tag);
		} else {
			this.tagSelecionada.setAll(tag);
		}
		
		limpaTag();
	}

	public void selecionaTag(Tag tag) {
		this.tagSelecionada = tag;
		this.tag = new Tag(tag);
	}
	
	
	public void limpaTag() {
		this.tagSelecionada = null;
		this.tag = new Tag();
	}


	
}
