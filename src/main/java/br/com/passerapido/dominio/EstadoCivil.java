package br.com.passerapido.dominio;

import br.com.passerapido.entity.TbEstadoCivil;

public class EstadoCivil {
	private Integer codigo;
	private String nome;

	public EstadoCivil(TbEstadoCivil ent) {
		this.codigo = ent.getCdEstadoCivil();
		this.nome = ent.getDsEstadoCivil();
	}
	
	public Integer getCodigo() {
		return this.codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
