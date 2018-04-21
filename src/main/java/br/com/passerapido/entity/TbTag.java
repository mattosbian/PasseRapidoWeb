package br.com.passerapido.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TB_TAG")
@NamedQueries( {
		@NamedQuery(name=TbTag.POR_ID_CLIENTE_ATIVOS, query="Select t from TbTag t inner join t.tbVeiculo v "
				+ "where v.idCliente = :idCliente "
				+ "and t.flAtivo = 1")
		,@NamedQuery(name=TbTag.POR_ID_CLIENTE_TODOS, query="Select t from TbTag t inner join t.tbVeiculo v "
				+ "where v.idCliente = :idCliente")
		} )
@SequenceGenerator(name="TbTagCd",sequenceName="SQ_TB_TAG_CD",initialValue=1,allocationSize=1)
public class TbTag {

	public static final String POR_ID_CLIENTE_ATIVOS = "br.com.passerapido.entity.TbTag.POR_ID_CLIENTE_ATIVOS";
	public static final String POR_ID_CLIENTE_TODOS = "br.com.passerapido.entity.TbTag.POR_ID_CLIENTE_TODOS";
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="TbTagCd")
	@Column(name="CD_TAG")
	private Integer cdTag; 

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="CD_VEICULO")
	private TbVeiculo tbVeiculo;
	
	@Column(name="VL_SALDO")
	private double vlSaldo; 

	@Column(name="FL_ATIVO")
	private Integer flAtivo; 

	public Integer getCdTag() {
		return cdTag;
	}

	public void setCdTag(Integer cdTag) {
		this.cdTag = cdTag;
	}


	public double getVlSaldo() {
		return vlSaldo;
	}

	public void setVlSaldo(double vlSaldo) {
		this.vlSaldo = vlSaldo;
	}

	public Integer getFlAtivo() {
		return flAtivo;
	}

	public void setFlAtivo(Integer flAtivo) {
		this.flAtivo = flAtivo;
	}

	public TbVeiculo getTbVeiculo() {
		return tbVeiculo;
	}

	public void setTbVeiculo(TbVeiculo tbVeiculo) {
		this.tbVeiculo = tbVeiculo;
	}
	
	public void setIdCliente(Integer idCliente) {
		this.tbVeiculo.setIdCliente(idCliente);
	}
	
}
