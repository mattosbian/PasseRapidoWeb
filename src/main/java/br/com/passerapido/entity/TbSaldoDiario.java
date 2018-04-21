package br.com.passerapido.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries ({
	@NamedQuery(name=TbSaldoDiario.POR_TAG_DATA, query="select t from TbSaldoDiario t where t.pk.dtSaldo = :dtSaldo "
			+ "and t.pk.cdTag = :cdTag")
})
@Table(name="TB_SALDO_DIARIO")
public class TbSaldoDiario {

	public static final String POR_TAG_DATA = "br.com.passerapido.entity.TbSaldoDiario.POR_TAG_DATA";

	@EmbeddedId
	private PKTbSaldoDiario pk;

	@Column(name="VL_SALDO")
	private double vlSaldo; 

	public PKTbSaldoDiario getPk() {
		return pk;
	}

	public void setPk(PKTbSaldoDiario pk) {
		this.pk = pk;
	}

	public double getVlSaldo() {
		return vlSaldo;
	}

	public void setVlSaldo(double vlSaldo) {
		this.vlSaldo = vlSaldo;
	}
	
	
}
