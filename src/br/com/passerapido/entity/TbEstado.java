package br.com.passerapido.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_ESTADO")
public class TbEstado {

	@Id
	@Column(name="SG_ESTADO")
	private String sgEstado; //VARCHAR2(2 BYTE)

	@Column(name="NM_ESTADO")
	private String nmEstado; //VARCHAR2(30 BYTE)


}
