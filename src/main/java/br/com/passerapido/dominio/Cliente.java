package br.com.passerapido.dominio;

import java.util.Calendar;

import br.com.passerapido.entity.TbCliente;
import br.com.passerapido.exception.DominioException;

public class Cliente {

	private Integer id;
	
	private String nome;
	
	private String sobrenome;
	
	private Calendar dataNascimento;
	
	private String nomeMae;
	
	private Integer dddCelular;	//NUMBER(2,0)
	
	private Integer numeroCelular; //NUMBER(9,0)
	
	private Integer dddTelefone; //	NUMBER(2,0)
	
	private Integer numeroTelefone;	//NUMBER(8,0)

	private String cpf;	//NUMBER(8,0)

	private String rg;	//NUMBER(8,0)

	private Genero genero; //NUMBER(1,0)
	
	private EstadoCivil estadoCivil; //	NUMBER(1,0)
	
	private String senha;	//VARCHAR2(50 BYTE)

	//private Endereco endereco;
	
	//private ContaBancaria contaBancaria; //ja estava
	
	public TbCliente toEntity() {
		TbCliente tbCliente = new TbCliente();
		//tbCliente.setNome(this.nome);

		tbCliente.setCdEstadoCivil(this.estadoCivil.getCodigo());
		tbCliente.setCdGenero(this.genero.getCdGenero());
		tbCliente.setCdsCPF(this.cpf);
		tbCliente.setCdsRG(this.rg);
		tbCliente.setDsPrimeiroNome(this.nome);
		tbCliente.setDsSobrenome(this.sobrenome);
		tbCliente.setDtNascimento(this.dataNascimento);
		tbCliente.setNmCompletoMae(this.nomeMae);
		tbCliente.setNrDddCelular(this.dddCelular);
		tbCliente.setNrCelular(this.numeroCelular);
		tbCliente.setNrDddTelefone(this.dddTelefone);
		tbCliente.setNrTelefone(this.numeroTelefone);
		tbCliente.setTxSenha(this.senha);
		
		return tbCliente;
	}
	
	
	public void validate() throws DominioException{
		
	}


//	public Endereco getEndereco() {
//		return endereco;
//	}
	
	
	public boolean isNovo() {
		return this.id==null;
	}
}
