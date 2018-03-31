package br.com.passerapido.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TB_VEICULO")
@SequenceGenerator(name="TbVeiculoCd", sequenceName="SQ_TB_VEICULO_CD",initialValue=1,allocationSize=1)
public class TbVeiculo {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="TbVeiculoCd")
	@Column(name="CD_VEICULO")
	private Integer cdVeiculo; //NUMBER(4,0)

	@Column(name="ID_CLIENTE")
	private Integer idCliente; //NUMBER

	@Column(name="NM_MARCA")
	private String nmMarca; //VARCHAR2(50 BYTE)

	@Column(name="NM_MODELO")
	private String nmModelo; //VARCHAR2(50 BYTE)

	@Column(name="NR_PLACA")
	private String nrPlaca; //VARCHAR2(8 BYTE)

	public Integer getCdVeiculo() {
		return cdVeiculo;
	}

	public void setCdVeiculo(Integer cdVeiculo) {
		this.cdVeiculo = cdVeiculo;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNmMarca() {
		return nmMarca;
	}

	public void setNmMarca(String nmMarca) {
		this.nmMarca = nmMarca;
	}

	public String getNmModelo() {
		return nmModelo;
	}

	public void setNmModelo(String nmModelo) {
		this.nmModelo = nmModelo;
	}

	public String getNrPlaca() {
		return nrPlaca;
	}

	public void setNrPlaca(String nrPlaca) {
		this.nrPlaca = nrPlaca;
	}
	
	
	
}
