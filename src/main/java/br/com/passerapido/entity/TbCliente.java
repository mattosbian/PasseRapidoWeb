package br.com.passerapido.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="TB_CLIENTE")
@SequenceGenerator(name="tbClienteId", sequenceName="SQ_TB_CLIENTE_ID",initialValue=1,allocationSize=1)
@NamedQueries( {
	@NamedQuery(name=TbCliente.POR_CPF_SENHA, query="Select t From TbCliente t where t.cdsCPF = :cpf and t.txSenha=:senha")
	,@NamedQuery(name=TbCliente.POR_CPF, query="Select t From TbCliente t where t.cdsCPF = :cpf")
	,@NamedQuery(name=TbCliente.COUNT_POR_CPF, query="Select count(t) From TbCliente t where t.cdsCPF = :cpf")
})
public class TbCliente {

	public static final String POR_CPF_SENHA="br.com.passerapido.entity.TbCliente.POR_CPF_SENHA";
	public static final String POR_CPF="br.com.passerapido.entity.TbCliente.POR_CPF";
	public static final String COUNT_POR_CPF="br.com.passerapido.entity.TbCliente.COUNT_POR_CPF";

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="tbClienteId")
	@Column(name="id_cliente")
	private Integer idCliente;
	
	@Column(name="ds_primeiro_nome")
	private String dsPrimeiroNome;
	
	@Column(name="ds_sobrenome")
	private String dsSobrenome;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dt_nascimento")
	private Calendar dtNascimento;
	
	@Column(name="nm_completo_mae")
	private String nmCompletoMae;
	
	@Column(name="nr_ddd_celular")
	private Integer nrDddCelular;
	
	@Column(name="nr_celular")
	private Integer nrCelular; 
	
	@Column(name="nr_ddd_telefone")
	private Integer nrDddTelefone; 
	
	@Column(name="nr_telefone")
	private Integer nrTelefone;	
	
	@Column(name="cd_genero")
	private Integer cdGenero; 
	
	@Column(name="cd_estado_civil")
	private Integer cdEstadoCivil; 
	
	@Column(name="tx_senha")
	private String txSenha;	

	@Column(name="cds_cpf")
	private String cdsCPF;	
	
	@Column(name="cds_rg")
	private String cdsRG;	
	
	@Column(name="ds_email")
	private String dsEmail;
	
	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getDsPrimeiroNome() {
		return dsPrimeiroNome;
	}

	public void setDsPrimeiroNome(String dsPrimeiroNome) {
		this.dsPrimeiroNome = dsPrimeiroNome;
	}

	public String getDsSobrenome() {
		return dsSobrenome;
	}

	public void setDsSobrenome(String dsSobrenome) {
		this.dsSobrenome = dsSobrenome;
	}

	public Calendar getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Calendar dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getNmCompletoMae() {
		return nmCompletoMae;
	}

	public void setNmCompletoMae(String nmCompletoMae) {
		this.nmCompletoMae = nmCompletoMae;
	}

	public Integer getNrDddCelular() {
		return nrDddCelular;
	}

	public void setNrDddCelular(Integer nrDddCelular) {
		this.nrDddCelular = nrDddCelular;
	}

	public Integer getNrCelular() {
		return nrCelular;
	}

	public void setNrCelular(Integer nrCelular) {
		this.nrCelular = nrCelular;
	}

	public Integer getNrDddTelefone() {
		return nrDddTelefone;
	}

	public void setNrDddTelefone(Integer nrDddTelefone) {
		this.nrDddTelefone = nrDddTelefone;
	}

	public Integer getNrTelefone() {
		return nrTelefone;
	}

	public void setNrTelefone(Integer nrTelefone) {
		this.nrTelefone = nrTelefone;
	}

	public Integer getCdGenero() {
		return cdGenero;
	}

	public void setCdGenero(Integer cdGenero) {
		this.cdGenero = cdGenero;
	}

	public Integer getCdEstadoCivil() {
		return cdEstadoCivil;
	}

	public void setCdEstadoCivil(Integer cdEstadoCivil) {
		this.cdEstadoCivil = cdEstadoCivil;
	}

	public String getTxSenha() {
		return txSenha;
	}

	public void setTxSenha(String txSenha) {
		this.txSenha = txSenha;
	}

	public String getCdsCPF() {
		return cdsCPF;
	}

	public void setCdsCPF(String cdsCPF) {
		this.cdsCPF = cdsCPF;
	}

	public String getCdsRG() {
		return cdsRG;
	}

	public void setCdsRG(String cdsRG) {
		this.cdsRG = cdsRG;
	}

	public String getDsEmail() {
		return dsEmail;
	}

	public void setDsEmail(String dsEmail) {
		this.dsEmail = dsEmail;
	}
	

}
