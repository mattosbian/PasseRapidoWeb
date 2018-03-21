package br.com.passerapido.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.passerapido.entity.TbSaldoDiario.PK;

@Entity
@IdClass(PK.class)
@Table(name="TB_SALDO_DIARIO")
public class TbSaldoDiario {

	@Id
	@Temporal(TemporalType.DATE)
	@Column(name="DT_SALDO")
	private Calendar dtSaldo; //DATE

	@Id
	@Column(name="CD_TAG")
	private Integer cdTag; //NUMBER

	@Column(name="VL_SALDO")
	private Integer vlSaldo; //NUMBER(8,2)
	
	public static class PK implements Serializable {
		private static final long serialVersionUID = 1L;
		private Calendar dtSaldo; //DATE
		private Integer cdTag; //NUMBER
		
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
			PK other = (PK) obj;
			return (this.cdTag.equals(other.getCdTag()) && 
					this.dtSaldo.equals(other.getDtSaldo()));
		}
		
		@Override
		public int hashCode() {
			return this.dtSaldo.hashCode();
		}
		
	}

}
