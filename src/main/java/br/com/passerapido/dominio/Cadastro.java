package br.com.passerapido.dominio;

import java.util.ArrayList;
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
	private ContaBancaria conta;
	
	// deve ser lista de dominio e nao entidade
	private List<Genero> generos;
	private List<EstadoCivil> estadosCivis;
	
	//deve ser dominio e nao entidade
	private Genero genero;
	private EstadoCivil estadoCivil;
	
	public Cadastro() {
		cliente = new Cliente(); // ja estava
		
		this.generos = listaGenero();
		this.estadosCivis = listaEstadoCivil();
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public List<Genero> getGeneros() {
		return generos;
	}
	
	public List<EstadoCivil> getEstadosCivis() {
		return estadosCivis;
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

	private List<Genero> listaGenero() {
		EntityManager em = EntityManagerUtil.getEntityManager();

		List<TbGenero> tbLista;
		
		try {
			Query query  = em.createNamedQuery(TbGenero.TODOS, TbGenero.class);
			tbLista = query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
		
		List<Genero> listaGeneros = new ArrayList<Genero>();
		for (TbGenero tbGenero : tbLista) {
			listaGeneros.add(new Genero(tbGenero));
		}
		
		return listaGeneros;
	}

	private List<EstadoCivil> listaEstadoCivil() {
		EntityManager em = EntityManagerUtil.getEntityManager();
		List<TbEstadoCivil> tbLista;
		
		try {
			Query query  = em.createNamedQuery(TbEstadoCivil.TODOS, TbEstadoCivil.class);
			tbLista = query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
		
		List<EstadoCivil> listaEstadosCivis = new ArrayList<EstadoCivil>();
		
		for (TbEstadoCivil tbEstadoCivil : tbLista) {
			listaEstadosCivis.add(new EstadoCivil(tbEstadoCivil));
		}
		
		return listaEstadosCivis;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}


}
