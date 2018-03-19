package br.com.passerapido.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="TB_GENERO")
@NamedQueries( {
		@NamedQuery(name=TbGenero.TODOS, query="Select t From TbGenero t")
})
public class TbGenero implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final String TODOS ="br.com.passerapido.entity.TbGenero.TODOS";
	
	@Id
	@Column(name="cd_genero")
	private Integer cdGenero; //NUMBER(1,0)
	
	@Column(name="ds_genero")
	private String dsGenero; //VARCHAR2(30 BYTE)

	public Integer getCdGenero() {
		return cdGenero;
	}

	public void setCdGenero(Integer cdGenero) {
		this.cdGenero = cdGenero;
	}

	public String getDsGenero() {
		return dsGenero;
	}

	public void setDsGenero(String dsGenero) {
		this.dsGenero = dsGenero;
	}

	
}
