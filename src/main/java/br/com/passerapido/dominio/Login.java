package br.com.passerapido.dominio;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.passerapido.entity.TbCliente;
import br.com.passerapido.entity.TbUsuario;
import br.com.passerapido.exception.LoginException;
import br.com.passerapido.util.EntityManagerUtil;

public class Login {
	
	//private long cpf;
	private String cpf;
	private String senha;
	
	public Login() {
	}

	public void entrarNoSistema() throws LoginException {

		TbCliente tbCliente;
		try {
			Query query  = getEntiManager().createNamedQuery(TbCliente.POR_CPF_SENHA);
			//query.setParameter("cpf",new BigDecimal(cpf));
			query.setParameter("cpf",cpf);
			query.setParameter("senha", senha);
			tbCliente = (TbCliente) query.getSingleResult();
		} catch (NoResultException e) {
			throw new LoginException("Não existe Cliente",e);
		}
				
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
	
	
	

}
