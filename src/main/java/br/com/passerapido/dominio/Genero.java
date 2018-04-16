package br.com.passerapido.dominio;

import java.io.Serializable;

import javax.persistence.EntityManager;

import br.com.passerapido.entity.TbGenero;
import br.com.passerapido.util.EntityManagerUtil;

public class Genero implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer cdGenero;
	private String dsGenero;

	public static Genero buscaPorId(Integer id) {
		EntityManager em = EntityManagerUtil.getEntityManager();
		TbGenero tbGenero = em.find(TbGenero.class, id);
		return new Genero(tbGenero);
	}
	
	public Genero(TbGenero tbGenero) {
		this.cdGenero = tbGenero.getCdGenero();
		this.dsGenero = tbGenero.getDsGenero();
	}

	public Genero() {
		// TODO Auto-generated constructor stub
	}

	public Integer getCdGenero() {
		return cdGenero;
	}
	public void setCdGenero(Integer cdGenero) {
		this.cdGenero = cdGenero;
	}
	public String getDsGenero() {
		return dsGenero;
	}
	public void setDsGenero(String dsGenero) {
		this.dsGenero = dsGenero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdGenero == null) ? 0 : cdGenero.hashCode());
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
		Genero other = (Genero) obj;
		if (cdGenero == null) {
			if (other.cdGenero != null)
				return false;
		} else if (!cdGenero.equals(other.cdGenero))
			return false;
		return true;
	}

	public boolean isPreenchido() {
		return this.cdGenero != null;
	}



}
