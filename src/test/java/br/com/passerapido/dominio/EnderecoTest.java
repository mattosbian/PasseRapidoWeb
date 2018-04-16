package br.com.passerapido.dominio;

import org.junit.Before;
import org.junit.Test;

import br.com.passerapido.exception.DominioException;

public class EnderecoTest {

	private Endereco endereco;

	@Before
	public void setUp( ) {
		endereco = new Endereco();
		endereco.setIdCliente(1);
		endereco.setSgEstado("SP");
		endereco.setNrCep("05030010");
	}
	
	@Test(expected=DominioException.class)
	public void idClienteDeveSerPreenchido( ) throws DominioException {
		endereco.setIdCliente(0);
		endereco.validate();
	}

	@Test(expected=DominioException.class)
	public void idClienteNaoPodeSerNull( ) throws DominioException {
		endereco.setIdCliente(null);
		endereco.validate();
	}

	@Test(expected=DominioException.class)
	public void sgEstadoDeveSerPreenchido( ) throws DominioException {
		endereco.setSgEstado("");
		endereco.validate();
	}

	@Test(expected=DominioException.class)
	public void sgEstadoNaoPodeSerNull( ) throws DominioException {
		endereco.setSgEstado(null);
		endereco.validate();
	}

	@Test(expected=DominioException.class)
	public void nrCepDeveSerPreenchido( ) throws DominioException {
		endereco.setNrCep("");
		endereco.validate();
	}

	@Test(expected=DominioException.class)
	public void nrCepNaoPodeSerNull( ) throws DominioException {
		endereco.setNrCep(null);
		endereco.validate();
	}
}
