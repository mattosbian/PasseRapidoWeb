package br.com.passerapido.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries ({
		@NamedQuery(name=TbEndereco.POR_ID_CLIENTE, query="select e from TbEndereco e where e.idCliente = :idCliente")
})
@Table(name="TB_ENDERECO")
public class TbEndereco {

	public static final String POR_ID_CLIENTE = "br.com.passerapido.entity.TbEndereco.POR_ID_CLIENTE";

	@Id
	@Column(name="ID_CLIENTE")
	private Integer idCliente;	
	
	@Column(name="SG_ESTADO")
	private String sgEstado;
	
	@Column(name="NR_CEP")
	private String nrCep;
	
	@Column(name="NM_CIDADE")
	private String nmCidade;
	
	@Column(name="NM_BAIRRO")
	private String nmBairro;	
	
	@Column(name="DS_ENDERECO")
	private String dsEndereco;	
	
	@Column(name="NR_ENDERECO")
	private Integer nrEndereco;	
	
	@Column(name="DS_COMPLEMENTO")
	private String dsComplemento;	

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
