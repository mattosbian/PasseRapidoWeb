package br.com.passerapido.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TB_TAG")
@SequenceGenerator(name="TbTagCd",sequenceName="SQ_TB_TAG_CD")
public class TbTag {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="TbTagCd")
	@Column(name="CD_TAG")
	private Integer cdTag; //NUMBER

	@Column(name="CD_VEICULO")
	private Integer cdVeiculo; //NUMBER(4,0)

	@Column(name="VL_SALDO")
	private Integer vlSaldo; //NUMBER(8,2)

	@Column(name="FL_ATIVO")
	private Integer flAtivo; //NUMBER(1,0)
}
