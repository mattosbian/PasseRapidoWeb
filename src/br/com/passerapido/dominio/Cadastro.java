package br.com.passerapido.dominio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.passerapido.entity.TbCliente;
import br.com.passerapido.entity.TbEstadoCivil;
import br.com.passerapido.entity.TbGenero;
import br.com.passerapido.exception.CadastroException;
import br.com.passerapido.exception.DominioException;
import br.com.passerapido.util.EntityManagerUtil;

public class Cadastro {
	
	private Cliente cliente;

	private List<TbGenero> tbGeneros;
	private List<TbEstadoCivil> tbEstadosCivis;
	
	private TbGenero tbGenero;
	private TbEstadoCivil tbEstadoCivil;
	
	public Cadastro() {
		cliente = new Cliente(); // ja estava
		
		this.tbGeneros = listaTbGenero();
		this.tbEstadosCivis = listaTbEstadoCivil();
		
		System.out.println("Cadastro construtor genero :" + this.tbGeneros.size());
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
	
	public void novoCadastro() throws CadastroException {	
		
		EntityManager em = EntityManagerUtil.getEntityManager();
		
		try {
			cliente.validate();

			TbCliente tbCliente = cliente.toEntity();
			//TbEndereco tbEndereco = cliente.getEndereco().toEntity();
			
			em.getTransaction().begin();
			
			em.persist(tbCliente);
			//em.persist(tbEndereco);
			
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
		// TODO Auto-generated method stub
		
	}

	private List<TbGenero> listaTbGenero() {
		EntityManager em = EntityManagerUtil.getEntityManager();
		
		try {
			Query query  = em.createNamedQuery(TbGenero.TODOS, TbGenero.class);
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	private List<TbEstadoCivil> listaTbEstadoCivil() {
		EntityManager em = EntityManagerUtil.getEntityManager();
		
		try {
			Query query  = em.createNamedQuery(TbEstadoCivil.TODOS, TbEstadoCivil.class);
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public TbGenero getTbGenero() {
		return tbGenero;
	}

	public void setTbGenero(TbGenero tbGenero) {
		this.tbGenero = tbGenero;
	}

	public TbEstadoCivil getTbEstadoCivil() {
		return tbEstadoCivil;
	}

	public void setTbEstadoCivil(TbEstadoCivil tbEstadoCivil) {
		this.tbEstadoCivil = tbEstadoCivil;
	}


}
