package br.com.passerapido.dominio;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.passerapido.entity.TbUsuario;
import br.com.passerapido.exception.LoginException;
import br.com.passerapido.util.EntityManagerUtil;

public class Login {
	
	private long cpf;
	private String senha;
	
	
	
	public Login() {
	}

	public void entrarNoSistema() throws LoginException {

		TbUsuario tbUsuario;
		try {
			Query query  = getEntiManager().createNamedQuery(TbUsuario.POR_CPF_SENHA);
			query.setParameter("cpf",new BigDecimal(cpf));
			query.setParameter("senha", senha);
			tbUsuario = (TbUsuario) query.getSingleResult();
		} catch (NoResultException e) {
			throw new LoginException("Cpf ou senha inválida",e);
		
		}
				
			
	}
	
	
	private EntityManager getEntiManager() {
		return EntityManagerUtil.getEntityManager();
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	

}
