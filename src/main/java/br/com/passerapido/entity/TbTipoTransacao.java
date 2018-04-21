package br.com.passerapido.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries( {
	@NamedQuery(name=TbTipoTransacao.POR_FL_SISTEMA, query="Select t From TbTipoTransacao t where t.flSistema = :flSistema")
	,@NamedQuery(name=TbTipoTransacao.POR_ID, query="Select t From TbTipoTransacao t where t.cdTipoTransacao = :id")
	,@NamedQuery(name=TbTipoTransacao.TODOS, query="Select t From TbTipoTransacao t")
})
@Table(name="TB_TIPO_TRANSACAO")
public class TbTipoTransacao {

	public static final String TODOS ="br.com.passerapido.entity.TbTipoTransacao.TODOS";
	public static final String POR_FL_SISTEMA ="br.com.passerapido.entity.TbTipoTransacao.TODOS_NAO_SISTEMA";
	public static final String POR_ID ="br.com.passerapido.entity.TbTipoTransacao.POR_ID";
	
	public static final Integer FL_SISTEMA_NAO = 0;
	public static final Integer CD_TIPO_TRANSFERENCIA = 4;
	public static final Integer CD_TIPO_CREDITO = 5;

	@Id
	@Column(name="CD_TIPO_TRANSACAO")
	private Integer cdTipoTransacao; 

	@Column(name="NM_TIPO_TRANSACAO")
	private String nmTipoTransacao;

	@Column(name="FL_CREDITO")
	private Integer flCredito; 

	@Column(name="FL_SISTEMA")
	private Integer flSistema; 
	
	public Integer getCdTipoTransacao() {
		return cdTipoTransacao;
	}

	public void setCdTipoTransacao(Integer cdTipoTransacao) {
		this.cdTipoTransacao = cdTipoTransacao;
	}

	public String getNmTipoTransacao() {
		return nmTipoTransacao;
	}

	public void setNmTipoTransacao(String nmTipoTransacao) {
		this.nmTipoTransacao = nmTipoTransacao;
	}

	public Integer getFlCredito() {
		return flCredito;
	}

	public void setFlCredito(Integer flCredito) {
		this.flCredito = flCredito;
	}

	public Integer getFlSistema() {
		return flSistema;
	}

	public void setFlSistema(Integer flSistema) {
		this.flSistema = flSistema;
	}
	
	
}
