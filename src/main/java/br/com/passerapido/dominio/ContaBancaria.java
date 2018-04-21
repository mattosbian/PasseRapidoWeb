package br.com.passerapido.dominio;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.passerapido.entity.TbContaBancaria;
import br.com.passerapido.exception.DominioException;
import br.com.passerapido.util.EntityManagerUtil;

public class ContaBancaria {

	private Integer idCliente;

	private String nmBanco;

	private String nrAgencia;

	private String nrConta;

	private Integer nrDigito;

	public ContaBancaria() {
		
	}
	
	public ContaBancaria(TbContaBancaria tbConta) {
		this.idCliente = tbConta.getIdCliente();
		this.nmBanco = tbConta.getNmBanco();
		this.nrAgencia = tbConta.getNrAgencia();
		this.nrConta = tbConta.getNrConta();
		this.nrDigito = tbConta.getNrDigito();
	}

	public TbContaBancaria toEntity() {
		TbContaBancaria tbConta = new TbContaBancaria();
		
		tbConta.setIdCliente(this.idCliente);
		tbConta.setNmBanco(this.nmBanco);
		tbConta.setNrAgencia(this.nrAgencia);
		tbConta.setNrConta(this.nrConta);
		tbConta.setNrDigito(this.nrDigito);
		
		return tbConta;
	}
	
	public void validate() throws DominioException {

	}

	public static ContaBancaria buscaPorIdCliente(Integer idCliente){
		
		EntityManager em = EntityManagerUtil.getEntityManager();
		
		TypedQuery<TbContaBancaria> query = em.createNamedQuery(TbContaBancaria.POR_ID_CLIENTE, TbContaBancaria.class);
		
		query.setParameter("idCliente", idCliente);
		
		TbContaBancaria tbContaBancaria;
		try {
			tbContaBancaria = query.getSingleResult();
		} catch (NoResultException e) {
			return new ContaBancaria();
		}

		return new ContaBancaria(tbContaBancaria);
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNmBanco() {
		return nmBanco;
	}

	public void setNmBanco(String nmBanco) {
		this.nmBanco = nmBanco;
	}

	public String getNrAgencia() {
		return nrAgencia;
	}

	public void setNrAgencia(String nrAgencia) {
		this.nrAgencia = nrAgencia;
	}

	public String getNrConta() {
		return nrConta;
	}

	public void setNrConta(String nrConta) {
		this.nrConta = nrConta;
	}

	public Integer getNrDigito() {
		return nrDigito;
	}

	public void setNrDigito(Integer nrDigito) {
		this.nrDigito = nrDigito;
	}

	public boolean isNovo() {
		return this.idCliente == null;
	}
	
	public boolean isAlgumCampoPreenchido() {
		if ((this.nmBanco == null || this.nmBanco.isEmpty())
			&& (this.nrAgencia == null || nrAgencia.isEmpty())  
			&& (this.nrConta == null || this.nrConta.isEmpty()) 
			&& nrDigito == null) {
			return false;
		}
		return true;
	}

}
