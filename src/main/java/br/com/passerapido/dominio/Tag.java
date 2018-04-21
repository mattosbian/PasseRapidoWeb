package br.com.passerapido.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.passerapido.entity.TbTag;
import br.com.passerapido.exception.DominioException;
import br.com.passerapido.util.EntityManagerUtil;

public class Tag implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer cdTag;
	private Veiculo veiculo;
	private double vlSaldo;
	private Integer flAtivo;

	public Tag() {
		this.flAtivo = 1; //ativo
		this.vlSaldo = 0;
		
		this.veiculo = new Veiculo();
	}

	public Tag(Tag other) {
		this();
		copiaAtributos(other);
		this.veiculo = new Veiculo(other.veiculo);
	}

	public Tag(TbTag tbT) {
		this.cdTag = tbT.getCdTag();
		this.veiculo = new Veiculo(tbT.getTbVeiculo());
		this.vlSaldo = tbT.getVlSaldo();
		this.flAtivo = tbT.getFlAtivo();
	}

	private void copiaAtributos(Tag other) {
		this.cdTag = other.cdTag;
		this.vlSaldo = other.vlSaldo;
		this.flAtivo = other.flAtivo;
	}

	public void setAll(Tag other) {
		copiaAtributos(other);
		this.veiculo = other.veiculo;
	}

	public TbTag toEntity() {
		TbTag tbT = new TbTag();
		
		tbT.setCdTag(this.cdTag);
		tbT.setTbVeiculo(this.getVeiculo().toEntity());
		tbT.setVlSaldo(this.vlSaldo);
		tbT.setFlAtivo(this.flAtivo);
		
		return tbT;
	}
	
	
	public void validate() throws DominioException {

		if(this.veiculo == null) {
			throw new DominioException("Veículo deve ser preenchido");
		} else {
			this.veiculo.validate();
		}

		if(this.flAtivo == null || this.flAtivo == 0) {
			throw new DominioException("Flag deve ser preenchida");
		}

	}

	public void creditaSaldo(double valor) throws DominioException {
		if (valor <= 0 ) {
			throw new DominioException("Valor de crédito deve ser maior que zero");	
		}
		
		this.vlSaldo += valor;
	}

	public void debitaSaldo(double valor) throws DominioException {
		if (valor <= 0 ) {
			throw new DominioException("Valor de débito deve ser maior que zero");	
		}
		
		if (this.vlSaldo < valor) {
			throw new DominioException("Saldo insuficiente");
		}
		
		this.vlSaldo -= valor;
	}

	
	public static List<Tag> buscaPorIdClienteAtivas(Integer idCliente) {
		EntityManager em = EntityManagerUtil.getEntityManager();
		
		TypedQuery<TbTag> query = em.createNamedQuery(TbTag.POR_ID_CLIENTE_ATIVOS, TbTag.class);
		
		query.setParameter("idCliente", idCliente);
		
		List<TbTag> tbTags = query.getResultList();
		
		List<Tag> tags = new ArrayList<Tag>();
		
		for (TbTag tb : tbTags) {
			tags.add(new Tag(tb));
		}

		return tags;
	}

	public static List<Tag> buscaPorIdCliente(Integer idCliente) {
		EntityManager em = EntityManagerUtil.getEntityManager();
		
		TypedQuery<TbTag> query = em.createNamedQuery(TbTag.POR_ID_CLIENTE_TODOS, TbTag.class);
		
		query.setParameter("idCliente", idCliente);
		
		List<TbTag> tbTags = query.getResultList();
		
		List<Tag> tags = new ArrayList<Tag>();
		
		for (TbTag tb : tbTags) {
			tags.add(new Tag(tb));
		}

		return tags;
	}

	
	public Integer getCdTag() {
		return cdTag;
	}
	public void setCdTag(Integer cdTag) {
		this.cdTag = cdTag;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public boolean isAtivo() {
		return flAtivo==1;
	}

	public double getVlSaldo() {
		return vlSaldo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdTag == null) ? 0 : cdTag.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (cdTag == null) {
			if (other.cdTag != null)
				return false;
		} else if (!cdTag.equals(other.cdTag))
			return false;
		return true;
	}

	public boolean isNovo() {
		return this.veiculo.isNovo();
	}

	public void setIdCliente(Integer id) {
		this.veiculo.setIdCliente(id);
		
	}

	public boolean isPreenchido() {
		return this.cdTag != null;
	}

	public void desativa() {
		this.flAtivo = 0;
	}
}
