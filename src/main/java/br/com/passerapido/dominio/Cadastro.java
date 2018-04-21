package br.com.passerapido.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.com.passerapido.entity.TbCliente;
import br.com.passerapido.entity.TbContaBancaria;
import br.com.passerapido.entity.TbEndereco;
import br.com.passerapido.entity.TbTag;
import br.com.passerapido.exception.CadastroException;
import br.com.passerapido.exception.DominioException;
import br.com.passerapido.util.EntityManagerUtil;

public class Cadastro {
	
	private Cliente cliente;
	private ContaBancaria conta;
	private Endereco endereco;

	private List<Genero> generos;
	private List<EstadoCivil> estadosCivis;
	private List<String> estados;
	private List<Tag> tags;
	

	private void inicio() {
		cliente = new Cliente();
		cliente.setGenero(new Genero());
		cliente.setEstadoCivil(new EstadoCivil());
		conta = new ContaBancaria();
		endereco = new Endereco();
		tags = new ArrayList<Tag>();
		
		carregaCombos();
	}
	
	private void carregaCombos() {
		this.generos = Genero.buscaTodos();
		this.estadosCivis = EstadoCivil.buscaTodos();
		this.estados = Estado.buscaTodosSigla();
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
		
		this.tags = Tag.buscaPorIdClienteAtivas(cliente.getId());

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

	public void novoCadastro() throws CadastroException {	
		
		EntityManager em = EntityManagerUtil.getEntityManager();
		
		try {
			cliente.validate();
			conta.validate();
			endereco.validate();
			if (tags.size() == 0) {
				throw new CadastroException("Deve ser cadastrado algum veículo"); 
			}
			
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
			
			if (conta.isAlgumCampoPreenchido()) {
				tbContaBancaria.setIdCliente(tbCliente.getIdCliente());
				em.persist(tbContaBancaria);
			}

			for (TbTag tb : tbTags) {
				tb.setIdCliente(tbCliente.getIdCliente());
				
				em.persist(tb);
			}

			em.getTransaction().commit();
		
			cliente.setId(tbCliente.getIdCliente());
			
		} catch (DominioException e) {
			throw new CadastroException(e.getMessage(),e);
		} catch (PersistenceException e) {
           em.getTransaction().rollback();
           throw new CadastroException(e.getMessage(),e);
		}	
		
	}


	public boolean isNovo() {
		return cliente.isNovo();
	}


	public void salvar() throws CadastroException {
		EntityManager em = EntityManagerUtil.getEntityManager();
		
		try {
			cliente.validate();
			conta.validate();
			endereco.validate();

			for (Tag t : tags) {
				t.validate();
			}

			TbCliente tbCliente = cliente.toEntity();
			TbEndereco tbEndereco = endereco.toEntity();
			TbContaBancaria tbContaBancaria = conta.toEntity();

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

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}



}
