package br.com.passerapido.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="TB_USUARIO")
@NamedQueries( {
		@NamedQuery(name=TbUsuario.POR_CPF_SENHA, query="Select t From TbUsuario t where t.nrCpf = :cpf and t.dsSenha=:senha")
})
public class TbUsuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String POR_CPF_SENHA="br.com.passerapido.entity.TbUsuario.POR_CPF_SENHA";
	
	@Id
	@Column(name="cd_usuario")
	private BigDecimal cdUsuario;
	
	@Column(name="nm_usuario")
	private String nmUsuario;
	
	@Column(name="nr_cpf")
	private BigDecimal nrCpf;
	
	@Column(name="ds_senha")
	private String dsSenha;

	public BigDecimal getCdUsuario() {
		return cdUsuario;
	}

	public void setCdUsuario(BigDecimal cdUsuario) {
		this.cdUsuario = cdUsuario;
	}

	
	
	public String getNmUsuario() {
		return nmUsuario;
	}

	public void setNmUsuario(String nmUsuario) {
		this.nmUsuario = nmUsuario;
	}

	public BigDecimal getNrCpf() {
		return nrCpf;
	}

	public void setNrCpf(BigDecimal nrCpf) {
		this.nrCpf = nrCpf;
	}

	public String getDsSenha() {
		return dsSenha;
	}

	public void setDsSenha(String dsSenha) {
		this.dsSenha = dsSenha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdUsuario == null) ? 0 : cdUsuario.hashCode());
		result = prime * result + ((nrCpf == null) ? 0 : nrCpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TbUsuario other = (TbUsuario) obj;
		if (cdUsuario == null) {
			if (other.cdUsuario != null)
				return false;
		} else if (!cdUsuario.equals(other.cdUsuario))
			return false;
		if (nrCpf == null) {
			if (other.nrCpf != null)
				return false;
		} else if (!nrCpf.equals(other.nrCpf))
			return false;
		return true;
	}
	
	

}
