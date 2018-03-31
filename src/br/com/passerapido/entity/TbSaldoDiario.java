package br.com.passerapido.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TB_SALDO_DIARIO")
public class TbSaldoDiario {

	@EmbeddedId
	private PKTbSaldoDiario pk;

	@Column(name="VL_SALDO")
	private Integer vlSaldo; //NUMBER(8,2)

	public PKTbSaldoDiario getPk() {
		return pk;
	}

	public void setPk(PKTbSaldoDiario pk) {
		this.pk = pk;
	}

	public Integer getVlSaldo() {
		return vlSaldo;
	}

	public void setVlSaldo(Integer vlSaldo) {
		this.vlSaldo = vlSaldo;
	}
	
	
}
