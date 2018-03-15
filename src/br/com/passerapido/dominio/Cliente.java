package br.com.passerapido.dominio;

import br.com.passerapido.entity.TbCliente;
import br.com.passerapido.exception.DominioException;

public class Cliente {

	private Integer id;
	
	private String nome;
	
	private Endereco endereco;
	
	private ContaBancaria contaBancaria;
	
	
	public TbCliente toEntity() {
		TbCliente tbCliente = new TbCliente();
		tbCliente.setNome(this.nome);
		return tbCliente;
	}
	
	
	public void validate() throws DominioException{
		
	}


	public Endereco getEndereco() {

		return endereco;
	}
	
	
	public boolean isNovo() {
		return this.id==null;
	}
}
