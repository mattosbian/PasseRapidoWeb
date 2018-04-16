package br.com.passerapido.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="TB_ESTADO")
@NamedQueries({
	@NamedQuery(name=TbEstado.TODOS, query="Select t from TbEstado t")
})
public class TbEstado {

	public static final String TODOS = "br.com.passerapido.entity.TbEstado.TODOS"; 
	
	@Id
	@Column(name="SG_ESTADO")
	private String sgEstado; //VARCHAR2(2 BYTE)

	@Column(name="NM_ESTADO")
	private String nmEstado; //VARCHAR2(30 BYTE)

	public String getSgEstado() {
		return sgEstado;
	}

	public void setSgEstado(String sgEstado) {
		this.sgEstado = sgEstado;
	}

	public String getNmEstado() {
		return nmEstado;
	}

	public void setNmEstado(String nmEstado) {
		this.nmEstado = nmEstado;
	}

	
}
