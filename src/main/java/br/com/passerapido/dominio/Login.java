package br.com.passerapido.dominio;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.passerapido.entity.TbCliente;
import br.com.passerapido.exception.LoginException;
import br.com.passerapido.util.EntityManagerUtil;

public class Login {
	
	private String cpf;
	private String senha;
	private boolean novo;
	
	public Login() {
		this.novo = false;
	}

	public static Login getUsuarioLogado() {
		FacesContext context = FacesContext.getCurrentInstance();
		Login login = (Login) context.getExternalContext().getSessionMap().get("cpfLogado");
		return login;
	}

	public static void removeUsuarioLogado() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("cpfLogado");
	}

	public void setUsuarioLogado() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put("cpfLogado", this);
	}
	
	public void entrarNoSistema() throws LoginException {

		try {
			Query query  = getEntiManager().createNamedQuery(TbCliente.POR_CPF_SENHA);
			query.setParameter("cpf",cpf);
			query.setParameter("senha", senha);
			query.getSingleResult();
		} catch (NoResultException e) {
			throw new LoginException("Não existe Cliente",e);
		}
	}

	
	public boolean existeCPF() {
		Query query  = getEntiManager().createNamedQuery(TbCliente.COUNT_POR_CPF);
		query.setParameter("cpf",cpf);
		long countCPF = (Long) query.getSingleResult();

		if (countCPF != 0) {
			return true;
		}
		
		return false;
	}
	
	
	private EntityManager getEntiManager() {
		return EntityManagerUtil.getEntityManager();
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isNovo() {
		return novo;
	}

	public void novo() {
		this.novo = true;
	}

}
