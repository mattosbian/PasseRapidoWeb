package br.com.passerapido.dominio;

import br.com.passerapido.dominio.ContaBancaria;
import br.com.passerapido.dominio.Endereco;
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


	public void validate() throws DominioException {

	}


	public Endereco getEndereco() {

		return endereco;
	}


	public boolean isNovo() {
		return this.id==null;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public ContaBancaria getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(ContaBancaria contaBancaria) {
		this.contaBancaria = contaBancaria;
	}
}
