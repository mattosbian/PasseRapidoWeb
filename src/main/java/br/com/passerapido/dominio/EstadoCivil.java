package br.com.passerapido.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.passerapido.entity.TbEstadoCivil;
import br.com.passerapido.util.EntityManagerUtil;

public class EstadoCivil implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer cdEstadoCivil;
	private String dsEstadoCivil;

	public EstadoCivil(TbEstadoCivil ent) {
		this.cdEstadoCivil = ent.getCdEstadoCivil();
		this.dsEstadoCivil = ent.getDsEstadoCivil();
	}
	
	public EstadoCivil() {
		// TODO Auto-generated constructor stub
	}

	public static EstadoCivil buscaPorId(int i) {
		EntityManager em = EntityManagerUtil.getEntityManager();
		TbEstadoCivil tbEstadoCivil = em.find(TbEstadoCivil.class, i);
		return new EstadoCivil(tbEstadoCivil);
	}

	public static List<EstadoCivil> buscaTodos() {
		EntityManager em = EntityManagerUtil.getEntityManager();
		TypedQuery<TbEstadoCivil> query = em.createNamedQuery(TbEstadoCivil.TODOS, TbEstadoCivil.class);
		List<TbEstadoCivil> tbList = query.getResultList();
		
		List<EstadoCivil> list = new ArrayList<EstadoCivil>();
		
		for(TbEstadoCivil t : tbList) {
			list.add(new EstadoCivil(t));
		}
		
		return list;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdEstadoCivil == null) ? 0 : cdEstadoCivil.hashCode());
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
		EstadoCivil other = (EstadoCivil) obj;
		if (cdEstadoCivil == null) {
			if (other.cdEstadoCivil != null)
				return false;
		} else if (!cdEstadoCivil.equals(other.cdEstadoCivil))
			return false;
		return true;
	}

	public boolean isPreenchido() {
		return this.cdEstadoCivil != null;
	}
	
	public Integer getcdEstadoCivil() {
		return this.cdEstadoCivil;
	}
	public void setcdEstadoCivil(Integer codigo) {
		this.cdEstadoCivil = codigo;
	}
	public String getdsEstadoCivil() {
		return dsEstadoCivil;
	}
	public void setdsEstadoCivil(String nome) {
		this.dsEstadoCivil = nome;
	}

	
}
