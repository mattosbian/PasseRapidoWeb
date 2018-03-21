package br.com.passerapido.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_TRANSACAO")
public class TbTransacao {

	@Id
	@Column(name="CD_TIPO_TRANSACAO")
	private Integer cdTipoTransacao; //NUMBER

	@Column(name="CD_TAG")
	private Integer cdTag; //NUMBER

	@Column(name="DT_TRANSACAO")
	private Calendar dtTransacao; //DATE

	@Column(name="DT_HORA_TRANSACAO")
	private Calendar dtHoraTransacao; //DATE

	@Column(name="VL_TRANSACAO")
	private Integer vlTransacao; //NUMBER(8,2)

	public Integer getCdTipoTransacao() {
		return cdTipoTransacao;
	}

	public void setCdTipoTransacao(Integer cdTipoTransacao) {
		this.cdTipoTransacao = cdTipoTransacao;
	}

	public Integer getCdTag() {
		return cdTag;
	}

	public void setCdTag(Integer cdTag) {
		this.cdTag = cdTag;
	}

	public Calendar getDtTransacao() {
		return dtTransacao;
	}

	public void setDtTransacao(Calendar dtTransacao) {
		this.dtTransacao = dtTransacao;
	}

	public Calendar getDtHoraTransacao() {
		return dtHoraTransacao;
	}

	public void setDtHoraTransacao(Calendar dtHoraTransacao) {
		this.dtHoraTransacao = dtHoraTransacao;
	}

	public Integer getVlTransacao() {
		return vlTransacao;
	}

	public void setVlTransacao(Integer vlTransacao) {
		this.vlTransacao = vlTransacao;
	}

	
}
