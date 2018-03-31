package br.com.passerapido.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="TB_TRANSACAO")
@SequenceGenerator(name="tbTransacaoId",sequenceName="SQ_TB_TRANSACAO_ID",allocationSize=1,initialValue=1)
public class TbTransacao {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="tbTransacaoId")
	@Column(name="ID_TRANSACAO")
	private Integer idTransacao;
	
	@Column(name="CD_TIPO_TRANSACAO")
	private Integer cdTipoTransacao; //NUMBER

	@Column(name="CD_TAG")
	private Integer cdTag; //NUMBER

	@Temporal(TemporalType.DATE)
	@Column(name="DT_TRANSACAO")
	private Calendar dtTransacao; //DATE

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_HORA_TRANSACAO")
	private Calendar dtHoraTransacao; //DATE

	@Column(name="VL_TRANSACAO")
	private Integer vlTransacao; //NUMBER(8,2)

	public Integer getCdTipoTransacao() {
		return cdTipoTransacao;
	}

	public void setIdTransacao(Integer idTransacao) {
		this.idTransacao = idTransacao;
	}
	
	public Integer getIdTransacao() {
		return idTransacao;
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
