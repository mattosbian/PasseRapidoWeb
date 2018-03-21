package br.com.passerapido.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_CONTA_BANCARIA")
public class TbContaBancaria {

	@Id
	@Column(name="ID_CLIENTE")
	private Integer idCliente;	//NUMBER

	@Column(name="NM_BANCO")
	private String nmBanco;	//VARCHAR2(50 BYTE)

	@Column(name="NR_AGENCIA")
	private String nrAgencia;	//VARCHAR2(10 BYTE)

	@Column(name="NR_CONTA")
	private String nrConta;	//VARCHAR2(20 BYTE)

	@Column(name="NR_DIGITO")
	private Integer nrDigito;	//NUMBER(2,0)

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNmBanco() {
		return nmBanco;
	}

	public void setNmBanco(String nmBanco) {
		this.nmBanco = nmBanco;
	}

	public String getNrAgencia() {
		return nrAgencia;
	}

	public void setNrAgencia(String nrAgencia) {
		this.nrAgencia = nrAgencia;
	}

	public String getNrConta() {
		return nrConta;
	}

	public void setNrConta(String nrConta) {
		this.nrConta = nrConta;
	}

	public Integer getNrDigito() {
		return nrDigito;
	}

	public void setNrDigito(Integer nrDigito) {
		this.nrDigito = nrDigito;
	}

	
}
