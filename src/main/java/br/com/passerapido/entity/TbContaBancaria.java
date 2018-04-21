package br.com.passerapido.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries ({
	@NamedQuery(name=TbContaBancaria.POR_ID_CLIENTE, query="select c from TbContaBancaria c where c.idCliente=:idCliente")
})
@Table(name="TB_CONTA_BANCARIA")
public class TbContaBancaria {

	public static final String POR_ID_CLIENTE = "br.com.passerapido.entity.TbContaBancaria.POR_ID_CLIENTE";

	@Id
	@Column(name="ID_CLIENTE")
	private Integer idCliente;	

	@Column(name="NM_BANCO")
	private String nmBanco;	

	@Column(name="NR_AGENCIA")
	private String nrAgencia;	

	@Column(name="NR_CONTA")
	private String nrConta;	

	@Column(name="NR_DIGITO")
	private Integer nrDigito;	

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
