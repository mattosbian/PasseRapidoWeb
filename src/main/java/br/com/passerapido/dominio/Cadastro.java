package br.com.passerapido.dominio;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.com.passerapido.dominio.Cliente;
import br.com.passerapido.entity.TbCliente;
import br.com.passerapido.entity.TbEndereco;
import br.com.passerapido.exception.CadastroException;
import br.com.passerapido.exception.DominioException;
import br.com.passerapido.util.EntityManagerUtil;

public class Cadastro {
	
	private Cliente cliente;
	
	public Cadastro() {
		cliente = new Cliente();
	}
	
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void novoCadastro() throws CadastroException {	
		
		EntityManager em = EntityManagerUtil.getEntityManager();
		
		try {
			cliente.validate();

			TbCliente tbCliente = cliente.toEntity();
			TbEndereco tbEndereco = cliente.getEndereco().toEntity();
			
			em.getTransaction().begin();
			
			em.persist(tbCliente);
			em.persist(tbEndereco);
			
			em.getTransaction().commit();
		
		} catch (DominioException e) {
			throw new CadastroException(e.getMessage(),e);
		} catch (PersistenceException e) {
           em.getTransaction().rollback();             
		}	
		
	}


	public boolean isNovo() {
		
		return cliente.isNovo();
	}


	public void salvar() {

	}

}
