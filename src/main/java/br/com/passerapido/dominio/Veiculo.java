package br.com.passerapido.dominio;

import br.com.passerapido.entity.TbVeiculo;
import br.com.passerapido.exception.DominioException;

public class Veiculo {

	private Integer cdVeiculo;
	private Integer idCliente;
	private String nmMarca;
	private String nmModelo;
	private String nrPlaca;
	
	public Veiculo() {
		
	}
	
	public Veiculo(Veiculo other) {
		this.cdVeiculo = other.cdVeiculo;
		this.idCliente = other.idCliente;
		this.nmMarca = other.nmMarca;
		this.nmModelo = other.nmModelo;
		this.nrPlaca = other.nrPlaca;
	}
	
	public Veiculo(TbVeiculo tbV) {
		this.cdVeiculo = tbV.getCdVeiculo();
		this.idCliente = tbV.getIdCliente();
		this.nmMarca = tbV.getNmMarca();
		this.nmModelo = tbV.getNmModelo();
		this.nrPlaca = tbV.getNrPlaca();
	}

	public TbVeiculo toEntity() {
		TbVeiculo tbV = new TbVeiculo();
		
		tbV.setCdVeiculo(this.cdVeiculo);
		tbV.setIdCliente(this.idCliente);
		tbV.setNmMarca(this.nmMarca);
		tbV.setNmModelo(this.nmModelo);
		tbV.setNrPlaca(this.nrPlaca);
		
		return tbV;
	}
	
	public void validate() throws DominioException {
		
	}
	
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

	public boolean isNovo() {
		return this.idCliente == null;
	} 

	
}
