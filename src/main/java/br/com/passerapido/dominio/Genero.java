package br.com.passerapido.dominio;

import br.com.passerapido.entity.TbGenero;

public class Genero {
	private Integer codigo;
	private String nome;
	
	public Genero(TbGenero tbGenero) {
		this.codigo = tbGenero.getCdGenero();
		this.nome = tbGenero.getDsGenero();
	}
	
	public Integer getCdGenero() {
		return codigo;
	}
	public void setCdGenero(Integer cdGenero) {
		this.codigo = cdGenero;
	}
	public String getDsGenero() {
		return nome;
	}
	public void setDsGenero(String dsGenero) {
		this.nome = dsGenero;
	}

}
