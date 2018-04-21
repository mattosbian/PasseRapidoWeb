package br.com.passerapido.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.passerapido.entity.TbEstado;
import br.com.passerapido.util.EntityManagerUtil;

public class Estado {
	private String sgEstado; 
	private String nmEstado;

	public static List<String> buscaTodosSigla() {
		EntityManager em = EntityManagerUtil.getEntityManager();
		TypedQuery<TbEstado> query = em.createNamedQuery(TbEstado.TODOS, TbEstado.class);
		List<TbEstado> tbList = query.getResultList();
		
		List<String> listaEstados = new ArrayList<String>();

		for (TbEstado tbEstado : tbList) {
			listaEstados.add(tbEstado.getSgEstado());
		}
		
		return listaEstados;
	}
	public Estado(TbEstado tbEstado) {
		this.sgEstado = tbEstado.getSgEstado();
		this.nmEstado = tbEstado.getNmEstado();
	}
	
	public String getSgEstado() {
		return sgEstado;
	}
	public void setSgEstado(String sgEstado) {
		this.sgEstado = sgEstado;
	}
	public String getNmEstado() {
		return nmEstado;
	}
	public void setNmEstado(String nmEstado) {
		this.nmEstado = nmEstado;
	} 
	
	
}
