package br.com.passerapido.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class PKTbSaldoDiario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_SALDO")
	private Calendar dtSaldo;

	@Column(name="CD_TAG")
	private Integer cdTag;

	public PKTbSaldoDiario() {
	}

	public Calendar getDtSaldo() {
		return dtSaldo;
	}

	public void setDtSaldo(Calendar dtSaldo) {
		this.dtSaldo = dtSaldo;
	}

	public Integer getCdTag() {
		return cdTag;
	}

	public void setCdTag(Integer cdTag) {
		this.cdTag = cdTag;
	}
	
	@Override
	public boolean equals(Object obj) {
		PKTbSaldoDiario other = (PKTbSaldoDiario) obj;
		return (this.getCdTag().equals(other.getCdTag()) && 
				this.getDtSaldo().equals(other.getDtSaldo()));
	}
	
	@Override
	public int hashCode() {
		return this.getDtSaldo().hashCode();
	}
}