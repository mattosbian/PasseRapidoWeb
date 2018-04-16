package br.com.passerapido.dominio;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.passerapido.entity.TbEndereco;
import br.com.passerapido.exception.DominioException;
import br.com.passerapido.util.EntityManagerUtil;

public class Endereco {

	private Integer idCliente;
	
	private String sgEstado;
	
	private String nrCep;
	
	private String nmCidade;
	
	private String nmBairro;
	
	private String dsEndereco;
	
	private Integer nrEndereco;	
	
	private String dsComplemento;
	
	public Endereco(TbEndereco tbE) {

		this.idCliente = tbE.getIdCliente();
		this.sgEstado = tbE.getSgEstado();
		this.nrCep = tbE.getNrCep();
		this.nmCidade = tbE.getNmCidade();
		this.nmBairro = tbE.getNmBairro();
		this.dsEndereco = tbE.getDsEndereco();
		this.nrEndereco = tbE.getNrEndereco();
		this.dsComplemento = tbE.getDsComplemento();

	}

	public Endereco() {
	}

	public TbEndereco toEntity() {
		TbEndereco tbE = new TbEndereco();
		
		tbE.setIdCliente(this.idCliente);
		tbE.setSgEstado(this.sgEstado);
		tbE.setNrCep(this.nrCep);
		tbE.setNmCidade(this.nmCidade);
		tbE.setNmBairro(this.nmBairro);
		tbE.setDsEndereco(this.dsEndereco);
		tbE.setNrEndereco(this.nrEndereco);
		tbE.setDsComplemento(this.dsComplemento);
		
		return tbE;
	}
	
	public void validate() throws DominioException {
		if (this.nrCep == null || this.nrCep.isEmpty()) {
			throw new DominioException("CEP deve ser preenchido");
		}

		if (this.sgEstado == null || this.sgEstado.isEmpty()) {
			throw new DominioException("Estado deve ser preenchido");
		}

	}

	
	public static Endereco buscaPorIdCliente(Integer idCliente){
		
		EntityManager em = EntityManagerUtil.getEntityManager();

		TypedQuery<TbEndereco> query = em.createNamedQuery(TbEndereco.POR_ID_CLIENTE, TbEndereco.class);
		
		query.setParameter("idCliente", idCliente);

		TbEndereco tbEndereco;
		
		try {
			tbEndereco = query.getSingleResult();
		} catch (NoResultException e) {
			return new Endereco();
		}

		return new Endereco(tbEndereco);
	}

	
	
	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getSgEstado() {
		return sgEstado;
	}

	public void setSgEstado(String sgEstado) {
		this.sgEstado = sgEstado;
	}

	public String getNrCep() {
		return nrCep;
	}

	public void setNrCep(String nrCep) {
		this.nrCep = nrCep;
	}

	public String getNmCidade() {
		return nmCidade;
	}

	public void setNmCidade(String nmCidade) {
		this.nmCidade = nmCidade;
	}

	public String getNmBairro() {
		return nmBairro;
	}

	public void setNmBairro(String nmBairro) {
		this.nmBairro = nmBairro;
	}

	public String getDsEndereco() {
		return dsEndereco;
	}

	public void setDsEndereco(String dsEndereco) {
		this.dsEndereco = dsEndereco;
	}

	public Integer getNrEndereco() {
		return nrEndereco;
	}

	public void setNrEndereco(Integer nrEndereco) {
		this.nrEndereco = nrEndereco;
	}

	public String getDsComplemento() {
		return dsComplemento;
	}

	public void setDsComplemento(String dsComplemento) {
		this.dsComplemento = dsComplemento;
	}

	public boolean isNovo() {
		return this.idCliente == null;
	}

	
}
