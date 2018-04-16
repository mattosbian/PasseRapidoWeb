package br.com.passerapido.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.passerapido.entity.TbCliente;
import br.com.passerapido.entity.TbContaBancaria;
import br.com.passerapido.entity.TbEndereco;
import br.com.passerapido.entity.TbEstado;
import br.com.passerapido.entity.TbEstadoCivil;
import br.com.passerapido.entity.TbGenero;
import br.com.passerapido.entity.TbTag;
import br.com.passerapido.exception.CadastroException;
import br.com.passerapido.exception.DominioException;
import br.com.passerapido.util.EntityManagerUtil;

public class Cadastro {
	
	private Cliente cliente;
	private ContaBancaria conta;
	private Endereco endereco;

	//deve ser dominio e nao entidade
	//private Genero genero;
	//private EstadoCivil estadoCivil;
	
	
	// deve ser lista de dominio e nao entidade
	private List<Genero> generos;
	private List<EstadoCivil> estadosCivis;
	private List<String> estados;
	private List<Tag> tags;
	

	private void inicio() {
		cliente = new Cliente(); // ja estava
		cliente.setGenero(new Genero());
		cliente.setEstadoCivil(new EstadoCivil());
		conta = new ContaBancaria();
		endereco = new Endereco();
		tags = new ArrayList<Tag>();
		
		carregaCombos();
	}
	
	private void carregaCombos() {
		this.generos = listaGenero();
		this.estadosCivis = listaEstadoCivil();
		this.estados = listaEstado();
	}

	public Cadastro(Login login) {
		
		if (login == null) {
			inicio();
		} else if (login.isNovo()) {
			inicio();
			this.cliente.setCdsCPF(login.getCpf());
		} else {
			carregaDados(login.getCpf());
		}
		
	}

	private void carregaDados(String cpf) {
		cliente = Cliente.buscaPorCPF(cpf);
		//this.genero = cliente.getGenero();
		//this.estadoCivil = cliente.getEstadoCivil();
		
		this.tags = Tag.buscaPorIdCliente(cliente.getId());

		System.out.println("Cadastro carregaDados idCliente:" + cliente.getId());
		
		endereco = Endereco.buscaPorIdCliente(cliente.getId());
		conta = ContaBancaria.buscaPorIdCliente(cliente.getId());

		carregaCombos();
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
	
	public List<Genero> getGeneros() {
		return generos;
	}
	
	public List<EstadoCivil> getEstadosCivis() {
		return estadosCivis;
	}

	public List<String> getEstados() {
		return estados;
	}

//	public Genero getGenero() {
//		return genero;
//	}

//	public void setGenero(Genero genero) {
//		this.genero = genero;
//	}

//	public EstadoCivil getEstadoCivil() {
//		return estadoCivil;
//	}

//	public void setEstadoCivil(EstadoCivil estadoCivil) {
//		this.estadoCivil = estadoCivil;
//	}

	
	public void novoCadastro() throws CadastroException {	
		
		EntityManager em = EntityManagerUtil.getEntityManager();
		
		try {
			cliente.validate();
			conta.validate();
			endereco.validate();
			
			List<TbTag> tbTags = new ArrayList<TbTag>();

			for (Tag t : tags) {
				t.validate();
				tbTags.add(t.toEntity());
			}

			TbCliente tbCliente = cliente.toEntity();
			TbEndereco tbEndereco = endereco.toEntity();
			TbContaBancaria tbContaBancaria = conta.toEntity();

			
			
			em.getTransaction().begin();
			
			em.persist(tbCliente);
			
			tbEndereco.setIdCliente(tbCliente.getIdCliente());
			em.persist(tbEndereco);
			
			tbContaBancaria.setIdCliente(tbCliente.getIdCliente());
			em.persist(tbContaBancaria);

			for (TbTag tb : tbTags) {
				tb.setIdCliente(tbCliente.getIdCliente());
				
				em.persist(tb);
			}

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


	public void salvar() throws CadastroException {
		EntityManager em = EntityManagerUtil.getEntityManager();
		
		try {
			System.out.println("Cadastro - antes validate");
			cliente.validate();
			conta.validate();
			endereco.validate();

			System.out.println("Cadastro - tag validate");
			for (Tag t : tags) {
				t.validate();
			}

			System.out.println("Cadastro - to entity");

			TbCliente tbCliente = cliente.toEntity();
			TbEndereco tbEndereco = endereco.toEntity();
			TbContaBancaria tbContaBancaria = conta.toEntity();

			System.out.println("Cadastro - begin");
			
			em.getTransaction().begin();
			
			tbCliente = em.merge(tbCliente);

			if (endereco.isNovo()) {
				tbEndereco.setIdCliente(tbCliente.getIdCliente());
				em.persist(tbEndereco);
			}else {
				tbEndereco = em.merge(tbEndereco);
			}
			

			if (conta.isNovo()) {
				tbContaBancaria.setIdCliente(tbCliente.getIdCliente());
				em.persist(tbContaBancaria);
			} else {
				tbContaBancaria = em.merge(tbContaBancaria);
			}
			

			for (Tag t : tags) {
				if (t.isNovo()) {
					t.setIdCliente(cliente.getId());
					em.persist(t.toEntity());
				} else {
					em.merge(t.toEntity());
				}
			}

			em.getTransaction().commit();
		
		} catch (DominioException e) {
			throw new CadastroException(e.getMessage(),e);
		} catch (PersistenceException e) {
           em.getTransaction().rollback();             
		}	
		
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

	private List<String> listaEstado() {
		EntityManager em = EntityManagerUtil.getEntityManager();

		List<TbEstado> tbLista;
		
		try {
			Query query  = em.createNamedQuery(TbEstado.TODOS, TbEstado.class);
			tbLista = query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
		
		List<String> listaEstados = new ArrayList<String>();
		for (TbEstado tbEstado : tbLista) {
			//listaEstados.add(new Estado(tbEstado));
			listaEstados.add(tbEstado.getSgEstado());
		}
		
		return listaEstados;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}



}
