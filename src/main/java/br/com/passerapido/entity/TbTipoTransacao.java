package br.com.passerapido.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_TIPO_TRANSACAO")
public class TbTipoTransacao {

	@Id
	@Column(name="CD_TIPO_TRANSACAO")
	private Integer cdTipoTransacao; //NUMBER

	@Column(name="NM_TIPO_TRANSACAO")
	private String nmTipoTransacao; //VARCHAR2(80 BYTE)

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
	
	
}
