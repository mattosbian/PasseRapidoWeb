package br.com.passerapido.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TB_TIPO_TRANSACAO")
@SequenceGenerator(name="TbTipoTransacaoCd",sequenceName="SQ_TB_TIPO_TRANSACAO_CD")
public class TbTipoTransacao {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="TbTipoTransacaoCd")
	@Column(name="CD_TIPO_TRANSACAO")
	private Integer cdTipoTransacao; //NUMBER

	@Column(name="NM_TIPO_TRANSACAO")
	private String nmTipoTransacao; //VARCHAR2(80 BYTE)
}
