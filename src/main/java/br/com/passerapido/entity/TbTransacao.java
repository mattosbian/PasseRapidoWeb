package br.com.passerapido.entity;

import java.util.Calendar;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries ({
	@NamedQuery(name=TbTransacao.POR_TAG_DATA, query="select t from TbTransacao t where t.cdTag = :cdTag "
			+ "and t.dtTransacao between :dtInicial and :dtFinal order by t.dtHoraTransacao")
	,@NamedQuery(name=TbTransacao.POR_TAG_DATA_TIPO, query="select t from TbTransacao t join t.tbTipoTransacao p "
			+ "where p.cdTipoTransacao = :cdTipoTransacao and t.cdTag = :cdTag "
			+ "and t.dtTransacao between :dtInicial and :dtFinal order by t.dtHoraTransacao")
})
@Table(name="TB_TRANSACAO")
@SequenceGenerator(name="tbTransacaoId",sequenceName="SQ_TB_TRANSACAO_ID",allocationSize=1,initialValue=1)
public class TbTransacao {

	public static final String POR_TAG_DATA = "br.com.passerapido.entity.TbTransacao.POR_TAG_DATA";
	public static final String POR_TAG_DATA_TIPO = "br.com.passerapido.entity.TbTransacao.POR_TAG_DATA_TIPO";
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="tbTransacaoId")
	@Column(name="ID_TRANSACAO")
	private Integer idTransacao;
	
	@OneToOne
	@JoinColumn(name="CD_TIPO_TRANSACAO")
	private TbTipoTransacao tbTipoTransacao;

	@Column(name="CD_TAG")
	private Integer cdTag;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_TRANSACAO")
	private Calendar dtTransacao; 

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_HORA_TRANSACAO")
	private Calendar dtHoraTransacao;

	@Column(name="VL_TRANSACAO")
	private double vlTransacao; 

	public void setIdTransacao(Integer idTransacao) {
		this.idTransacao = idTransacao;
	}
	
	public Integer getIdTransacao() {
		return idTransacao;
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

	public double getVlTransacao() {
		return vlTransacao;
	}

	public void setVlTransacao(double vlTransacao) {
		this.vlTransacao = vlTransacao;
	}

	public TbTipoTransacao getTipoTransacao() {
		return tbTipoTransacao;
	}

	public void setTipoTransacao(TbTipoTransacao tipoTransacao) {
		this.tbTipoTransacao = tipoTransacao;
	}
	
}
