package br.com.passerapido.dominio;

import br.com.passerapido.entity.TbEstado;

public class Estado {
	private String sgEstado; 
	private String nmEstado;
	
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
