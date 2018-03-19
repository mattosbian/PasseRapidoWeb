package br.com.passerapido.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="TB_ESTADO_CIVIL")
@NamedQueries( {
		@NamedQuery(name=TbEstadoCivil.TODOS, query="Select t From TbEstadoCivil t")
})
public class TbEstadoCivil implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final String TODOS ="br.com.passerapido.entity.TbEstadoCivil.TODOS";
	
	@Id
	@Column(name="cd_estado_civil")
	private Integer cdEstadoCivil; //NUMBER(1,0)
	
	@Column(name="ds_estado_civil")
	private String dsEstadoCivil; //VARCHAR2(30 BYTE)

	public Integer getCdEstadoCivil() {
		return cdEstadoCivil;
	}

	public void setCdEstadoCivil(Integer cdEstadoCivil) {
		this.cdEstadoCivil = cdEstadoCivil;
	}

	public String getDsEstadoCivil() {
		return dsEstadoCivil;
	}

	public void setDsEstadoCivil(String dsEstadoCivil) {
		this.dsEstadoCivil = dsEstadoCivil;
	}
	
	
	
}
