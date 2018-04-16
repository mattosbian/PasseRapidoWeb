package br.com.passerapido.dominio;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.passerapido.exception.DominioException;
import br.com.passerapido.test.BaseTest;

public class ClienteTest extends BaseTest{

	private Cliente cliente;

	@Before
	public void setUp() {
		criarEntityManager();

		cliente = new Cliente();
		cliente.setDsPrimeiroNome("Marco");
		cliente.setDsSobreNome("Pasqua");
		cliente.setDtNascimento(Calendar.getInstance());
		cliente.setNmCompletoMae("Midori");
		cliente.setNrDDDCelular(11);
		cliente.setNrCelular(992721111);
		cliente.setGenero(Genero.buscaPorId(1));
		cliente.setEstadoCivil(EstadoCivil.buscaPorId(1));
		cliente.setTxSenha1("12345");
		cliente.setTxSenha2("12345");
		cliente.setCdsCPF("12345678910");
		cliente.setCdsRG("ABCDFGHIG");
	}
	
	@Test(expected=DominioException.class)
	public void PrimeiroNomeDeveSerPreenchido() throws DominioException {
		cliente.setDsPrimeiroNome("");
		cliente.validate();
	}
	
	@Test(expected=DominioException.class)
	public void PrimeiroNomeNaoPermiteNull() throws DominioException {
		cliente.setDsPrimeiroNome(null);
		cliente.validate();
	}

	@Test(expected=DominioException.class)
	public void SobreNomeDeveSerPreenchido() throws DominioException {
		cliente.setDsSobreNome("");
		cliente.validate();
	}
	
	@Test(expected=DominioException.class)
	public void SobreNomeNaoPermiteNull() throws DominioException {
		cliente.setDsSobreNome(null);
		cliente.validate();
	}
	
	@Test(expected=DominioException.class)
	public void DataDeNascimentoNaoPermiteNull() throws DominioException {
		cliente.setDtNascimento(null);
		cliente.validate();
	}

	@Test(expected=DominioException.class)
	public void NomeMaeDeveSerPreenchido() throws DominioException {
		cliente.setNmCompletoMae("");
		cliente.validate();
	}

	@Test(expected=DominioException.class)
	public void NomeMaeNaoPermiteNull() throws DominioException {
		cliente.setNmCompletoMae(null);
		cliente.validate();
	}

	@Test(expected=DominioException.class)
	public void DDDCelularDeveSerPreenchido() throws DominioException {
		cliente.setNrDDDCelular(0);
		cliente.validate();
	}

	@Test(expected=DominioException.class)
	public void DDDCelularNaoPermiteNull() throws DominioException {
		cliente.setNrDDDCelular(null);
		cliente.validate();
	}
	
	@Test(expected=DominioException.class)
	public void NrCelularDeveSerPreenchido() throws DominioException {
		cliente.setNrCelular(0);
		cliente.validate();
	}

	@Test(expected=DominioException.class)
	public void NrCelularNaoPermiteNull() throws DominioException {
		cliente.setNrCelular(null);
		cliente.validate();
	}

	@Test(expected=DominioException.class)
	public void GeneroNaoPermiteNull() throws DominioException {
		cliente.setGenero(null);
		cliente.validate();
	}

	@Test(expected=DominioException.class)
	public void EstadoCivilNaoPermiteNull() throws DominioException {
		cliente.setEstadoCivil(null);
		cliente.validate();
	}

	@Test(expected=DominioException.class)
	public void SenhaDeveSerPreenchido() throws DominioException {
		cliente.setTxSenha1("");
		cliente.validate();
	}

	@Test(expected=DominioException.class)
	public void SenhaNaoPermiteNull() throws DominioException {
		cliente.setTxSenha1(null);
		cliente.validate();
	}

	@Test(expected=DominioException.class)
	public void CPFDeveSerPreenchido() throws DominioException {
		cliente.setCdsCPF("");
		cliente.validate();
	}

	@Test(expected=DominioException.class)
	public void CPFNaoPermiteNull() throws DominioException {
		cliente.setCdsCPF(null);
		cliente.validate();
	}
	
	@Test(expected=DominioException.class)
	public void RGDeveSerPreenchido() throws DominioException {
		cliente.setCdsRG("");
		cliente.validate();
	}

	@Test(expected=DominioException.class)
	public void RGNaoPermiteNull() throws DominioException {
		cliente.setCdsRG(null);
		cliente.validate();
	}
	@After
	public void closeTest() {
		closeEntityManager();
	}
	
}
