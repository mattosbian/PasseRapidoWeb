package br.com.passerapido.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_ENDERECO")
public class TbEndereco {

	@Id
	@Column(name="ID_CLIENTE")
	private Integer idCliente;	//NUMBER
	
	@Column(name="SG_ESTADO")
	private String sgEstado;	//VARCHAR2(2 BYTE)
	
	@Column(name="NR_CEP")
	private String nrCep;	//VARCHAR2(10 BYTE)
	
	@Column(name="NM_CIDADE")
	private String nmCidade;	//VARCHAR2(100 BYTE)
	
	@Column(name="NM_BAIRRO")
	private String nmBairro;	//VARCHAR2(100 BYTE)
	
	@Column(name="DS_ENDERECO")
	private String dsEndereco;	//VARCHAR2(100 BYTE)
	
	@Column(name="NR_ENDERECO")
	private Integer nrEndereco;	//NUMBER(5,0)
	
	@Column(name="DS_COMPLEMENTO")
	private String dsComplemento;	//VARCHAR2(100 BYTE)

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getSgEstado() {
		return sgEstado;
	}

	public void setSgEstado(String sgEstado) {
		this.sgEstado = sgEstado;
	}

	public String getNrCep() {
		return nrCep;
	}

	public void setNrCep(String nrCep) {
		this.nrCep = nrCep;
	}

	public String getNmCidade() {
		return nmCidade;
	}

	public void setNmCidade(String nmCidade) {
		this.nmCidade = nmCidade;
	}

	public String getNmBairro() {
		return nmBairro;
	}

	public void setNmBairro(String nmBairro) {
		this.nmBairro = nmBairro;
	}

	public String getDsEndereco() {
		return dsEndereco;
	}

	public void setDsEndereco(String dsEndereco) {
		this.dsEndereco = dsEndereco;
	}

	public Integer getNrEndereco() {
		return nrEndereco;
	}

	public void setNrEndereco(Integer nrEndereco) {
		this.nrEndereco = nrEndereco;
	}

	public String getDsComplemento() {
		return dsComplemento;
	}

	public void setDsComplemento(String dsComplemento) {
		this.dsComplemento = dsComplemento;
	}
	
	
}
