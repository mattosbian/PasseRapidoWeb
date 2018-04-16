package br.com.passerapido.dominio;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.passerapido.entity.TbCliente;
import br.com.passerapido.exception.DominioException;
import br.com.passerapido.util.EntityManagerUtil;

public class Cliente {

	private Integer idCliente;
	
	private String dsPrimeiroNome;
	
	private String dsSobreNome;
	
	private Calendar dtNascimento;
	
	private String nmCompletoMae;
	
	private Integer nrDDDCelular;	//NUMBER(2,0)
	
	private Integer nrCelular; //NUMBER(9,0)
	
	private Integer nrDDDTelefone; //	NUMBER(2,0)
	
	private Integer nrTelefone;	//NUMBER(8,0)

	private String cdsCPF;	//NUMBER(8,0)

	private String cdsRG;	//NUMBER(8,0)

	private Genero genero; //NUMBER(1,0)
	
	private EstadoCivil estadoCivil; //	NUMBER(1,0)
	
	private String txSenha1;
	
	private String txSenha2;
	
	private String dsEmail1;
	
	private String dsEmail2;

	//private ContaBancaria contaBancaria; //ja estava
	
	public Cliente() {
		this.dtNascimento = Calendar.getInstance();
		this.dtNascimento.clear();
	}

	
	public Cliente(TbCliente tbCliente) {
		this.idCliente			= tbCliente.getIdCliente();
		this.estadoCivil		= EstadoCivil.buscaPorId(tbCliente.getCdEstadoCivil());
		this.genero				= Genero.buscaPorId(tbCliente.getCdGenero());
		this.cdsCPF				= tbCliente.getCdsCPF();
		this.cdsRG				= tbCliente.getCdsRG();
		this.dsPrimeiroNome		= tbCliente.getDsPrimeiroNome();
		this.dsSobreNome		= tbCliente.getDsSobrenome();
		this.dtNascimento		= tbCliente.getDtNascimento();
		this.nmCompletoMae		= tbCliente.getNmCompletoMae();
		this.nrDDDCelular		= tbCliente.getNrDddCelular();
		this.nrCelular			= tbCliente.getNrCelular();
		this.nrDDDTelefone		= tbCliente.getNrDddTelefone();
		this.nrTelefone			= tbCliente.getNrTelefone();
		this.txSenha1			= tbCliente.getTxSenha();
		this.txSenha2			= tbCliente.getTxSenha();
		this.dsEmail1			= tbCliente.getDsEmail();
		this.dsEmail2			= tbCliente.getDsEmail();
	}

	
	
	public TbCliente toEntity() {
		TbCliente tbCliente = new TbCliente();

		tbCliente.setIdCliente(this.idCliente);
		tbCliente.setCdEstadoCivil(this.estadoCivil.getcdEstadoCivil());
		tbCliente.setCdGenero(this.genero.getCdGenero());
		tbCliente.setCdsCPF(this.cdsCPF);
		tbCliente.setCdsRG(this.cdsRG);
		tbCliente.setDsPrimeiroNome(this.dsPrimeiroNome);
		tbCliente.setDsSobrenome(this.dsSobreNome);
		tbCliente.setDtNascimento(this.dtNascimento);
		tbCliente.setNmCompletoMae(this.nmCompletoMae);
		tbCliente.setNrDddCelular(this.nrDDDCelular);
		tbCliente.setNrCelular(this.nrCelular);
		tbCliente.setNrDddTelefone(this.nrDDDTelefone);
		tbCliente.setNrTelefone(this.nrTelefone);
		tbCliente.setTxSenha(this.txSenha1);
		tbCliente.setDsEmail(this.getDsEmail1());
		
		return tbCliente;
	}

	
	public static Cliente buscaPorCPF(String cpf) {
		
		EntityManager em = EntityManagerUtil.getEntityManager();
		
		TypedQuery<TbCliente> query = em.createNamedQuery(TbCliente.POR_CPF, TbCliente.class);
		query.setParameter("cpf", cpf);
		
		TbCliente tbCliente = query.getSingleResult();
		
		return new Cliente(tbCliente);
	}

	
	public void validate() throws DominioException{

		if (this.cdsCPF == null || this.cdsCPF.isEmpty()) {
			throw new DominioException("CPF deve ser preenchido");
		}

		if (this.cdsRG == null || this.cdsRG.isEmpty()) {
			throw new DominioException("RG deve ser preenchido");
		}

		if (this.dsPrimeiroNome == null || this.dsPrimeiroNome.isEmpty()) {
			throw new DominioException("Primeiro Nome deve ser preenchido");
		}

		if (this.dsSobreNome == null || this.dsSobreNome.isEmpty()) {
			throw new DominioException("Sobrenome deve ser preenchido");
		}

		if (this.dtNascimento == null) {
			throw new DominioException("Data de Nascimento deve ser preenchida");
		}

		if (this.genero == null || !this.genero.isPreenchido()) {
			throw new DominioException("Sexo deve ser preenchido");
		}
		
		if (this.estadoCivil == null || !this.estadoCivil.isPreenchido()) {
			throw new DominioException("Estado Civil deve ser preenchido");
		}
		
		if (this.nmCompletoMae == null || this.nmCompletoMae.isEmpty()) {
			throw new DominioException("Nome da Mãe deve ser preenchido");
		}

		if (this.getDsEmail1() != null && !this.getDsEmail1().equals(this.getDsEmail2())) {
			throw new DominioException("Email e Confirmação de email devem ser iguais");
		}
		
		if (this.nrDDDCelular == null || this.nrDDDCelular == 0) {
			throw new DominioException("DDD Celular ser preenchido");
		}

		if (this.nrCelular == null || this.nrCelular == 0) {
			throw new DominioException("Celular ser preenchido");
		}
		
		if (this.txSenha1 == null || this.txSenha1.isEmpty()) {
			throw new DominioException("Senha deve ser preenchida");
		}

		if (this.txSenha2 == null || this.txSenha2.isEmpty()) {
			throw new DominioException("Confirmação de Senha deve ser preenchida");
		}

		if (!this.txSenha1.equals(this.txSenha2)) {
			throw new DominioException("Senha e Confirmação de Senha devem ser iguais");
		}

}

	public boolean isNovo() {
		return this.idCliente==null;
	}


	public Integer getId() {
		return idCliente;
	}


	public void setId(Integer id) {
		this.idCliente = id;
	}


	public String getDsPrimeiroNome() {
		return dsPrimeiroNome;
	}


	public void setDsPrimeiroNome(String dsPrimeiroNome) {
		this.dsPrimeiroNome = dsPrimeiroNome;
	}


	public String getDsSobreNome() {
		return dsSobreNome;
	}


	public void setDsSobreNome(String dsSobreNome) {
		this.dsSobreNome = dsSobreNome;
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


	public Integer getNrDDDCelular() {
		return nrDDDCelular;
	}


	public void setNrDDDCelular(Integer nrDDDCelular) {
		this.nrDDDCelular = nrDDDCelular;
	}


	public Integer getNrCelular() {
		return nrCelular;
	}


	public void setNrCelular(Integer nrCelular) {
		this.nrCelular = nrCelular;
	}


	public Integer getNrDDDTelefone() {
		return nrDDDTelefone;
	}


	public void setNrDDDTelefone(Integer nrDDDTelefone) {
		this.nrDDDTelefone = nrDDDTelefone;
	}


	public Integer getNrTelefone() {
		return nrTelefone;
	}


	public void setNrTelefone(Integer nrTelefone) {
		this.nrTelefone = nrTelefone;
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


	public Genero getGenero() {
		return genero;
	}


	public void setGenero(Genero genero) {
		this.genero = genero;
	}


	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}


	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getTxSenha1() {
		return txSenha1;
	}

	public void setTxSenha1(String txSenha) {
		this.txSenha1 = txSenha;
	}

	public String getTxSenha2() {
		return txSenha2;
	}
	
	public void setTxSenha2(String txSenha2) {
		this.txSenha2 = txSenha2;
	}

	public String getDsEmail1() {
		return dsEmail1;
	}

	public void setDsEmail1(String dsEmail1) {
		this.dsEmail1 = dsEmail1;
	}

	public String getDsEmail2() {
		return dsEmail2;
	}

	public void setDsEmail2(String dsEmail2) {
		this.dsEmail2 = dsEmail2;
	}

	
	
}
