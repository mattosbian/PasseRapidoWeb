package br.com.passerapido.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.passerapido.entity.TbTipoTransacao;
import br.com.passerapido.util.EntityManagerUtil;

public class TipoTransacao implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer cdTipoTransacao;
	private String nmTipoTransacao; 
	private Integer flCredito; 
	
	public TipoTransacao(TbTipoTransacao tbT) {
		this.cdTipoTransacao = tbT.getCdTipoTransacao();
		this.nmTipoTransacao = tbT.getNmTipoTransacao();
		this.flCredito		 = tbT.getFlCredito();
	}

	public TbTipoTransacao toEntity() {
		TbTipoTransacao tbT = new TbTipoTransacao();

		tbT.setCdTipoTransacao(this.cdTipoTransacao);
		tbT.setNmTipoTransacao(this.nmTipoTransacao);
		tbT.setFlCredito(this.flCredito);

		return tbT;
	}
	
	public TipoTransacao() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static List<TipoTransacao> buscaTodosNaoSistema() {
		EntityManager em = EntityManagerUtil.getEntityManager();
		TypedQuery<TbTipoTransacao> query = em.createNamedQuery(TbTipoTransacao.POR_FL_SISTEMA, TbTipoTransacao.class);
		query.setParameter("flSistema", TbTipoTransacao.FL_SISTEMA_NAO);
		List<TbTipoTransacao> tbList = query.getResultList();
		
		List<TipoTransacao> list = new ArrayList<TipoTransacao>();
		
		for(TbTipoTransacao t : tbList) {
			list.add(new TipoTransacao(t));
		}
		
		return list;
	}

	public static List<TipoTransacao> buscaTodos() {
		EntityManager em = EntityManagerUtil.getEntityManager();
		TypedQuery<TbTipoTransacao> query = em.createNamedQuery(TbTipoTransacao.TODOS, TbTipoTransacao.class);
		List<TbTipoTransacao> tbList = query.getResultList();
		
		List<TipoTransacao> list = new ArrayList<TipoTransacao>();
		
		for(TbTipoTransacao t : tbList) {
			list.add(new TipoTransacao(t));
		}
		
		return list;
	}

	
	
	public static TipoTransacao buscaPorId(Integer cdTipoTransacao) {
		EntityManager em = EntityManagerUtil.getEntityManager();

		TypedQuery<TbTipoTransacao> query = em.createNamedQuery(TbTipoTransacao.POR_ID, TbTipoTransacao.class);
		query.setParameter("id", cdTipoTransacao);

		try {
			
			TbTipoTransacao tbTipo = query.getSingleResult();
			return new TipoTransacao(tbTipo);
			
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
	public boolean isCredito() {
		return this.flCredito == 1;
	}

	

	
	public Integer getCdTipoTransacao() {
		return cdTipoTransacao;
	}

	public void setCdTipoTransacao(Integer cdTipoTransacao) {
		this.cdTipoTransacao = cdTipoTransacao;
	}

	public String getNmTipoTransacao() {
		return nmTipoTransacao;
	}

	public void setNmTipoTransacao(String nmTipoTransacao) {
		this.nmTipoTransacao = nmTipoTransacao;
	}

	public boolean isPreenchido() {
		return this.cdTipoTransacao != null;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdTipoTransacao == null) ? 0 : cdTipoTransacao.hashCode());
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
		TipoTransacao other = (TipoTransacao) obj;
		if (cdTipoTransacao == null) {
			if (other.cdTipoTransacao != null)
				return false;
		} else if (!cdTipoTransacao.equals(other.cdTipoTransacao))
			return false;
		return true;
	}
	
}
